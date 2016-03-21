package com.atex.plugins.timeline;

import com.atex.plugins.baseline.policy.BaselinePolicy;

/**
 * Timeline element policy.
 */
public class TimelineConfigPolicy extends BaselinePolicy {

    private static final String MEDIASIZE = "mediaSize";
    private static final String LANG = "lang";
    private static final String FONT = "font";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String MAXITEMS = "maxItems";

    public static final int MEDIASIZE_DEFAULTVALUE = 0;
    public static final int MAXITEMS_DEFAULTVALUE = 50;

    public int getMediaSize() {
        return getChildIntValue(MEDIASIZE, MEDIASIZE_DEFAULTVALUE);
    }

    public String getLanguage() {
        return getChildValue(LANG, null);
    }

    public String getFont() {
        return getChildValue(FONT, null);
    }

    public String getWidth() {
        return getChildValue(WIDTH, null);
    }

    public String getHeight() {
        return getChildValue(HEIGHT, null);
    }

    public int getMaxItems() {
        return getChildIntValue(MAXITEMS, MAXITEMS_DEFAULTVALUE);
    }

    private int getChildIntValue(final String name, final int defaultValue) {

        final String value = getChildValue(name, Integer.toString(defaultValue));
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logger.warning("Cannot convert '" + value + "'");
        }
        return defaultValue;
    }

}
