package com.lab.vxt.heywake.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lab.vxt.heywake.R;
import com.lab.vxt.heywake.models.AlarmModel;
import com.lab.vxt.heywake.untils.AlarmDBHelper;
import com.lab.vxt.heywake.untils.AlarmManagerHelper;
import com.lab.vxt.heywake.untils.Constants;

import java.util.ArrayList;

public class AddAlarmActivity extends AppCompatActivity {
    private AlarmModel alarmDetails;
    private AlarmDBHelper alarmDBHelper = new AlarmDBHelper(this);

    private Toolbar toolbar;
    private ConstraintLayout constraintLayoutAlarmMode;
    private ConstraintLayout constraintLayoutSelectTune;
    private ConstraintLayout constraintLayoutRepeate;
    private ConstraintLayout constraintLayoutTitle;
    private TextView textViewTuntTitle;
    private TimePicker timePickerAddAlarm;
    private Toolbar mToolbar;
    private TextView textViewDate;
    private String mTitle;

    private ConstraintLayout constraintLayoutDefault;
    private ConstraintLayout constraintLayoutShake;
    private ConstraintLayout constraintLayoutCountNum;
    private ConstraintLayout constraintLayoutRememberTask;
    private ConstraintLayout previousConstraintLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        bindViews();
        //Trong trường hợp chỉ tạo mới
        alarmDetails = new AlarmModel();
        alarmDetails.style = Constants.DEFAULT_MODE;
        Log.d("thong bug",alarmDetails.style);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.save_item) {

            updateAlarm();
            alarmDBHelper.createAlarm(alarmDetails);
            AlarmManagerHelper.setAlarms(AddAlarmActivity.this);
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    protected void bindViews(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        constraintLayoutAlarmMode = (ConstraintLayout) findViewById(R.id.constraintLayoutAlarmMode);
        constraintLayoutSelectTune = (ConstraintLayout) findViewById(R.id.constraintLayoutSelectTune);
        constraintLayoutRepeate = (ConstraintLayout) findViewById(R.id.constraintLayoutRepeate);
        constraintLayoutTitle = (ConstraintLayout) findViewById(R.id.constraintLayoutTitle);

        timePickerAddAlarm = (TimePicker)findViewById(R.id.timePickerAddAlarm);

        textViewDate = (TextView)findViewById(R.id.textViewDate);
        textViewTuntTitle = (TextView)findViewById(R.id.textViewTuneTitle);

        constraintLayoutAlarmMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogChooseStyle();
            }
        });

        constraintLayoutRepeate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
        constraintLayoutTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTitleDialog();
            }
        });
        constraintLayoutSelectTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Chọn nhạc chuông");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
                startActivityForResult(intent, 5);
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

    private void createDialogChooseStyle(){
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_choose_style,null);
        constraintLayoutDefault = (ConstraintLayout)dialogView.findViewById(R.id.constraintLayoutDefault);
        constraintLayoutCountNum = (ConstraintLayout)dialogView.findViewById(R.id.constraintLayoutCountNum);
        constraintLayoutShake = (ConstraintLayout)dialogView.findViewById(R.id.constraintLayoutShake);
        constraintLayoutRememberTask = (ConstraintLayout)dialogView.findViewById(R.id.constraintLayoutRememberTask);
        previousConstraintLayout = constraintLayoutDefault;
        dialogView.setBackgroundColor(Color.TRANSPARENT);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(dialogView);
        final AlertDialog chooseModeDialog = alert.create();
        constraintLayoutRememberTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick(constraintLayoutRememberTask);
                alarmDetails.style = Constants.SHAKE_MODE;
            }
        });
        constraintLayoutDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick(constraintLayoutDefault);
                alarmDetails.style = Constants.DEFAULT_MODE;
            }
        });
        constraintLayoutShake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick(constraintLayoutShake);
                createChooseNumberDialog();

                chooseModeDialog.dismiss();

            }
        });
        constraintLayoutCountNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick(constraintLayoutCountNum);
                alarmDetails.style = Constants.COUNT_NUMBER_MODE;
            }
        });

        chooseModeDialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        chooseModeDialog.show();
    }

    private void createChooseNumberDialog(){
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_shake_number,null);

        final String[] displayedValues = {"30","35","40","45","50","25","20"};
        final NumberPicker numberPickerCount = (NumberPicker)dialogView.findViewById(R.id.numberPickerCount);
        Button buttonOk = (Button)dialogView.findViewById(R.id.buttonOk);
        numberPickerCount.setMinValue(0);
        numberPickerCount.setMaxValue(displayedValues.length-1);
        numberPickerCount.setDisplayedValues(displayedValues);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(dialogView);
        AlertDialog chooseModeDialog = alert.create();
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmDetails.style = Constants.SHAKE_MODE;
                alarmDetails.numOfReapeat = Integer.parseInt(displayedValues[numberPickerCount.getValue()]);
            }
        });
        chooseModeDialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        chooseModeDialog.show();
    }
    private void onclick(ConstraintLayout layout){
        if (layout != previousConstraintLayout){
            layout.setBackgroundResource(R.drawable.bg_alarmode_active);
            previousConstraintLayout.setBackgroundResource(R.drawable.bg_alarmode_inactive);
            previousConstraintLayout = layout;
        }
    }
}
