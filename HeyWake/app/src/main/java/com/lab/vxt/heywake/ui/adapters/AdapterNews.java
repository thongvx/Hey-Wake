package com.lab.vxt.heywake.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lab.vxt.heywake.R;
import com.lab.vxt.heywake.models.News;
import com.lab.vxt.heywake.ui.activities.DetailsNewsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by VXT on 10/23/2017.
 */

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {
    private Context context;
    private ArrayList<News> newses;

    public AdapterNews(Context context, ArrayList<News> newses) {
        this.context = context;
        this.newses = newses;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final News news = newses.get(position);

        Picasso.with(context).load(news.getImageURL()).into(holder.imageViewAvatar);
        holder.textViewNewsTime.setText(news.getNewsTime());
        holder.textViewNewsSource.setText(news.getNewsSource());
        holder.textViewTitle.setText(news.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsNewsActivity.class);
                intent.putExtra("LINK",news.getLink());
                context.startActivity(intent);
            }
        });

        Log.d("thong",news.getImageURL());
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewAvatar;
        public TextView textViewTitle;
        public TextView textViewNewsSource;
        public TextView textViewNewsTime;
        public ViewHolder(View itemView) {
            super(itemView);
            imageViewAvatar = (ImageView)itemView.findViewById(R.id.imageViewNewsAvatar);
            textViewNewsSource = (TextView)itemView.findViewById(R.id.textViewNewsSource);
            textViewTitle = (TextView)itemView.findViewById(R.id.textViewNewsTitle);
            textViewNewsTime = (TextView)itemView.findViewById(R.id.textViewNewsTime);

        }
    }
}
