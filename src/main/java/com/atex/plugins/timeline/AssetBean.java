package com.atex.plugins.timeline;

import com.atex.onecms.content.Content;
import com.atex.onecms.ws.image.ImageServiceUrlBuilder;
import com.atex.plugins.imagegallery.util.ImageGalleryImageBean;

/**
 * Bean for an asset.
 */
public class AssetBean {

    private String byline;
    private String caption;
    private Content<ImageGalleryImageBean> content;
    private String imageServiceSecret;

    public String getByline() {
        return byline;
    }

    public void setByline(final String byline) {
        this.byline = byline;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public Content<ImageGalleryImageBean> getContent() {
        return content;
    }

    public void setContent(final Content<ImageGalleryImageBean> content) {
        this.content = content;
    }

    public String getImageServiceSecret() {
        return imageServiceSecret;
    }

    public void setImageServiceSecret(final String imageServiceSecret) {
        this.imageServiceSecret = imageServiceSecret;
    }

    public ImageServiceUrlBuilder createUrlBuilder() {
        return new ImageServiceUrlBuilder(content, imageServiceSecret);
    }

}
