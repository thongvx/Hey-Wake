package com.example.nga.game1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    LinearLayout container;

    List<Button> btnList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout)findViewById(R.id.container);


    }

    public void addBtn(View v){
        btnList.add(new Button(this));
        container.addView(btnList.get(btnList.size() - 1 ));
    }
}
