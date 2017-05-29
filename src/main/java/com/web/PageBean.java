package com.web;

/**
 * Created by king on 2017/4/10.
 */
public class PageBean {
    private String Html = "";
    private String path = "";
    private String url = "";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String host="";

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    private String fileUrl="";

    public PageBean(String url) {
        this.url = url;
        this.host=Util.getHost(url);
        this.path=Data.g_topPath;
    }

    public String getHtml() {
        return Html;
    }

    public void setHtml(String html) {
        Html = html;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
