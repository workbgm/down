package com.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

/**
 * Created by king on 2017/4/11.
 */
public class PageService {
    private Elements links=null;
    private Elements medias =null;
    private Elements imports=null;
    private PageBean pb=null;
    private Document doc=null;

    /**
     * 构造方法
     * @param pb
     */
    public PageService(PageBean pb) {
        this.pb = pb;
        Util.createDirectory(this.pb.getPath());//创建目录
        try {
            this.doc = Jsoup.connect(pb.getUrl()).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)").get();
            links = this.doc.select("a[href]");
            medias = this.doc.select("[src]");
            imports = this.doc.select("link[href]");
            this.getMediaFile();
            this.getImportsFile();
            this.getLinks_();
            String htmlPath=this.pb.getPath()+Util.StringFilter(this.pb.getUrl())+".html";
            Util.saveFile(htmlPath,this.doc.html());
            //this.getLinksFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取link数据
     */
    private void getLinksFile(){
        String linkFilePath=this.pb.getPath();
        Util.createDirectory(linkFilePath);//创建目录
        Util.log("开始下载link:["+pb.getUrl()+"]");
        for(Element link:this.links){
            if(link.attr("href").equals("/")) continue;
            String href=link.attr("ahref");
            if(Data.isIn(href)) continue;
            Data.g_url.add(href);
            if(Util.getHost(href).equalsIgnoreCase(this.pb.getHost())) { //同域名才下载
                this.pb.setFileUrl(linkFilePath);
                new PageService(new PageBean(href));
            }
            //Util.log("下载link:"+href);
        }
    }

    /**
     * 变更链接
     */
    private void getLinks_(){
        String linkFilePath=this.pb.getPath();
        for(Element link:this.links){
            String href=link.attr("abs:href");
            try {
                if(href==null  || href.length() ==0) continue;
                if (Util.getHost(href).equalsIgnoreCase(this.pb.getHost())) { //同域名才下载
                    link.attr("ahref", href);
                    link.attr("href", Util.StringFilter(href) + ".html");
                }
            }catch (IllegalStateException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取Import数据
     */
    private void getImportsFile(){
        String importFilePath=this.pb.getPath()+"import-"+Util.StringFilter(this.pb.getUrl())+"/";
        Util.createDirectory(importFilePath);//创建目录
        Util.log("开始下载import:["+pb.getUrl()+"]");
        for(Element importf:this.imports){
            String href=importf.attr("abs:href");
            String fileName=Util.getFileName(href);
            if(fileName!=null  && fileName.length() >0) {
                String path = importFilePath + fileName;
                Util.downFile(href, path);
                importf.attr("href", "import-"+Util.StringFilter(this.pb.getUrl())+"/"+fileName);
                //Util.log("下载import:"+path);
            }
        }
    }

    /**
     * 获取Media数据
     */
    private void  getMediaFile(){
        String mediaFilePath=this.pb.getPath()+"media-"+Util.StringFilter(this.pb.getUrl())+"/";
        Util.createDirectory(mediaFilePath);//创建目录
        Util.log("开始下载media:["+pb.getUrl()+"]");
        for(Element media :this.medias){
            String src=media.attr("abs:src");
            String path=mediaFilePath+Util.getFileName(src);
            Util.downFile(src,path);
            media.attr("src","media-"+Util.StringFilter(this.pb.getUrl())+"/"+Util.getFileName(src)+Util.getFilepixName(src));
            //Util.log("下载Media:"+path);
        }
    }


    public Elements getLinks() {
        return links;
    }

    public void setLinks(Elements links) {
        this.links = links;
    }

    public Elements getMedias() {
        return medias;
    }

    public void setMedias(Elements medias) {
        this.medias = medias;
    }

    public Elements getImports() {
        return imports;
    }

    public void setImports(Elements imports) {
        this.imports = imports;
    }

    public PageBean getPb() {
        return pb;
    }

    public void setPb(PageBean pb) {
        this.pb = pb;
    }
}
