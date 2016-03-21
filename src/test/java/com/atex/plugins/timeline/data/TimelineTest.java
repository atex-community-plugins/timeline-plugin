package com.atex.plugins.timeline.data;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

/**
 * Unit test for {@link com.atex.plugins.timeline.data.Timeline}.
 */
@RunWith(MockitoJUnitRunner.class)
public class TimelineTest {

    private Timeline timeline = new Timeline();

    @Test
    public void testEmptyTimeline() {
        Assert.assertNotNull(timeline.getType());
        Assert.assertNull(timeline.getHeadline());
        Assert.assertNull(timeline.getText());
        Assert.assertNull(timeline.getAsset());
        Assert.assertNotNull(timeline.getDate());
        Assert.assertEquals(0, timeline.getDate().size());
    }

    @Test
    public void testNotEmptyTimeline() {
        final String type = RandomStringUtils.randomAlphanumeric(10);
        final String headline = RandomStringUtils.randomAlphanumeric(10);
        final String text = RandomStringUtils.randomAlphanumeric(10);
        final List<TimelineNews> news = Lists.newArrayList();
        news.add(null);

        timeline.setType(type);
        timeline.setHeadline(headline);
        timeline.setText(text);
        timeline.setAsset(new Asset());
        timeline.setDate(news);

        Assert.assertEquals(type, timeline.getType());
        Assert.assertEquals(headline, timeline.getHeadline());
        Assert.assertEquals(text, timeline.getText());
        Assert.assertNotNull(timeline.getAsset());
        Assert.assertEquals(news, timeline.getDate());
        Assert.assertEquals(1, timeline.getDate().size());
    }

}