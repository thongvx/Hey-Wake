package com.lab.vxt.heywake.ui.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.lab.vxt.heywake.R;

/**
 * Created by Thuy Nguyen on 12/24/2017.
 */

public class SleepFragment extends BaseFragment{
    private Button buttonSleepSetting;
    private TextView textViewSleep;
    private int hour, minute;
    private TimePicker timePickerSleep;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_sleep, container, false);

        buttonSleepSetting = fragment.findViewById(R.id.buttonSleepSetting);
        textViewSleep = fragment.findViewById(R.id.textViewSleep);

        timePickerSleep = fragment.findViewById(R.id.timePickerSleep);
        hour = timePickerSleep.getCurrentHour().intValue();
        minute = timePickerSleep.getCurrentMinute().intValue();
        textViewSleep.setText(hour+" : " + minute );

        buttonSleepSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        textViewSleep.setText(hour+" : " + minute );
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });
        return fragment;

    }
}
