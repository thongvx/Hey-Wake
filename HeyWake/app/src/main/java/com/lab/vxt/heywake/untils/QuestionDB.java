package com.lab.vxt.heywake.untils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lab.vxt.heywake.models.MultipleChoiceQuestion;

import java.util.ArrayList;

import static com.lab.vxt.heywake.untils.Constants.DATABASE_NAME;
import static com.lab.vxt.heywake.untils.Constants.DATABASE_VERSION;


/**
 * Created by Thuy Nguyen on 12/22/2017.
 */

public class QuestionDB extends SQLiteOpenHelper {

    public QuestionDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE "+ Constants.TABLE_NAME_QUESTION+" ("+ Constants.COLUMN_NAME_QUESTION_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Constants.COLUMN_NAME_QUESTION_QUESTION+" TEXT, "+
                Constants.COLUMN_NAME_QUESTION_FAIL1+" TEXT, "+
                Constants.COLUMN_NAME_QUESTION_FAIL2+" TEXT, "+
                Constants.COLUMN_NAME_QUESTION_FAIL3+" TEXT, "+
                Constants.COLUMN_NAME_QUESTION_TRUE4+" TEXT, "+
                Constants.COLUMN_NAME_QUESTION_ISUSED+" INTEGER DEFAULT 0)";
        sqLiteDatabase.execSQL(sqlQuery);
        Log.d("sql", sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME_QUESTION);
        onCreate(sqLiteDatabase);
    }

    public void addQuestion(ArrayList<MultipleChoiceQuestion> multipleChoiceQuestions){
        SQLiteDatabase db = this.getWritableDatabase();
        String arrayToSql = "";
        for(int i = 0; i < multipleChoiceQuestions.size(); i++){
            arrayToSql += "( '"+multipleChoiceQuestions.get(i).getQuestion()+"', '"+multipleChoiceQuestions.get(i).getFalse1()
                    +"', '"+multipleChoiceQuestions.get(i).getFalse2()+"', '"+multipleChoiceQuestions.get(i).getFalse3()+"', '"
                    +multipleChoiceQuestions.get(i).getTrue4()+"' )";
            if(i !=  multipleChoiceQuestions.size() - 1)
                arrayToSql += ",";
        }

        String sql = "INSERT INTO "+Constants.TABLE_NAME_QUESTION+" ("+Constants.COLUMN_NAME_QUESTION_QUESTION+
                ", "+Constants.COLUMN_NAME_QUESTION_FAIL1+", "+Constants.COLUMN_NAME_QUESTION_FAIL2+", "+Constants.COLUMN_NAME_QUESTION_FAIL3
                +", "+Constants.COLUMN_NAME_QUESTION_TRUE4+") VALUES "+ arrayToSql;

        Cursor cursor = db.rawQuery(sql, null);
        cursor.close();
        db.close();
    }

    public MultipleChoiceQuestion getQuestion() {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_NAME_QUESTION + " WHERE " + Constants.COLUMN_NAME_QUESTION_ISUSED + " = 0 LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()){
            multipleChoiceQuestion.setQuestion(cursor.getString(1));
            multipleChoiceQuestion.setFalse1(cursor.getString(2));
            multipleChoiceQuestion.setFalse2(cursor.getString(3));
            multipleChoiceQuestion.setFalse3(cursor.getString(4));
            multipleChoiceQuestion.setTrue4(cursor.getString(5));
            cursor.close();
        }
        db.close();
        return multipleChoiceQuestion;
    }

    public int updateQuestion(MultipleChoiceQuestion multipleChoiceQuestion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME_QUESTION_ISUSED, 1);

        // updating row
        return db.update(Constants.TABLE_NAME_QUESTION, values, Constants.COLUMN_NAME_QUESTION_ID + " = ?",
                new String[]{String.valueOf(multipleChoiceQuestion.getId())});
    }

    public void deleteQuestion() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME_QUESTION, Constants.COLUMN_NAME_QUESTION_ISUSED + " = ?",
                new String[] {String.valueOf(1)});
        db.close();
    }
}
