package com.maizi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by king on 2017/5/10.
 */
public class MaiZiService {
    public static List<VideoBean> videoList = new ArrayList<VideoBean>();

    public String url = null;

    public MaiZiService(String url) {
        this.url = url;
        getVideoList(this.url);
    }

    private void getVideoList(String url) {
        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)").get();
            Elements links = doc.select("div.lesson li>*");
            for (Element link : links) {
                String href = link.attr("abs:href");
                VideoBean vb = new VideoBean(href);
                vb.setVideo_Url(getVideo(vb));
                videoList.add(vb);
                System.out.println(vb.getVideo_Url());
            }
            System.out.println("分析完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getVideo(VideoBean vb) {
        try {
            Document doc = Jsoup.connect(vb.getPage_Url()).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)").get();
            Elements e = doc.getElementsByTag("script").eq(2);
            String str=e.html();
            String pattern = "http:.*.mp4";

            Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            Matcher m = r.matcher(str);
            while(m.find()){
               return m.group().toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
