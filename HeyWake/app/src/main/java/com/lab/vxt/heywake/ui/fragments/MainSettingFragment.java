package com.lab.vxt.heywake.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lab.vxt.heywake.R;

/**
 * Created by Thuy Nguyen on 12/24/2017.
 */

public class MainSettingFragment extends BaseFragment implements View.OnClickListener{
    private Button buttonAbout;
    private Button buttonSleep;
    private Button buttonEvent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_main_setting, container, false);
        buttonAbout = fragment.findViewById(R.id.buttonAbout);
        buttonSleep = fragment.findViewById(R.id.buttonSleep);
        buttonEvent = fragment.findViewById(R.id.buttonEvent);

        buttonAbout.setOnClickListener(this);
        buttonSleep.setOnClickListener(this);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        if(view == buttonAbout){
            changeFragment(new AboutFragment());
        }else if(view == buttonSleep){
            changeFragment(new SleepFragment());
        }else{
            changeFragment(new EventFragment());
        }
    }

    public void changeFragment(Fragment newFragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutSetting, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
