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

    public static final int NUMBER_OF_QUESTION_REQUEST = 1;

    public static final String TABLE_NAME_QUESTION = "multipleChoiceQuestion";
    public static final String COLUMN_NAME_QUESTION_ID = "id";
    public static final String COLUMN_NAME_QUESTION_QUESTION = "question";
    public static final String COLUMN_NAME_QUESTION_FAIL1 = "fail1";
    public static final String COLUMN_NAME_QUESTION_FAIL2 = "fail2";
    public static final String COLUMN_NAME_QUESTION_FAIL3 = "fail3";
    public static final String COLUMN_NAME_QUESTION_TRUE4 = "true4";
    public static final String COLUMN_NAME_QUESTION_ISUSED = "isUsed";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "alarmclock.db";
}
