package com.atex.plugins.timeline;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.atex.onecms.content.Content;
import com.atex.onecms.content.ContentManager;
import com.atex.onecms.content.ContentResult;
import com.atex.onecms.content.ContentVersionId;
import com.atex.onecms.content.IdUtil;
import com.atex.onecms.content.Subject;
import com.atex.onecms.content.repository.StorageException;
import com.atex.onecms.ws.image.ImageServiceConfigurationProvider;
import com.atex.plugins.imagegallery.util.ImageGalleryImageBean;
import com.polopoly.cm.ContentId;
import com.polopoly.cm.client.CMException;
import com.polopoly.cm.client.CMRuntimeException;
import com.polopoly.cm.client.CmClient;
import com.polopoly.cm.policy.PolicyCMServer;

/**
 * TimeLine filter which provides beans given articles using a variant.
 */
public class TimelineNewsFilter {

    private static final Logger LOGGER = Logger.getLogger(TimelineNewsFilter.class.getName());

    private static final String TIMELINENEWS_VARIANT = "com.atex.plugins.timeline.timelinenews";

    private final PolicyCMServer cmServer;
    private final ContentManager contentManager;

    public TimelineNewsFilter(final CmClient cmClient) {
        if (cmClient == null) {
            throw new CMRuntimeException("Could not fetch cmClient");
        }

        cmServer = cmClient.getPolicyCMServer();
        contentManager = cmClient.getContentManager();
    }

    public TimelineNewsFilter(final PolicyCMServer cmServer, final ContentManager contentManager) {
        this.cmServer = cmServer;
        this.contentManager = contentManager;
    }

    /**
     * Fetches the result content using content manager, content must have a wrapper for
     * "com.atex.plugins.timeline.timelinenews" variant.
     *
     * @return a {@link com.atex.plugins.timeline.TimelineNewsBean} or null if no variant configured.
     */
    public TimelineNewsBean getTimelineNewsVariant(final ContentId contentId) {
        if (contentId != null) {
            try {
                ContentVersionId vid = getContentManager().resolve(IdUtil.fromPolicyContentId(contentId.getContentId()),
                        Subject.NOBODY_CALLER);
                ContentResult<TimelineNewsBean> dataResult = getContentManager().get(
                        vid,
                        TIMELINENEWS_VARIANT,
                        TimelineNewsBean.class,
                        Collections.<String, Object>emptyMap(),
                        Subject.NOBODY_CALLER);
                if (dataResult != null && dataResult.getContent() != null) {
                    return dataResult.getContent().getContentData();
                }
            } catch (ClassCastException | StorageException e) {
                LOGGER.log(Level.SEVERE, "Unable to fetch timelinenews variant content using id: "
                        + contentId.getContentId().getContentIdString(), e);
            }
        }
        return null;
    }

    public AssetBean getAssetBeanVariant(final ContentId contentId) {
        if (contentId != null) {
            try {
                ContentVersionId vid = getContentManager().resolve(IdUtil.fromPolicyContentId(contentId.getContentId()),
                        Subject.NOBODY_CALLER);
                final ContentResult<ImageGalleryImageBean> variant = getContentManager().get(
                        vid,
                        ImageGalleryImageBean.VARIANT_NAME,
                        ImageGalleryImageBean.class,
                        Collections.<String, Object>emptyMap(),
                        Subject.NOBODY_CALLER);
                if (variant != null && variant.getStatus().isOk()) {
                    final String secret = getImageServiceSecret();
                    final Content<ImageGalleryImageBean> content = variant.getContent();
                    final AssetBean assetBean = new AssetBean();
                    assetBean.setByline(content.getContentData().getByline());
                    assetBean.setCaption(content.getContentData().getCaption());
                    assetBean.setContent(content);
                    assetBean.setImageServiceSecret(secret);
                    return assetBean;
                }
            } catch (ClassCastException | StorageException | CMException e ) {
                LOGGER.log(Level.SEVERE, "Unable to fetch " + ImageGalleryImageBean.VARIANT_NAME
                        + " variant content using id: " + contentId.getContentId().getContentIdString(), e);
            }
        }
        return null;
    }

    private String getImageServiceSecret() throws CMException {
        return new ImageServiceConfigurationProvider(cmServer)
                .getImageServiceConfiguration()
                .getSecret();
    }

    public PolicyCMServer getCMServer() {
        return cmServer;
    }

    public ContentManager getContentManager() {
        return contentManager;
    }

}
