package com.atex.plugins.timeline.data;

/**
 * Representation of a news in the timeline.
 */
public class TimelineNews {

    /**
     * Headline.
     */
    private String headline;

    /**
     * Text.
     */
    private String text;

    /**
     * Event start date.
     */
    private String startDate;

    /**
     * Event end date.
     */
    private String endDate;

    /**
     * Event category (max 6 different categories across all news in the same timeline are supported).
     */
    private String tag;

    /**
     * css class for this news.
     */
    private String classname;

    /**
     * Media asset.
     */
    private Asset asset;

    /**
     * Get the headline.
     *
     * @return the headline.
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * Set the headline.
     *
     * @param headline a string.
     */
    public void setHeadline(final String headline) {
        this.headline = headline;
    }

    /**
     * Get the text.
     *
     * @return the text.
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text.
     *
     * @param text a string.
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Get the event start date.
     *
     * @return the start date.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Set the event start date.
     *
     * @param startDate a date in the format "yyyy, MM, dd"
     */
    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    /**
     * Get the event end date.
     *
     * @return the end date.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Set the event end date.
     *
     * @param endDate a date in the format "yyyy, MM, dd"
     */
    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }

    /**
     * Get the tag.
     *
     * @return the event category.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Set the event tag.
     *
     * @param tag a string.
     */
    public void setTag(final String tag) {
        this.tag = tag;
    }

    /**
     * Get the css class.
     *
     * @return a css class.
     */
    public String getClassname() {
        return classname;
    }

    /**
     * Set the css class.
     *
     * @param classname a string.
     */
    public void setClassname(final String classname) {
        this.classname = classname;
    }

    /**
     * Get the associated asset.
     *
     * @return an asset or null.
     */
    public Asset getAsset() {
        return asset;
    }

    /**
     * Set the associated asset.
     *
     * @param asset an asset.
     */
    public void setAsset(final Asset asset) {
        this.asset = asset;
    }
}
