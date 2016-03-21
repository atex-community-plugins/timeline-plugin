package com.atex.plugins.timeline;

import javax.ws.rs.core.UriBuilder;

import com.polopoly.cm.client.CMRuntimeException;
import com.polopoly.cm.client.CmClient;
import com.polopoly.model.ModelPathUtil;
import com.polopoly.model.ModelWrite;
import com.polopoly.render.RenderRequest;
import com.polopoly.siteengine.dispatcher.ControllerContext;
import com.polopoly.siteengine.model.TopModel;

/**
 * Render controller for the timeline element.
 */
public class MainElementRenderController extends AbstractRenderController {

    @Override
    public void populateModelBeforeCacheKey(final RenderRequest request, final TopModel m, final ControllerContext context) {

        super.populateModelBeforeCacheKey(request, m, context);

        final CmClient cmClient = getCmClient(context);
        if (cmClient == null) {
            throw new CMRuntimeException("Could not fetch cmClient");
        }

        final ModelWrite local = m.getLocal();

        final TimelineConfigPolicy configPolicy = getConfiguration(cmClient.getPolicyCMServer());
        if (configPolicy != null) {
            local.setAttribute("lang", configPolicy.getLanguage());
            local.setAttribute("font", configPolicy.getFont());
            local.setAttribute("width", configPolicy.getWidth());
            local.setAttribute("height", configPolicy.getHeight());
        }

        final TimelinePolicy policy = (TimelinePolicy) ModelPathUtil.getBean(context.getContentModel());
        final String jsonUrl = UriBuilder.fromUri(m.getRequest().getHttpRequest().getRequestURI())
                                         .replaceQuery("")
                                         .queryParam("ot", "com.atex.plugins.timeline.JSONQueue.ot")
                                         .queryParam("elementId", policy.getContentId().getContentId().getContentIdString())
                                         .build()
                                         .toString();

        local.setAttribute("jsonUrl", jsonUrl);
        local.setAttribute("estimateListSize", policy.getDefaultList().size());
    }

}
