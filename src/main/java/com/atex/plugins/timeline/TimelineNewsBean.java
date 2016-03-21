package com.atex.plugins.timeline;

import java.util.Date;

import com.polopoly.cm.ContentId;

/**
 * Bean for a single time line item.
 */
public class TimelineNewsBean {

    private ContentId contentId = null;

    private String headline = "";
    private String text = "";
    private Date startDate;
    private Date endDate;
    private String tag;
    private String classname;

    private ContentId imageContentId = null;
    private ContentId[] linkPath = null;

    /**
     * Policy contentId.
     *
     * @return
     */
    public ContentId getContentId() {
        return contentId;
    }

    /**
     * Set the policy contentId.
     *
     * @param contentId
     */
    public void setContentId(final ContentId contentId) {
        this.contentId = contentId;
    }

    /**
     * TimeLine item headline.
     *
     * @return
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * Set the TimeLine item headline.
     *
     * @param headline
     */
    public void setHeadline(final String headline) {
        this.headline = headline;
    }

    /**
     * TimeLine item text.
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Set the TimeLine item text.
     *
     * @param text
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * TimeLine item start date.
     *
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the TimeLine item start date.
     *
     * @param startDate
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * TimeLine item end date.
     *
     * @return
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the TimeLine item end date.
     *
     * @param endDate
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * TimeLine item tag.
     *
     * @return
     */
    public String getTag() {
        return tag;
    }

    /**
     * Set the TimeLine item tag.
     *
     * @param tag
     */
    public void setTag(final String tag) {
        this.tag = tag;
    }

    /**
     * TimeLine item class name.
     *
     * @return
     */
    public String getClassname() {
        return classname;
    }

    /**
     * Set the TimeLine item class name.
     *
     * @param classname
     */
    public void setClassname(final String classname) {
        this.classname = classname;
    }

    /**
     * Image Content Id (null for no image).
     *
     * @return
     */
    public ContentId getImageContentId() {
        return imageContentId;
    }

    /**
     * Set the image content id.
     *
     * @param imageContentId
     */
    public void setImageContentId(final ContentId imageContentId) {
        this.imageContentId = imageContentId;
    }

    /**
     * Get the item parent ids.
     *
     * @return
     */
    public ContentId[] getLinkPath() {
        return linkPath;
    }

    /**
     * Set the item parent ids.
     *
     * @param linkPath
     */
    public void setLinkPath(final ContentId[] linkPath) {
        this.linkPath = linkPath;
    }

}
