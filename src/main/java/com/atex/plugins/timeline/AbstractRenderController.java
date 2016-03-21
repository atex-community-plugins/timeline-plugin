package com.atex.plugins.timeline;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.polopoly.cm.ExternalContentId;
import com.polopoly.cm.client.CMException;
import com.polopoly.cm.policy.PolicyCMServer;
import com.polopoly.siteengine.mvc.RenderControllerBase;

/**
 * Abstract Render Controller, provides a {@link com.atex.plugins.timeline.TimelineConfigPolicy} config policy.
 */
public abstract class AbstractRenderController extends RenderControllerBase {

    private static final Logger LOGGER = Logger.getLogger(AbstractRenderController.class.getName());

    public static final String CONFIG_EXTERNALID = "plugins.com.atex.plugins.timeline.Config";

    /**
     * Get the configuration from the plugin configuration policy.
     *
     * @param cmServer
     * @return the configuration or null.
     */
    TimelineConfigPolicy getConfiguration(final PolicyCMServer cmServer) {
        try {
            final ExternalContentId searchConfigId = new ExternalContentId(CONFIG_EXTERNALID);
            final TimelineConfigPolicy policy = (TimelineConfigPolicy) cmServer.getPolicy(searchConfigId);
            return policy;
        } catch (CMException e) {
            LOGGER.log(Level.SEVERE, "Cannot load configuration from " + CONFIG_EXTERNALID + ": " + e.getMessage(), e);
        }
        return null;
    }

}
