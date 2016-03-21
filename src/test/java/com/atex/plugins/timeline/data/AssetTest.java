package com.atex.plugins.timeline.data;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for {@link com.atex.plugins.timeline.data.Asset}.
 */
@RunWith(MockitoJUnitRunner.class)
public class AssetTest {

    private Asset asset = new Asset();

    @Test
    public void testEmptyAsset() {
        Assert.assertNull(asset.getMedia());
        Assert.assertNull(asset.getThumbnail());
        Assert.assertNull(asset.getCredit());
        Assert.assertNull(asset.getCaption());
    }

    @Test
    public void testNotEmptyTimeline() {
        final String media = RandomStringUtils.randomAlphanumeric(10);
        final String thumb = RandomStringUtils.randomAlphanumeric(10);
        final String credit = RandomStringUtils.randomAlphanumeric(10);
        final String caption = RandomStringUtils.randomAlphanumeric(10);

        asset.setMedia(media);
        asset.setThumbnail(thumb);
        asset.setCredit(credit);
        asset.setCaption(caption);

        Assert.assertEquals(media, asset.getMedia());
        Assert.assertEquals(thumb, asset.getThumbnail());
        Assert.assertEquals(credit, asset.getCredit());
        Assert.assertEquals(caption, asset.getCaption());
    }

}