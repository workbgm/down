package com.web;

import java.io.*;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by king on 2017/4/10.
 */
public class Util {
    public static final int URL_TIME = 30000;
    public static final int FILE_TIME = 50000;
    private static final String DEFAULT_ENCODE = "UTF-8";

    public static void log(String log) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH/mm/ss");
        System.out.println(df.format(new Date()) + ":" + log);
    }




    public static void downFile(String url, String path) {
//        url=url.replaceAll("../","");
        Response resultResponse;
        try {
            resultResponse = Jsoup.connect(url).ignoreContentType(true).timeout(Util.FILE_TIME).execute();
            FileOutputStream out = (new FileOutputStream(new java.io.File(path)));
            out.write(resultResponse.bodyAsBytes());
            out.close();
        } catch (IOException e) {
            log("下载文件错误,地址:" + url + "错误信息:" + e.getMessage());
        }
    }


    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 新建目录.
     *
     * @param path 文件路径
     * @throws Exception
     */
    public static void createDirectory(String path) {
        if (isEmpty(path)) {
            return;
        }
        try {
            // 获得文件对象
            File f = new File(path);
            if (!f.exists()) {
                // 如果路径不存在,则创建
                f.mkdirs();
            }
        } catch (Exception e) {
            log("创建目录错误.path=" + path + ";异常:" + e);
        }
    }


    /**
     * 新建文件.
     *
     * @param path 文件路径
     * @throws Exception
     */
    public static void createFile(String path) throws Exception {
        if (isEmpty(path)) {
            return;
        }
        try {
            // 获得文件对象

            File f = new File(path);
            if (f.exists()) {
                return;
            }
            // 如果路径不存在,则创建

            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            f.createNewFile();
        } catch (Exception e) {
            log("创建文件错误.path=" + path + ";异常:" + e);
            throw e;
        }
    }

    /**
     * 保存文件(文件不存在会自动创建).
     *
     * @param path    文件路径
     * @param content 文件内容
     * @throws Exception
     */
    public static void saveFile(String path, String content) throws Exception {
        saveFile(path, content, DEFAULT_ENCODE);
    }

    /**
     * 保存文件(文件不存在会自动创建).
     *
     * @param path     文件路径
     * @param content  文件内容
     * @param encoding 编码(UTF-8/gb2312/...)
     * @throws Exception
     */
    public static void saveFile(String path, String content, String encoding) throws Exception {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bw = null;
        try {
            // 获得文件对象

            File f = new File(path);
            // 默认编码UTF-8

            encoding = (isEmpty(encoding)) ? DEFAULT_ENCODE : encoding;
            // 如果路径不存在,则创建

            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            // 开始保存文件

            fileOutputStream = new FileOutputStream(path);
            bw = new BufferedOutputStream(fileOutputStream);
            bw.write(content.getBytes(encoding));
        } catch (Exception e) {
            log("保存文件错误.path=" + path + ",content=" + content + ",encoding=" + encoding + ";异常:" + e);
            throw e;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e1) {
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e1) {
                }
            }
        }


    }


    /**
     * 将文本文件中的内容读入到buffer中
     * @param buffer buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

    /**
     * 读取文本文件内容
     * @param filePath 文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static String readFile(String filePath)  {
        StringBuffer sb = new StringBuffer();
        try {
            Util.readToBuffer(sb, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 获取文件名，带后缀的
     * @param path
     * @return
     */
    public static String getFileName(String path){
        String fName = path.trim();
        String fileName = fName.substring(fName.lastIndexOf("/")+1);
        int pix=fileName.lastIndexOf("?");
        if(pix<0) {
            return fileName;
        }else{
            fileName=fileName.substring(fileName.lastIndexOf("/")+1,pix);
            return fileName;
        }
    }

    /**
     * 获取文件后缀，比如版本信息
     * @param path
     * @return
     */
    public static String getFilepixName(String path){
        String fName = path.trim();
        String fileName = fName.substring(fName.lastIndexOf("/")+1);
        int pix=fileName.lastIndexOf("?");
        if(pix<0) {
            return "";
        }else{
            fileName=fileName.substring(pix);
            return fileName;
        }
    }

    /**
     * 获取文件后缀，不带.
     * @param path
     * @return
     */
    public static String getFileType(String path){
        String fType = path.trim();
        String fileType = fType.substring(fType.lastIndexOf(".")+1);
        return fileType;
    }

    public static String StringFilter(String str){
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static String getHost(String url){
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)",Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        matcher.find();
        return matcher.group();
    }
}
