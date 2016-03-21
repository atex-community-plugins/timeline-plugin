package com.atex.plugins.timeline.data;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for {@link com.atex.plugins.timeline.data.TimelineNews}.
 */
@RunWith(MockitoJUnitRunner.class)
public class TimelineNewsTest {

    private TimelineNews news = new TimelineNews();

    @Test
    public void testEmptyTimelineNews() {
        Assert.assertNull(news.getHeadline());
        Assert.assertNull(news.getText());
        Assert.assertNull(news.getAsset());
        Assert.assertNull(news.getStartDate());
        Assert.assertNull(news.getEndDate());
        Assert.assertNull(news.getTag());
        Assert.assertNull(news.getClassname());
    }

    @Test
    public void testNotEmptyTimelineNews() {
        final String headline = RandomStringUtils.randomAlphanumeric(10);
        final String text = RandomStringUtils.randomAlphanumeric(10);
        final String startDate = RandomStringUtils.randomAlphanumeric(10);
        final String endDate = RandomStringUtils.randomAlphanumeric(10);
        final String tag = RandomStringUtils.randomAlphanumeric(10);
        final String className = RandomStringUtils.randomAlphanumeric(10);

        news.setHeadline(headline);
        news.setText(text);
        news.setAsset(new Asset());
        news.setStartDate(startDate);
        news.setEndDate(endDate);
        news.setTag(tag);
        news.setClassname(className);

        Assert.assertEquals(headline, news.getHeadline());
        Assert.assertEquals(text, news.getText());
        Assert.assertNotNull(news.getAsset());
        Assert.assertEquals(startDate, news.getStartDate());
        Assert.assertEquals(endDate, news.getEndDate());
        Assert.assertEquals(tag, news.getTag());
        Assert.assertEquals(className, news.getClassname());
    }

}