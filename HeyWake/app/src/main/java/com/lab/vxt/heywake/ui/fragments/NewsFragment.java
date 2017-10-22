package com.lab.vxt.heywake.ui.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lab.vxt.heywake.R;
import com.lab.vxt.heywake.models.News;
import com.lab.vxt.heywake.ui.adapters.AdapterNews;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by VXT on 10/23/2017.
 */

public class NewsFragment extends BaseFragment {
    private final String vnexpressRSS = "https://vnexpress.net/rss/tin-moi-nhat.rss";
    private RecyclerView recyclerViewNews;
    private AdapterNews adapterNews;
    DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerViewNews = fragment.findViewById(R.id.recyclerViewNews);
        new LoadRss().execute(vnexpressRSS);
        return fragment;

    }

    class  LoadRss extends AsyncTask<String,Void,ArrayList<News>> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            String url = strings[0];
            ArrayList<News> result = new ArrayList<>();

            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements = doc.select("item");
                for (Element item : elements) {
                    String title = item.select("title").text();
                    String link = item.select("link").text();
                    String time = item.select("pubDate").text();
                    String description = item.select("description").text();
                    // lấy trộm ảnh từ thằng description => dùng jsoup để parse
                    Document docImage = Jsoup.parse(description);
                    String [] arrTmp = time.split("\\s+");
                    time= arrTmp[4];

                    String imgURL = docImage.select("img").get(0).attr("src");
                    result.add(new News(imgURL, title, "VNEXPRESS", time,link));
                }

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<News> newsModels) {

            adapterNews = new AdapterNews(getContext(), newsModels);
            recyclerViewNews.setAdapter(adapterNews);
            recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));
            adapterNews.notifyDataSetChanged();
            super.onPostExecute(newsModels);
        }

    }


}
