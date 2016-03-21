package com.atex.plugins.timeline;

import java.util.logging.Level;

import com.atex.plugins.baseline.policy.BaselinePolicy;
import com.polopoly.cm.ContentId;
import com.polopoly.cm.app.policy.SingleValuePolicy;
import com.polopoly.cm.client.CMException;
import com.polopoly.cm.collections.ContentList;
import com.polopoly.cm.collections.ContentListProvider;
import com.polopoly.cm.collections.ContentListUtil;

/**
 * Policy of a timeline.
 */
public class TimelinePolicy extends BaselinePolicy {

    private static final String NAME = "headline";
    private static final String BODY = "body";
    private static final String PUBLISHINGQUEUE = "publishingQueue";
    private static final String IMAGE_LIST = "images";

    @Override
    public String getName() throws CMException {
        String name = null;
        try {
            name = ((SingleValuePolicy) getChildPolicy(NAME)).getValue();
        } catch (CMException e) {
            logger.log(Level.SEVERE, "Couldn't read child policy with name '" + NAME + "'", e);
        }
        return name == null ? super.getName() : name;
    }

    public String getBody() {
        return getChildValue(BODY, null);
    }

    public ContentList getDefaultList() {
        ContentList list = ContentListUtil.EMPTY_CONTENT_LIST;
        try {
            ContentList queue = getContentList(PUBLISHINGQUEUE);
            if (queue.size() > 0) {
                final ContentId queueId = queue.getEntry(0).getReferredContentId();
                final ContentListProvider queuePolicy = (ContentListProvider) getCMServer().getPolicy(queueId);
                list = queuePolicy.getContentList();
            }
        } catch (CMException e) {
            logger.log(Level.WARNING, "Unable to get content list publishingQueue", e);
        }
        return list;
    }

    public ContentId getImageContentId() {
        ContentId imageId = null;
        try {
            final ContentList list = getContentList(IMAGE_LIST);
            if (list != null && list.size() > 0) {
                imageId = list.getEntry(0).getReferredContentId();
            }
        } catch (CMException exception) {
            logger.log(Level.SEVERE, "Couldn't get image id for timeline: " + getContentId(), exception);
        }
        return imageId;
    }

}
