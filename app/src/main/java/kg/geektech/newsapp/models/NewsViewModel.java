package kg.geektech.newsapp.models;

import java.io.Serializable;

public class NewsViewModel implements Serializable {
    private String title;
    private long data;

    public NewsViewModel(String title, long data) {
        this.title = title;
        this.data = data;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }
}
