package com.lab.vxt.heywake.models;

/**
 * Created by VXT on 10/23/2017.
 */

public class News {
    private String imageURL;
    private String title;
    private String newsSource;
    private String newsTime;
    private String link;


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public News(String imageURL, String title, String newsSource, String newsTime, String link) {
        this.imageURL = imageURL;
        this.title = title;
        this.newsSource = newsSource;
        this.newsTime = newsTime;
        this.link = link;

    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }
}
