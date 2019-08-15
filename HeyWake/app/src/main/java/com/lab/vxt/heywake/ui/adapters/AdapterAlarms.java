package com.lab.vxt.heywake.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.lab.vxt.heywake.R;
import com.lab.vxt.heywake.models.AlarmModel;
import com.lab.vxt.heywake.untils.Constants;

import java.util.List;

/**
 * Created by VXT on 10/30/2017.
 */

public class AdapterAlarms extends RecyclerView.Adapter<AdapterAlarms.ViewHolder> {

    private Context context;
    private List<AlarmModel> alarmModels;

    public AdapterAlarms(Context context, List<AlarmModel> alarmModels) {
        this.context = context;
        this.alarmModels = alarmModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm,parent,false);

        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        AlarmModel alarm = alarmModels.get(position);

        holder.textViewTime.setText(String.format("%02d : %02d", alarm.timeHour, alarm.timeMinute));
        holder.switchStatus.setChecked(alarm.isEnabled);
        if (alarm.style.equals(Constants.DEFAULT_MODE)){
            holder.imageViewMode.setImageDrawable((context.getResources().getDrawable(R.drawable.ic_alarm_black_24dp)));
        }
        if (alarm.style.equals(Constants.SHAKE_MODE)){
            holder.imageViewMode.setImageDrawable((context.getResources().getDrawable(R.drawable.ic_vibration_black_24dp)));
        }
        if (alarm.style.equals(Constants.MEMORY_TASK_MODE )){
            holder.imageViewMode.setImageDrawable((context.getResources().getDrawable(R.drawable.ic_games_black_24dp)));
        }
        if (alarm.style.equals(Constants.COUNT_NUMBER_MODE )){
            holder.imageViewMode.setImageDrawable((context.getResources().getDrawable(R.drawable.ic_games_black_24dp)));
        }

        holder.switchStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){

                    holder.textViewRepeat.setTextColor(Color.BLACK);
                }else {


                    holder.textViewRepeat.setTextColor(Color.GRAY);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (alarmModels == null){
            return 0;
        }
        return alarmModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTime;
        public TextView textViewRepeat;
        public Switch switchStatus;
        public ImageView imageViewMode;
        public ViewHolder(View itemView) {
            super(itemView);

            textViewTime = (TextView)itemView.findViewById(R.id.textViewTime);
            textViewRepeat = (TextView)itemView.findViewById(R.id.textViewRepeat);
            imageViewMode = (ImageView)itemView.findViewById(R.id.imageViewMode);
            switchStatus = (Switch)itemView.findViewById(R.id.switchStatus);
        }
    }
}
