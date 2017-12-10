package com.example.alexander.numbergame;



import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

/**
 * Created by Alexander on 05/12/2017.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private int index = 0;
    private int count = 0;
    private int counterr = 0;

    private List<Integer> listNumber;
    private Context context;
    private String colorDefault = "#808B96";

    public MyAdapter(List<Integer> listNumber, Context context) {
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
        String k = Integer.toString(number);
        holder.button.setText(k);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        if(count == Integer.parseInt((String) holder.button.getText())){
                            count++;
                            holder.button.setVisibility(View.INVISIBLE);

                        }
                        else{
                            if(counterr > 0){
                                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
                                dlgAlert.setMessage("Do not touch without a look at it, cheater!");
                                dlgAlert.setTitle("Take it ez");
                                dlgAlert.setPositiveButton("OK", null);
                                dlgAlert.setCancelable(true);
                                dlgAlert.create().show();
                                counterr = 0;
                            }
                            else{
                                counterr++;
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
    private int[] arr() {
        int Max = 47;
        int A[] = new int[Max];
        for (int i = 0; i < Max; i++) {
            A[i] = i;
        }

        return A;
    }
}