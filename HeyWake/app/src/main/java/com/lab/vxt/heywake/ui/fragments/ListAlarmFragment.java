package com.lab.vxt.heywake.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lab.vxt.heywake.R;

/**
 * Created by VXT on 10/23/2017.
 */

public class ListAlarmFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_alarm_list,container,false);

        return fragment;
    }
}
