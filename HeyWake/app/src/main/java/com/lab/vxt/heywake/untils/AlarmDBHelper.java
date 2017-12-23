package com.lab.vxt.heywake.untils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.lab.vxt.heywake.models.AlarmModel;

import java.util.ArrayList;
import java.util.List;

import static com.lab.vxt.heywake.untils.Constants.DATABASE_NAME;
import static com.lab.vxt.heywake.untils.Constants.DATABASE_VERSION;

/**
 * Created by VXT on 11/22/2017.
 */

public class AlarmDBHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ALARM = "CREATE TABLE " + AlarmContract.Alarm.TABLE_NAME + " (" +
            AlarmContract.Alarm._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_NAME + " TEXT," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_TIME_HOUR + " INTEGER," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_TIME_MINUTE + " INTEGER," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_REPEAT_DAYS + " TEXT," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_REPEAT_WEEKLY + " BOOLEAN," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_TONE + " TEXT," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_ENABLED + " BOOLEAN" + " )";

    private static final String SQL_DELETE_ALARM =
            "DROP TABLE IF EXISTS " + AlarmContract.Alarm.TABLE_NAME;

    public AlarmDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
        if (context == null){
            Log.d("thong","sai");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(SQL_CREATE_ALARM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(SQL_DELETE_ALARM);
        onCreate(db);
    }

    private AlarmModel populateModel(Cursor c) {
        AlarmModel model = new AlarmModel();
        model.id = c.getLong(c.getColumnIndex(AlarmContract.Alarm._ID));
        model.name = c.getString(c.getColumnIndex(AlarmContract.Alarm.COLUMN_NAME_ALARM_NAME));
        model.timeHour = c.getInt(c.getColumnIndex(AlarmContract.Alarm.COLUMN_NAME_ALARM_TIME_HOUR));
        model.timeMinute = c.getInt(c.getColumnIndex(AlarmContract.Alarm.COLUMN_NAME_ALARM_TIME_MINUTE));
        model.repeatWeekly = c.getInt(c.getColumnIndex(AlarmContract.Alarm.COLUMN_NAME_ALARM_REPEAT_WEEKLY)) == 0 ? false : true;
        model.alarmTone = Uri.parse(c.getString(c.getColumnIndex(AlarmContract.Alarm.COLUMN_NAME_ALARM_TONE)));
        model.isEnabled = c.getInt(c.getColumnIndex(AlarmContract.Alarm.COLUMN_NAME_ALARM_ENABLED)) == 0 ? false : true;

        String[] repeatingDays = c.getString(c.getColumnIndex(AlarmContract.Alarm.COLUMN_NAME_ALARM_REPEAT_DAYS)).split(",");
        for (int i = 0; i < repeatingDays.length; ++i) {
            model.setRepeatingDay(i, repeatingDays[i].equals("false") ? false : true);
        }

        return model;
    }

    private ContentValues populateContent(AlarmModel model) {
        ContentValues values = new ContentValues();
        values.put(AlarmContract.Alarm.COLUMN_NAME_ALARM_NAME, model.name);
        values.put(AlarmContract.Alarm.COLUMN_NAME_ALARM_TIME_HOUR, model.timeHour);
        values.put(AlarmContract.Alarm.COLUMN_NAME_ALARM_TIME_MINUTE, model.timeMinute);
        values.put(AlarmContract.Alarm.COLUMN_NAME_ALARM_REPEAT_WEEKLY, model.repeatWeekly);
        values.put(AlarmContract.Alarm.COLUMN_NAME_ALARM_TONE, model.alarmTone != null ? model.alarmTone.toString() : "");
        values.put(AlarmContract.Alarm.COLUMN_NAME_ALARM_ENABLED, model.isEnabled);

        String repeatingDays = "";
        for (int i = 0; i<7; ++i) {
            repeatingDays += model.getRepeatingDay(i) + ",";
        }
        values.put(AlarmContract.Alarm.COLUMN_NAME_ALARM_REPEAT_DAYS, repeatingDays);

        return values;
    }

    public long createAlarm(AlarmModel model) {
        ContentValues values = populateContent(model);
        return getWritableDatabase().insert(AlarmContract.Alarm.TABLE_NAME, null, values);
    }

    public AlarmModel getAlarm(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + AlarmContract.Alarm.TABLE_NAME + " WHERE " + AlarmContract.Alarm._ID + " = " + id;

        Cursor c = db.rawQuery(select, null)
                ;
        if (c.moveToNext()) {
            return populateModel(c);
        }

        return null;
    }

    public long updateAlarm(AlarmModel model) {
        ContentValues values = populateContent(model);
        return getWritableDatabase().update(AlarmContract.Alarm.TABLE_NAME, values, AlarmContract.Alarm._ID + " = ?", new String[] {String.valueOf(model.id)});
    }

    public int deleteAlarm(long id) {
        return getWritableDatabase().delete(AlarmContract.Alarm.TABLE_NAME, AlarmContract.Alarm._ID + " = ?", new String[] {String.valueOf(id)});
    }

    public List<AlarmModel> getAlarms() {

        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + AlarmContract.Alarm.TABLE_NAME;

        Cursor c = db.rawQuery(select, null);

        List<AlarmModel> alarmList = new ArrayList<AlarmModel>();

        while (c.moveToNext()) {
            alarmList.add(populateModel(c));

        }


        if (!alarmList.isEmpty()) {
            /*for (AlarmModel alarmModel : alarmList){
                Log.d("Alarm : ",alarmModel.toString());
            }*/
            return alarmList;
        }
        return null;
    }

}