package com.web;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by king on 2017/4/11.
 */
public class Data {
    public static List<String> g_url=new ArrayList<String>();
    public static String g_topPath="";
    public  static boolean isIn(String url){
        for(int i=0; i<g_url.size();i++){
           if(g_url.get(i).equalsIgnoreCase(url)){
               return true;
           }
        }
        return false;
    }
}
