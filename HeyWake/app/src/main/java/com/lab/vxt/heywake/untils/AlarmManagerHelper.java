package com.lab.vxt.heywake.untils;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lab.vxt.heywake.models.AlarmModel;
import com.lab.vxt.heywake.reciever.AlarmReciever;

import java.util.Calendar;
import java.util.List;

/**
 * Created by VXT on 11/22/2017.
 */

public class AlarmManagerHelper extends BroadcastReceiver {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TIME_HOUR = "timeHour";
    public static final String TIME_MINUTE = "timeMinute";
    public static final String TONE = "alarmTone";


    String TAG = this.getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        setAlarms(context);
    }

    /*
    public static void setAlarms(Context context) {
        Log.d("SetAlarms","work");

        cancelAlarms(context);

        AlarmDBHelper dbHelper = new AlarmDBHelper(context);

        List<AlarmModel> alarms = dbHelper.getAlarms();

        for (AlarmModel alarm:alarms) {
            if (alarm.isEnabled) {
                PendingIntent pIntent = createPendingIntent(context, alarm);

                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY, alarm.timeHour);
                calendar.set(Calendar.MINUTE, alarm.timeMinute);
                calendar.set(Calendar.SECOND, 00);

                final int nowDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                final int nowHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                final int nowMinute = Calendar.getInstance().get(Calendar.MINUTE);
                boolean alarmSet = false;


                if ((alarm.timeHour > nowHour) | ((alarm.timeHour == nowHour) && (alarm.timeMinute > nowMinute))){
                    calendar.set(Calendar.DAY_OF_WEEK, nowDay);
                    setAlarm(context, calendar, pIntent);
                    alarmSet = true;
                } else {
                    Log.d("time", "vao day");
                    for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; ++ dayOfWeek) {
                        if ( alarm.getRepeatingDay(dayOfWeek -1 ) && dayOfWeek >= nowDay && !(dayOfWeek == nowDay && alarm.timeHour < nowHour)
                                && !(dayOfWeek == nowDay && alarm.timeHour == nowHour && alarm.timeMinute <= nowMinute)) {
                            calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                            Log.i("abc123", "set Alarmed: " + nowDay + " : " + alarm.timeHour + " : " + alarm.timeMinute);

                            setAlarm(context, calendar, pIntent);
                            alarmSet = true;
                            break;
                        }
                    }
                }

                if (!alarmSet) {
                    for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; ++ dayOfWeek) {
                        if (alarm.getRepeatingDay(dayOfWeek -1 ) && dayOfWeek <= nowDay && alarm.repeatWeekly) {
                            calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                            calendar.add(Calendar.WEEK_OF_YEAR, 1);

                            setAlarm(context, calendar, pIntent);
                            break;
                        }
                    }
                }
            }
        }
    }

    */


    public static void setAlarms(Context context){

        AlarmDBHelper dbHelper = new AlarmDBHelper(context);
        List<AlarmModel> alarms = dbHelper.getAlarms();
        cancelAlarms(context,alarms);
        for (AlarmModel alarm:alarms){
            if (alarm.isEnabled == true){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarm.timeHour);
                calendar.set(Calendar.MINUTE, alarm.timeMinute);
                calendar.set(Calendar.SECOND, 00);
                long timestart = calendar.getTimeInMillis();


                final int nowDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                final int nowHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                final int nowMinute = Calendar.getInstance().get(Calendar.MINUTE);
                boolean alarmSet = false;

                if ((alarm.timeHour > nowHour) | ((alarm.timeHour == nowHour) && (alarm.timeMinute > nowMinute))){
                    calendar.set(Calendar.DAY_OF_WEEK, nowDay);
                    setOneAlarm(context,calendar,alarm);
                    alarmSet = true;
                }else {
                    Log.d("time", "vao day");
                    for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; ++ dayOfWeek) {
                        if ( alarm.getRepeatingDay(dayOfWeek -1 ) && dayOfWeek >= nowDay && !(dayOfWeek == nowDay && alarm.timeHour < nowHour)
                                && !(dayOfWeek == nowDay && alarm.timeHour == nowHour && alarm.timeMinute <= nowMinute)) {
                            calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                            Log.i("abc123", "set Alarmed: " + nowDay + " : " + alarm.timeHour + " : " + alarm.timeMinute);

                            setOneAlarm(context, calendar,alarm);
                            alarmSet = true;
                            break;
                        }
                    }
                }

                if (!alarmSet) {
                    for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; ++ dayOfWeek) {
                        if (alarm.getRepeatingDay(dayOfWeek -1 ) && dayOfWeek <= nowDay && alarm.repeatWeekly) {
                            calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                            calendar.add(Calendar.WEEK_OF_YEAR, 1);
                            setOneAlarm(context, calendar,alarm);
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void setOneAlarm(Context context,Calendar calendar, AlarmModel alarm){
        Intent myIntent = new Intent(context,AlarmReciever.class);
        myIntent.putExtra(ID, alarm.id);
        myIntent.putExtra(NAME, alarm.name);
        myIntent.putExtra(TIME_HOUR, alarm.timeHour);
        myIntent.putExtra(TIME_MINUTE, alarm.timeMinute);
        myIntent.putExtra(TONE, alarm.alarmTone.toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,(int) alarm.id,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
    }
    @SuppressLint("NewApi")
    public static void setAlarm(Context context, Calendar calendar, PendingIntent pIntent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Log.d("time",calendar.getTimeInMillis()+"");
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);

//		alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
//		Toast.makeText(this, "Alarm scheduled for tomorrow", Toast.LENGTH_SHORT).show();
    }

    public static void cancelAlarms(Context context,List<AlarmModel> alarms) {


        if (alarms != null)
        {
            for (AlarmModel alarm:alarms) {
                if (alarm.isEnabled) {

                    PendingIntent pendingIntent = createPendingIntent(context,alarm);
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                }
            }
        }
    }

    private static PendingIntent createPendingIntent(Context context, AlarmModel model) {
        Intent intent = new Intent(context, AlarmReciever.class);
        intent.putExtra(ID, model.id);
        intent.putExtra(NAME, model.name);
        intent.putExtra(TIME_HOUR, model.timeHour);
        intent.putExtra(TIME_MINUTE, model.timeMinute);
        intent.putExtra(TONE, model.alarmTone.toString());
        intent.putExtra("MODE",model.style);
        intent.putExtra("NUM_REPEATE",model.numOfReapeat);

        return PendingIntent.getService(context, (int) model.id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
