package com.example.nga.game;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by Nga on 11/22/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private int a;
    private int b;

    private List<Integer> listNumber;
    private Context context;
    private Button previusButton;

    private String colorDefault = "#808B96";
    private String[] colors = {"#2980B9","#C0392B","#F4D03F","#8E44AD","#2E4053","#6E2C00","#145A32","#7D6608"};
    private boolean flag = true;

    public MyAdapter(List<Integer> listNumber, Context context) {
        a = -1;
        b = -1;
        this.listNumber = listNumber;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_box,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final  ViewHolder holder, final int position) {

        final  int number = listNumber.get(position);
        holder.button.setBackgroundColor(Color.parseColor(colorDefault));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == true){

                    holder.button.setBackgroundColor(Color.parseColor(colors[number]));
                    if(a == -1 && b == -1){

                        holder.button.setBackgroundColor(Color.BLUE);
                    }

                    if(a == -1){
                        a = number;

                        previusButton = holder.button;


                    }else if(a == number && previusButton != holder.button){
                        flag = false;
                        b = listNumber.get(position);
                        //holder.button.setBackgroundColor(Color.parseColor(colors[number]));
                        a = b = -1;

                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        holder.button.setBackgroundColor(Color.WHITE);
                                        previusButton.setBackgroundColor(Color.WHITE);
                                        holder.button.setVisibility(View.INVISIBLE);
                                        previusButton.setVisibility(View.INVISIBLE);
                                        flag = true;
                                    }
                                },
                                1000);

                    }else{

                        a = b = -1;
                        flag = false;

                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        holder.button.setBackgroundColor(Color.parseColor(colorDefault));
                                        previusButton.setBackgroundColor(Color.parseColor(colorDefault));
                                        flag = true;
                                    }
                                },
                                1000);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNumber.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button ;
        public ViewHolder(View itemView) {
            super(itemView);
            button = (Button)itemView.findViewById(R.id.buttonItem);

        }
    }
}
