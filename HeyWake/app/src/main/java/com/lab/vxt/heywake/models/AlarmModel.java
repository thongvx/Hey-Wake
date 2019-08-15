package com.lab.vxt.heywake.models;

import android.net.Uri;

/**
 * Created by VXT on 10/30/2017.
 */

public class AlarmModel {

    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRDIAY = 5;
    public static final int SATURDAY = 6;

    public long id = -1;
    public int timeHour;
    public int timeMinute;
    public boolean repeatingDays[];
    public boolean repeatWeekly;
    public Uri alarmTone;
    public String name;
    public boolean isEnabled;
    public String style;  // cách tắt báo thức
    public int numOfReapeat; // số lần lặp lại

    public AlarmModel() {
        repeatingDays = new boolean[7];
    }

    public AlarmModel(long id, int timeHour, int timeMinute,
                      boolean[] repeatingDays, boolean repeatWeekly, Uri alarmTone,
                      String name, boolean isEnabled,String style, int numOfReapeat) {
        this.id = id;
        this.timeHour = timeHour;
        this.timeMinute = timeMinute;
        this.repeatingDays = repeatingDays;
        this.repeatWeekly = repeatWeekly;
        this.alarmTone = alarmTone;
        this.name = name;
        this.isEnabled = isEnabled;
        this.style = style;
        this.numOfReapeat = numOfReapeat;
    }

    public void setRepeatingDay(int dayOfWeek, boolean value) {
        repeatingDays[dayOfWeek] = value;
    }

    public boolean getRepeatingDay(int dayOfWeek) {
        return repeatingDays[dayOfWeek];
    }

    public String toString(){

        return this.timeHour+" : "+ timeMinute + " name : "+name +" isEnable :" +isEnabled +", id: "+id+" , style"+style;
    }

}
