package com.jying.taobao.Bean;

import java.util.List;

/**
 * Created by Jying on 2018/3/15.
 */

public class HomeBottom {
    private String state;
    private String msg;
    private int totalPage;
    private List<HomeBase> data;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<HomeBase> getData() {
        return data;
    }

    public void setData(List<HomeBase> data) {
        this.data = data;
    }
}
