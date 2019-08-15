package com.lab.vxt.heywake.untils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Thuy Nguyen on 12/24/2017.
 */

public class InternetDetector {

    private Context context;
    public InternetDetector(Context context) {
        this.context = context;
    }

    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null){
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null)
                if(networkInfo.getState() == NetworkInfo.State.CONNECTED)
                    return true;
        }
        return  false;
    }
}
