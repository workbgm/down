package com.web;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wuweimei on 2017/6/13.
 */
public class downFileThread implements  Runnable {
    public final static boolean DEBUG = true; // 调试用
    protected  String destUrl;
    protected  String fileName;
    protected  String info;
    protected  int current;
    protected   int count;
    protected  int buffer_size;

    public downFileThread(String destUrl, String fileName, int current,int count, int buffer_size) {
        this.destUrl = destUrl;
        this.fileName = fileName;
        this.info = "【"+String.valueOf(current)+" of "+String.valueOf(count)+"】";
        this.current=current;
        this.count= count;
        this.buffer_size = buffer_size;
    }

    /**
     * 将HTTP资源另存为文件
     * @throws Exception
     */
    public void saveToFile() throws IOException {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[this.buffer_size];
        int size = 0;

        // 建立链接
        url = new URL(destUrl);
        httpUrl = (HttpURLConnection) url.openConnection();
        // 连接指定的资源
        httpUrl.connect();
        // 获取网络输入流
        bis = new BufferedInputStream(httpUrl.getInputStream());
        // 建立文件
        fos = new FileOutputStream(fileName);

//        if (this.DEBUG)
//            System.out.println(info+"正在获取链接[" + destUrl + "]的内容...\n将其保存为文件["
//                    + fileName + "]");
        // 保存文件
        while ((size = bis.read(buf)) != -1)
            fos.write(buf, 0, size);

        fos.close();
        bis.close();
        httpUrl.disconnect();
        if (this.DEBUG)
            System.out.println(info+"下载完成!");
        SaveFile.isFinalshed();
    }


    public void run()  {
        try{
            this.saveToFile();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
