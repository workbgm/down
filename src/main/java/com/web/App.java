package com.web;

import com.houdun.HouDunService;
import com.maizi.MaiZiService;

/**
 * Jsoup下载整站
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        String url="http://www.eruipan.com/";
//        String path="D:";
//        Data.g_topPath=path+"/"+Util.StringFilter(url)+"/";
//        PageService ps=new PageService(new PageBean(url));
//        HouDunService hds=new HouDunService("http://www.houdunren.com/houdunren18_59_.html");
        MaiZiService mz= new MaiZiService("http://www.maiziedu.com/course/904-13317/");
    }

}
