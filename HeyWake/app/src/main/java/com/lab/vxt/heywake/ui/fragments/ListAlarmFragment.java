package com.lab.vxt.heywake.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lab.vxt.heywake.R;
import com.lab.vxt.heywake.ui.adapters.AdapterAlarms;
import com.lab.vxt.heywake.untils.AlarmDBHelper;
/**
 * Created by VXT on 10/23/2017.
 */

public class ListAlarmFragment extends BaseFragment  {

    private RecyclerView recyclerViewAlarms;
    private AdapterAlarms adapterAlarms;
    private AlarmDBHelper alarmDBHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_alarm_list,container,false);
        alarmDBHelper  = new AlarmDBHelper(getContext());

        recyclerViewAlarms = (RecyclerView)fragment.findViewById(R.id.recyclerViewAlarms);
        adapterAlarms = new AdapterAlarms(getContext(), alarmDBHelper.getAlarms());
        recyclerViewAlarms.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAlarms.setAdapter(adapterAlarms);

        return fragment;
    }

    @Override
    public void onResume() {

        super.onResume();
        adapterAlarms = new AdapterAlarms(getContext(), alarmDBHelper.getAlarms());
        recyclerViewAlarms.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAlarms.setAdapter(adapterAlarms);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
