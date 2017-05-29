package com.houdun;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by king on 2017/5/10.
 */
public class HouDunService {
    public static List<VideoBean> videoList=new ArrayList<VideoBean>();

    public String url=null;

    public HouDunService(String url) {
        this.url = url;
        getVideoList(this.url);
    }

    private void  getVideoList(String url) {
        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)").get();
            Elements links= doc.getElementsByClass("cover");
            for(Element link:links){
               String href= link.attr("href");
               VideoBean vb=new VideoBean(href);
                vb.setVideo_Url(getVideo(vb));
               videoList.add(vb);
               System.out.println(vb.getVideo_Url());
            }
            System.out.println("分析完毕！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String  getVideo(VideoBean vb) {
        try {
            Document doc = Jsoup.connect(vb.getPage_Url()).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)").get();
            Elements es=doc.getElementsByTag("source");
            if(es.size()==1) {
               return es.get(0).attr("src").toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
