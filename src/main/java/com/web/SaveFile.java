package com.web;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wuweimei on 2017/6/9.
 */
public class SaveFile {
    public final static boolean DEBUG = true; // 调试用
    private static int BUFFER_SIZE = 102400; // 缓冲区大小(缓冲区越大下载的越快,但是要根据自己的服务器配置)
    private Vector vDownLoad = new Vector(); // URL列表
    private Vector vFileList = new Vector(); // 下载后的保存文件名列表

    private static int totalCount=0;

    /**
     * 构造方法
     */
    public SaveFile() {
    }

    /**
     * 清除下载列表
     */
    public void resetList() {
        vDownLoad.clear();
        vFileList.clear();
    }

    /**
     * 增加下载列表项
     *
     * @param url
     *            String
     * @param filename
     *            String
     */

    public void addItem(String url, String filename) {
        vDownLoad.add(url);
        vFileList.add(filename);
    }

    /**
     * 根据列表下载资源
     */
    public void downLoadByList() {
        String url = null;
        String filename = null;

        // 按列表顺序保存资源
        int size = vDownLoad.size();
        for (int i = 0; i < size; i++) {
            url = (String) vDownLoad.get(i);
            filename = (String) vFileList.get(i);

            try {
                String info = "【"+String.valueOf(i+1)+" of "+String.valueOf(size)+"】";
                saveToFile(url, filename,info);
            } catch (IOException err) {
                if (DEBUG) {
                    System.out.println("资源[" + url + "]下载失败!!!");
                }
            }
        }

        if (DEBUG) {
            System.out.println("下载完成!!!");
        }
    }

    public void downLoadByThreadList(int count){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(count);
        String url = null;
        String filename = null;

        // 按列表顺序保存资源
        int size = vDownLoad.size();
        totalCount = size;
        for (int i = 0; i < size; i++) {
            url = (String) vDownLoad.get(i);
            filename = (String) vFileList.get(i);

            try {
                int current = i+1;
                downFileThread dft=new downFileThread(url,filename,current,size,BUFFER_SIZE);
                fixedThreadPool.execute(dft);
            } catch (Exception err) {
                if (DEBUG) {
                    System.out.println("资源[" + url + "]下载失败!!!");
                }
            }finally {
//                fixedThreadPool.shutdown();
            }
        }
    }

    /**
     * 将HTTP资源另存为文件
     *
     * @param destUrl
     *            String
     * @param fileName
     *            String
     * @throws Exception
     */
    public void saveToFile(String destUrl, String fileName,String info) throws IOException {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
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

        if (this.DEBUG)
            System.out.println(info+"正在获取链接[" + destUrl + "]的内容...\n将其保存为文件["
                    + fileName + "]");
        // 保存文件
        while ((size = bis.read(buf)) != -1)
            fos.write(buf, 0, size);

        fos.close();
        bis.close();
        httpUrl.disconnect();
    }

    /**
     * 将HTTP资源另存为文件
     *
     * @param destUrl
     *            String
     * @param fileName
     *            String
     * @throws Exception
     */
    public void saveToFile2(String destUrl, String fileName) throws IOException {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        // 建立链接
        url = new URL(destUrl);
        httpUrl = (HttpURLConnection) url.openConnection();

        // String authString = "username" + ":" + "password";
        String authString = "50301" + ":" + "88888888";
        String auth = "Basic "
                + new sun.misc.BASE64Encoder().encode(authString.getBytes());
        httpUrl.setRequestProperty("Proxy-Authorization", auth);

        // 连接指定的资源
        httpUrl.connect();
        // 获取网络输入流
        bis = new BufferedInputStream(httpUrl.getInputStream());
        // 建立文件
        fos = new FileOutputStream(fileName);

        if (this.DEBUG)
            System.out.println("正在获取链接[" + destUrl + "]的内容...\n将其保存为文件["
                    + fileName + "]");
        // 保存文件
        while ((size = bis.read(buf)) != -1)
            fos.write(buf, 0, size);

        fos.close();
        bis.close();
        httpUrl.disconnect();
    }

    /**
     * 设置代理服务器
     *
     * @param proxy
     *            String
     * @param proxyPort
     *            String
     */
    public void setProxyServer(String proxy, String proxyPort) {
        // 设置代理服务器
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("proxyHost", proxy);
        System.getProperties().put("proxyPort", proxyPort);
    }

    public static void  isFinalshed(){
        --totalCount;
        if(totalCount==0){
            System.out.println("-------所有下载任务完成!-------");
            System.exit(0);
        }else{
            System.out.println("还剩【"+String.valueOf(totalCount)+"】个下载任务");
        }
    }

}
