package com.atex.plugins.timeline.data;

/**
 * Media asset.
 */
public class Asset {

    /**
     * Media url.
     */
    private String media;

    /**
     * Thumbnail url.
     */
    private String thumbnail;

    /**
     * Credit.
     */
    private String credit;

    /**
     * Caption.
     */
    private String caption;

    /**
     * Get the media url.
     *
     * @return an url.
     */
    public String getMedia() {
        return media;
    }

    /**
     * Set the media url.
     *
     * @param media a string.
     */
    public void setMedia(final String media) {
        this.media = media;
    }

    /**
     * Get the thumbnail url.
     *
     * @return an url.
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Set the thumbnail url, it should be a 32x32 jpeg or png image.
     *
     * @param thumbnail a string.
     */
    public void setThumbnail(final String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * Get the credit text (i.e. author, byline, etc.)
     *
     * @return the credit text.
     */
    public String getCredit() {
        return credit;
    }

    /**
     * Set the credit text.
     *
     * @param credit a string.
     */
    public void setCredit(final String credit) {
        this.credit = credit;
    }

    /**
     * Get the caption text.
     *
     * @return the caption text.
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Set the caption text.
     *
     * @param caption a string.
     */
    public void setCaption(final String caption) {
        this.caption = caption;
    }
}
