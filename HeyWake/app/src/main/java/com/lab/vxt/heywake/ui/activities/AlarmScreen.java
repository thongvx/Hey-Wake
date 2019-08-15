package com.lab.vxt.heywake.ui.activities;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lab.vxt.heywake.R;
import com.lab.vxt.heywake.untils.AlarmManagerHelper;

public class AlarmScreen extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private PowerManager.WakeLock mWakelock;
    private MediaPlayer mMedia;
    int k= 3;

    private static int WAKELOCK_TIME = 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_alarm_screen);

        String name = getIntent().getStringExtra(AlarmManagerHelper.NAME);
        int timeHour = getIntent().getIntExtra(AlarmManagerHelper.TIME_HOUR, 0);
        int timeMinute = getIntent().getIntExtra(AlarmManagerHelper.TIME_MINUTE, 0);
        String tone = getIntent().getStringExtra(AlarmManagerHelper.TONE);
        String style = getIntent().getStringExtra(AlarmManagerHelper.MODE);


        TextView tvName = (TextView) findViewById(R.id.alarm_screen_name);
        tvName.setText(name);

        TextView tvTime = (TextView) findViewById(R.id.alarm_screen_time);
        tvTime.setText(String.format("%02d : %02d", timeHour, timeMinute));

        Button dismissBtn = (Button) findViewById(R.id.alarm_screen_dismiss);

        dismissBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                k = k - 1;
                Toast.makeText(AlarmScreen.this,"Ấn "+k+" lần nữa để tắt báo thức !",Toast.LENGTH_SHORT).show();
                if(k == 0) {
                    mMedia.stop();
                    finish();
                }
            }
        });

        mMedia = new MediaPlayer();

        try {
            if (tone != null && !tone.equals("")) {
                Uri toneUri = Uri.parse(tone);
                if (toneUri != null) {
                    mMedia.setDataSource(this, toneUri);
                    mMedia.setAudioStreamType(AudioManager.STREAM_ALARM);
                    mMedia.setLooping(true);
                    mMedia.prepare();
                    mMedia.start();
                }
            }else{
                mMedia = MediaPlayer.create(AlarmScreen.this,R.raw.song);
                mMedia.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMedia.setLooping(true);
                mMedia.prepare();
                mMedia.start();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        Runnable releaseWakelock = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

                if (mWakelock != null && mWakelock.isHeld()) {
                    mWakelock.release();
                }
            }
        };

        new Handler().postDelayed(releaseWakelock, WAKELOCK_TIME);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        PowerManager pm = (PowerManager) getApplication().getSystemService(Context.POWER_SERVICE);

        if (mWakelock == null) {
            mWakelock = pm.newWakeLock((PowerManager.FULL_WAKE_LOCK | PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), TAG);
        }

        if (!mWakelock.isHeld()) {
            mWakelock.acquire();
            Log.i(TAG, "Wakelog acquired!");
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        if (mWakelock != null && mWakelock.isHeld()) {
            mWakelock.release();
        }
    }
}
