package com.atex.plugins.timeline;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atex.onecms.image.AspectRatio;
import com.atex.onecms.image.ResizeMode;
import com.atex.onecms.ws.image.ImageServiceUrlBuilder;
import com.atex.plugins.timeline.data.Asset;
import com.atex.plugins.timeline.data.Timeline;
import com.atex.plugins.timeline.data.TimelineNews;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.polopoly.cm.ContentIdFactory;
import com.polopoly.cm.ContentReference;
import com.polopoly.cm.client.CMException;
import com.polopoly.cm.client.CMRuntimeException;
import com.polopoly.cm.client.CmClient;
import com.polopoly.cm.collections.ContentList;
import com.polopoly.cm.collections.ContentListUtil;
import com.polopoly.cm.policy.Policy;
import com.polopoly.cm.servlet.RequestPreparator;
import com.polopoly.cm.servlet.URLBuilder;
import com.polopoly.model.ModelWrite;
import com.polopoly.render.CacheInfo;
import com.polopoly.render.RenderRequest;
import com.polopoly.render.RenderResponse;
import com.polopoly.siteengine.dispatcher.ControllerContext;
import com.polopoly.siteengine.model.TopModel;

/**
 * Render controller which generates the json data used by timelineJs.
 */
public class JSONQueueRenderController extends AbstractRenderController {

    private static final Logger LOGGER = Logger.getLogger(JSONQueueRenderController.class.getName());

    private static final String MAIN_ELEMENT_EXTERNAL_ID = "com.atex.plugins.timeline.MainElement";
    private static final String APPLICATION_JSON = "application/json";

    @Override
    public com.polopoly.siteengine.mvc.Renderer getRenderer(final RenderRequest request,
                                                            final TopModel m,
                                                            final com.polopoly.siteengine.mvc.Renderer defaultRenderer,
                                                            final ControllerContext context) {
        return new Renderer(defaultRenderer);
    }

    @Override
    public void populateModelBeforeCacheKey(final RenderRequest request, final TopModel m, final ControllerContext context) {

        super.populateModelBeforeCacheKey(request, m, context);

        final CmClient cmClient = getCmClient(context);
        if (cmClient == null) {
            throw new CMRuntimeException("Could not fetch cmClient");
        }

        final ModelWrite local = m.getLocal();

        Policy mainElement;
        try {
            com.polopoly.cm.ContentId elementId = ContentIdFactory.createContentId(request.getParameter("elementId"));
            mainElement = cmClient.getPolicyCMServer().getPolicy(elementId);
            if (!MAIN_ELEMENT_EXTERNAL_ID.equals(mainElement.getInputTemplate().getName())) {
                LOGGER.log(Level.WARNING, "Failed to get MainElement's policy, incorrect type: " + mainElement.getInputTemplate().getName());
                return;
            }

            final int mediaSize;
            final int maxItems;

            final TimelineConfigPolicy configPolicy = getConfiguration(cmClient.getPolicyCMServer());
            if (configPolicy != null) {
                mediaSize = configPolicy.getMediaSize();
                maxItems = configPolicy.getMaxItems();
            } else {
                mediaSize = TimelineConfigPolicy.MEDIASIZE_DEFAULTVALUE;
                maxItems = TimelineConfigPolicy.MAXITEMS_DEFAULTVALUE;
            }

            final TimelineNewsFilter filter = new TimelineNewsFilter(cmClient);

            final TimelinePolicy timelinePolicy = (TimelinePolicy) mainElement;
            final Timeline timeline = new Timeline();
            timeline.setHeadline(timelinePolicy.getName());
            timeline.setText(timelinePolicy.getBody());
            if (timelinePolicy.getImageContentId() != null) {
                final AssetBean assetBean = filter.getAssetBeanVariant(timelinePolicy.getImageContentId());
                if (assetBean != null) {
                    timeline.setAsset(createFromBean(assetBean, mediaSize));
                }
            }

            int numItems = 0;
            final ContentList contentList = timelinePolicy.getDefaultList();
            for (final ContentReference ref : ContentListUtil.iterable(contentList)) {
                final TimelineNewsBean bean = filter.getTimelineNewsVariant(ref.getReferredContentId());
                if (bean != null) {
                    final TimelineNews news = createFromBean(bean, (HttpServletRequest) request);
                    if (bean.getImageContentId() != null) {
                        final AssetBean assetBean = filter.getAssetBeanVariant(bean.getImageContentId());
                        if (assetBean != null) {
                            news.setAsset(createFromBean(assetBean, mediaSize));
                        }
                    }
                    timeline.getDate().add(news);
                    numItems += 1;
                    if (numItems >= maxItems) {
                        break;
                    }
                }
            }

            final Gson gson = new Gson();
            local.setAttribute("json", gson.toJson(timeline));
        } catch (CMException e) {
            LOGGER.log(Level.WARNING, "Failed to get MainElement's policy", e);
            return;
        }
    }

    private Asset createFromBean(final AssetBean bean, final int mediaSize) {
        final Asset asset = new Asset();
        asset.setCaption(bean.getCaption());
        asset.setCredit(bean.getByline());
        final ImageServiceUrlBuilder thumbUrlBuilder = bean
                .createUrlBuilder()
                .width(32)
                .height(32)
                .aspectRatio(new AspectRatio(1, 1))
                .mode(ResizeMode.FILL);

        asset.setThumbnail(thumbUrlBuilder.buildUrl());

        ImageServiceUrlBuilder mediaUrlBuilder = bean
                .createUrlBuilder()
                .aspectRatio(new AspectRatio(1, 1));
        if (mediaSize > 0) {
            mediaUrlBuilder = mediaUrlBuilder
                    .width(mediaSize)
                    .height(mediaSize);
        }

        asset.setMedia(mediaUrlBuilder.buildUrl());
        return asset;
    }

    private TimelineNews createFromBean(final TimelineNewsBean bean, final HttpServletRequest request)
            throws CMException {
        final URLBuilder urlBuilder = RequestPreparator.getURLBuilder(request);
        final String url = urlBuilder.createUrl(bean.getLinkPath(), request);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd");
        final TimelineNews news = new TimelineNews();
        news.setHeadline("<a href=\"" + url + "\">" + Strings.nullToEmpty(bean.getHeadline()) + "</a>");
        news.setText(bean.getText());
        news.setClassname(bean.getClassname());
        news.setTag(bean.getTag());
        if (bean.getStartDate() != null) {
            news.setStartDate(dateFormat.format(bean.getStartDate()));
        }
        if (bean.getEndDate() != null) {
            news.setEndDate(dateFormat.format(bean.getEndDate()));
        }
        return news;
    }

    private static class Renderer implements com.polopoly.siteengine.mvc.Renderer {
        private final com.polopoly.siteengine.mvc.Renderer defaultRenderer;

        public Renderer(final com.polopoly.siteengine.mvc.Renderer defaultRenderer) {
            this.defaultRenderer = defaultRenderer;
        }

        @Override
        public void render(final TopModel topModel,
                           final RenderRequest renderRequest,
                           final RenderResponse renderResponse,
                           final CacheInfo cacheInfo,
                           final ControllerContext controllerContext) {

            if (((HttpServletResponse) renderResponse).isCommitted()) {
                LOGGER.warning("Response was committed before rendering.");
                return;
            }
            renderResponse.setContentType(APPLICATION_JSON);
            defaultRenderer.render(topModel, renderRequest, renderResponse, cacheInfo, controllerContext);
        }
    }


}
