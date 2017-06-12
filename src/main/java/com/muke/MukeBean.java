package com.muke;

import com.alibaba.fastjson.JSONArray;
import com.web.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuweimei on 2017/6/9.
 */
public class MukeBean {
    private String vid="";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name="";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url="";

    public MukeBean(String vid, String name, JSONArray urls) {
        this.vid = vid;
        this.name = Util.StringFilter(name);
        this.urls = urls;
        this.url=this.urls.getString(2);
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    private JSONArray urls=null;

}
