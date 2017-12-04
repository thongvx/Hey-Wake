package com.lab.vxt.heywake.ui.activities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lab.vxt.heywake.R;

public class AlarmModeActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayoutDefault;
    private ConstraintLayout constraintLayoutShake;
    private ConstraintLayout constraintLayoutGameQuiz;
    private ConstraintLayout constraintLayoutRememberTask;

    private ConstraintLayout previousConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_mode);
        constraintLayoutDefault = (ConstraintLayout)findViewById(R.id.constraintLayoutDefault);
        constraintLayoutGameQuiz = (ConstraintLayout)findViewById(R.id.constraintLayoutQuizGame);
        constraintLayoutShake = (ConstraintLayout)findViewById(R.id.constraintLayoutShake);
        constraintLayoutRememberTask = (ConstraintLayout)findViewById(R.id.constraintLayoutRememberTask);
        previousConstraintLayout = constraintLayoutDefault;


        constraintLayoutRememberTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick(constraintLayoutRememberTask);
            }
        });
        constraintLayoutDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick(constraintLayoutDefault);
            }
        });
        constraintLayoutShake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick(constraintLayoutShake);
            }
        });
        constraintLayoutGameQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick(constraintLayoutGameQuiz);
            }
        });


    }


    private void onclick(ConstraintLayout layout){

        if (layout != previousConstraintLayout){
            layout.setBackgroundResource(R.drawable.bg_alarmode_active);
            previousConstraintLayout.setBackgroundResource(R.drawable.bg_alarmode_inactive);
            previousConstraintLayout = layout;
        }

    }
}
