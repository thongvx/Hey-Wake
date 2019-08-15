package com.lab.vxt.heywake.reciever;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.lab.vxt.heywake.R;
import com.lab.vxt.heywake.ui.activities.AlarmScreen;
import com.lab.vxt.heywake.ui.activities.AlarmShakeActivity;
import com.lab.vxt.heywake.untils.AlarmManagerHelper;
import com.lab.vxt.heywake.untils.Constants;

/**
 * Created by VXT on 12/4/2017.
 */

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Thức dậy với những hạnh phúc nào!")
                .setContentText("THIS IS MY ALARM")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentInfo("Info");

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());

        String mode = intent.getExtras().getString(AlarmManagerHelper.MODE);
        if (mode.equals(Constants.DEFAULT_MODE)){
            Intent alarmIntent = new Intent(context, AlarmScreen.class);
            alarmIntent.putExtras(intent);
            context.startActivity(alarmIntent);

        }



        if (mode.equals(Constants.SHAKE_MODE)){
            Intent alarmIntent = new Intent(context, AlarmShakeActivity.class);
            alarmIntent.putExtras(intent);
            context.startActivity(alarmIntent);
        }



    }
}
