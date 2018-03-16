package com.jying.taobao.Bean;

import java.util.List;

/**
 * Created by Jying on 2018/3/15.
 */

public class HomeTop {
    private List<HomeBase> headlines;
    private List<HomeBase> list;


    public List<HomeBase> getHeadlines() {
        return headlines;
    }

    public void setHeadlines(List<HomeBase> headlines) {
        this.headlines = headlines;
    }


    public List<HomeBase> getList() {
        return list;
    }

    public void setList(List<HomeBase> list) {
        this.list = list;
    }

    public static class Carousel {
        private String url;
        private int id;

        public Carousel(int id, String url) {
            this.id = id;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
