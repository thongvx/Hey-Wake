package com.lab.vxt.heywake.ui.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.lab.vxt.heywake.R;
import com.lab.vxt.heywake.ShakeDetector;
import com.lab.vxt.heywake.untils.AlarmManagerHelper;

public class AlarmShakeActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private TextView textViewCount;
    private TextView textViewClock;
    private TextView textViewTitle;
    private TextView textViewDescription;

    private int numberRepeate;

    private MediaPlayer ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_shake);


        String name = getIntent().getStringExtra(AlarmManagerHelper.NAME);
        int timeHour = getIntent().getIntExtra(AlarmManagerHelper.TIME_HOUR, 0);
        int timeMinute = getIntent().getIntExtra(AlarmManagerHelper.TIME_MINUTE, 0);
        String tone = getIntent().getStringExtra(AlarmManagerHelper.TONE);
        String style = getIntent().getStringExtra(AlarmManagerHelper.MODE);
        numberRepeate = getIntent().getIntExtra(AlarmManagerHelper.NUM_REPEATE,0);



        textViewCount = (TextView) findViewById(R.id.textViewCount);
        textViewClock = (TextView)findViewById(R.id.textViewClock);
        textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        textViewTitle = (TextView)findViewById(R.id.textViewTitle);

        textViewTitle.setText(name);
        textViewDescription.setText("Lắc điện thoại "+numberRepeate+" lần để tắt!");
        textViewClock.setText(String.format("%02d : %02d", timeHour, timeMinute));


        ring= MediaPlayer.create(AlarmShakeActivity.this,R.raw.song);
        ring.start();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                handleShakeEvent(count);
            }
        });
    }

    private void handleShakeEvent(int count) {
        textViewCount.setText(count+"");
        Toast.makeText(this, "shake", Toast.LENGTH_SHORT).show();
        if(count == numberRepeate){
            ring.stop();
            finish();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
