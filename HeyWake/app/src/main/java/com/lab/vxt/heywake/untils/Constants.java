package com.lab.vxt.heywake.untils;

import android.provider.BaseColumns;

/**
 * Created by VXT on 10/30/2017.
 */

public class Constants implements BaseColumns {
    public static final String TABLE_NAME = "alarm";
    public static final String COLUMN_NAME_ALARM_NAME = "name";
    public static final String COLUMN_NAME_ALARM_TIME_HOUR = "hour";
    public static final String COLUMN_NAME_ALARM_TIME_MINUTE = "minute";
    public static final String COLUMN_NAME_ALARM_REPEAT_DAYS = "days";
    public static final String COLUMN_NAME_ALARM_REPEAT_WEEKLY = "weekly";
    public static final String COLUMN_NAME_ALARM_TONE = "tone";
    public static final String COLUMN_NAME_ALARM_ENABLED = "isEnabled";
}
