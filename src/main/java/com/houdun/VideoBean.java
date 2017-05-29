package com.houdun;

/**
 * Created by king on 2017/5/10.
 */
public class VideoBean {
    private String page_Url=null;

    public String getVideo_Url() {
        return video_Url;
    }

    public void setVideo_Url(String video_Url) {
        this.video_Url = video_Url;
    }

    private String video_Url=null;

    public VideoBean(String page_Url) {
        this.page_Url = page_Url;
    }

    public String getPage_Url() {
        return page_Url;
    }

    public void setPage_Url(String page_Url) {
        this.page_Url = page_Url;
    }

}
