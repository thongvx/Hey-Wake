package com.lab.vxt.heywake.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lab.vxt.heywake.R;
import com.lab.vxt.heywake.models.AlarmModel;
import com.lab.vxt.heywake.untils.AlarmDBHelper;
import com.lab.vxt.heywake.untils.AlarmManagerHelper;

import java.util.ArrayList;

public class AddAlarmActivity extends BaseActivity {
    private AlarmModel alarmDetails;
    private AlarmDBHelper alarmDBHelper = new AlarmDBHelper(this);

    private Toolbar toolbar;
    private CardView cardViewMode;
    private CardView cardViewSelectTune;
    private CardView cardViewRepeate;
    private CardView cardViewTitle;
    private Button buttonAddAlarmCancle;
    private Button buttonAddAlarmOk;

    private TextView textViewTuntTitle;

    private TimePicker timePickerAddAlarm;

    private TextView textViewDate;

    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        bindViews();

        //Trong trường hợp chỉ tạo mới
        alarmDetails = new AlarmModel();

    }


    protected void bindViews(){
        cardViewMode = (CardView)findViewById(R.id.cardviewMode);
        cardViewSelectTune = (CardView)findViewById(R.id.cardviewSelectTune);
        cardViewRepeate = (CardView)findViewById(R.id.cardviewRepeate);
        cardViewTitle = (CardView)findViewById(R.id.cardviewTitle);

        buttonAddAlarmCancle = (Button)findViewById(R.id.buttonCancle);
        buttonAddAlarmOk = (Button)findViewById(R.id.buttonOk);


        timePickerAddAlarm = (TimePicker)findViewById(R.id.timePickerAddAlarm);

        textViewDate = (TextView)findViewById(R.id.textViewDate);
        textViewTuntTitle = (TextView)findViewById(R.id.textViewTuneTitle);

        cardViewMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAlarmActivity.this, AlarmModeActivity.class);
                startActivity(intent);
            }
        });

        cardViewRepeate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
        cardViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTitleDialog();
            }
        });
        cardViewSelectTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Chọn nhạc chuông");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
                startActivityForResult(intent, 5);
            }
        });

        buttonAddAlarmOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlarmManagerHelper.cancelAlarms(AddAlarmActivity.this);
                showToast("Save");
                updateAlarm();
                alarmDBHelper.createAlarm(alarmDetails);
                AlarmManagerHelper.setAlarms(AddAlarmActivity.this);
                Intent intent = new Intent(AddAlarmActivity.this, HomeActivity.class);
                AddAlarmActivity.this.startActivity(intent);
            }
        });
        buttonAddAlarmCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAlarmActivity.this, HomeActivity.class);
                AddAlarmActivity.this.startActivity(intent);
                showToast("Thoat");
            }
        });
    }

    private void createDialog(){
        final CharSequence[] items = {" Thứ 2 "," Thứ 3 "," Thứ 4 "," Thứ 5 ", " Thứ 6 ", " Thứ 7 ", " Chủ nhật "};

        int selected [] = {1,3};
        final ArrayList seletedItems=new ArrayList();

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Lặp lại vào các ngày")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            seletedItems.add(indexSelected);

                        } else if (seletedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        String s = "";
                        int n = seletedItems.size();
                        for (int i = 0; i < n ; i++){
                            s = s+ "Th"+ seletedItems.get(i)+" ";
                            textViewDate.setText(s);
                        }

                    }
                }).setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel
                    }
                }).create();
        dialog.show();
    }
    private void createTitleDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nhãn thông báo");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTitle = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent)
    {
        if (resultCode == Activity.RESULT_OK && requestCode == 5)
        {
            Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            if (uri != null)
            {
                Toast.makeText(this,"ok"+uri,Toast.LENGTH_SHORT).show();
                alarmDetails.alarmTone = uri;

                Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
                String title = ringtone.getTitle(this);
                textViewTuntTitle.setText(title);
            }
            else
            {
                Toast.makeText(this,"Không nhận được âm thanh",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateAlarm(){
        alarmDetails.timeMinute = timePickerAddAlarm.getCurrentMinute().intValue();
        alarmDetails.timeHour = timePickerAddAlarm.getCurrentHour().intValue();
        alarmDetails.name = mTitle;
        alarmDetails.isEnabled = true;
    }

}
