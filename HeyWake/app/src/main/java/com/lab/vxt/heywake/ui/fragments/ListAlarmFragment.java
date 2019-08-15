package com.lab.vxt.heywake.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lab.vxt.heywake.R;
import com.lab.vxt.heywake.models.AlarmModel;
import com.lab.vxt.heywake.ui.adapters.AdapterAlarms;
import com.lab.vxt.heywake.untils.AlarmDBHelper;

import java.util.List;

/**
 * Created by VXT on 10/23/2017.
 */

public class ListAlarmFragment extends BaseFragment  {

    private RecyclerView recyclerViewAlarms;
    private AdapterAlarms adapterAlarms;
    private AlarmDBHelper alarmDBHelper;


    private LinearLayout linearLayoutRemind;
    private  LinearLayout linearLayoutQoutes;
    private  List<AlarmModel> alarmModelList;
    private ImageView imageViewAlarmMode;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_alarm_list,container,false);
        alarmDBHelper  = new AlarmDBHelper(getContext());
        linearLayoutRemind = (LinearLayout)fragment.findViewById(R.id.linearLayoutRemind);
        linearLayoutQoutes = (LinearLayout)fragment.findViewById(R.id.linearLayoutQoutes);

        alarmModelList = alarmDBHelper.getAlarms();
        if (alarmModelList != null){
            linearLayoutRemind.setVisibility(View.GONE);
            linearLayoutQoutes.setVisibility(View.VISIBLE);
        }else{
            linearLayoutRemind.setVisibility(View.VISIBLE);
            linearLayoutQoutes.setVisibility(View.GONE);
        }
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
        if (alarmDBHelper.getAlarms() != null){
            linearLayoutRemind.setVisibility(View.GONE);
            linearLayoutQoutes.setVisibility(View.VISIBLE);
        }



    }
    @Override
    public void onDestroy() {
        super.onDestroy();


    }
    public void afterSave(){
        linearLayoutRemind.setVisibility(View.GONE);
        linearLayoutQoutes.setVisibility(View.VISIBLE);
    }


}
