package com.atex.plugins.timeline.data;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Wrapper for the timeline data.
 */
public class Timeline {

    /**
     * Headline.
     */
    private String headline;

    /**
     * Timeline type.
     */
    private String type = "default";

    /**
     * Text.
     */
    private String text;

    /**
     * Media asset.
     */
    private Asset asset;

    /**
     * News list.
     */
    private List<TimelineNews> date = Lists.newArrayList();

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
     * Get the timeline type. Right now only "default" is supported.
     *
     * @return the timeline type.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the timeline type.
     *
     * @param type a string.
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Get the timeline text.
     *
     * @return the text.
     */
    public String getText() {
        return text;
    }

    /**
     * Set the timeline text.
     *
     * @param text a string.
     */
    public void setText(final String text) {
        this.text = text;
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

    /**
     * Get the list of news.
     *
     * @return a not null list of news.
     */
    public List<TimelineNews> getDate() {
        return date;
    }

    /**
     * Set the list of news.
     *
     * @param date a not null list.
     */
    public void setDate(final List<TimelineNews> date) {
        this.date = date;
    }
}
