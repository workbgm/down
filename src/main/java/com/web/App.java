package com.web;

import com.houdun.HouDunService;
import com.maizi.MaiZiService;
import com.muke.MuKeService;

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
//        HouDunService hds=new HouDunService("http://www.houdunren.com/houdunren18_60_.html");
        //MaiZiService mz= new MaiZiService("http://www.maiziedu.com/course/904-13317/");
        String jsonString = "[{\"vid\":\"14903\",\"name\":\"1-1提示面板\",\"url\":[\"http://v3.mukewang.com/59140087b3fee3df768b45c0/L.mp4\",\"http://v3.mukewang.com/59140087b3fee3df768b45c0/M.mp4\",\"http://v3.mukewang.com/59140087b3fee3df768b45c0/H.mp4\"]},{\"vid\":\"14904\",\"name\":\"1-2下拉面板\",\"url\":[\"http://v3.mukewang.com/59140094b3fee39c768b45cb/L.mp4\",\"http://v3.mukewang.com/59140094b3fee39c768b45cb/M.mp4\",\"http://v3.mukewang.com/59140094b3fee39c768b45cb/H.mp4\"]},{\"vid\":\"14905\",\"name\":\"1-3表格\",\"url\":[\"http://v3.mukewang.com/591400a0b3fee3be768b45c3/L.mp4\",\"http://v3.mukewang.com/591400a0b3fee3be768b45c3/M.mp4\",\"http://v3.mukewang.com/591400a0b3fee3be768b45c3/H.mp4\"]},{\"vid\":\"14906\",\"name\":\"1-4模态框\",\"url\":[\"http://v3.mukewang.com/591400acb3fee3c6768b45eb/L.mp4\",\"http://v3.mukewang.com/591400acb3fee3c6768b45eb/M.mp4\",\"http://v3.mukewang.com/591400acb3fee3c6768b45eb/H.mp4\"]},{\"vid\":\"14907\",\"name\":\"1-5选项卡\",\"url\":[\"http://v3.mukewang.com/591400b6b3fee382768b45ea/L.mp4\",\"http://v3.mukewang.com/591400b6b3fee382768b45ea/M.mp4\",\"http://v3.mukewang.com/591400b6b3fee382768b45ea/H.mp4\"]},{\"vid\":\"14908\",\"name\":\"1-6可折叠面板\",\"url\":[\"http://v3.mukewang.com/591400bfb3fee38f768b45eb/L.mp4\",\"http://v3.mukewang.com/591400bfb3fee38f768b45eb/M.mp4\",\"http://v3.mukewang.com/591400bfb3fee38f768b45eb/H.mp4\"]},{\"vid\":\"14909\",\"name\":\"1-7多媒体对象容器\",\"url\":[\"http://v3.mukewang.com/591400c9b3fee38c768b45e1/L.mp4\",\"http://v3.mukewang.com/591400c9b3fee38c768b45e1/M.mp4\",\"http://v3.mukewang.com/591400c9b3fee38c768b45e1/H.mp4\"]},{\"vid\":\"14910\",\"name\":\"1-8可隐藏菜单\",\"url\":[\"http://v3.mukewang.com/591400d6b3fee389768b45da/L.mp4\",\"http://v3.mukewang.com/591400d6b3fee389768b45da/M.mp4\",\"http://v3.mukewang.com/591400d6b3fee389768b45da/H.mp4\"]},{\"vid\":\"14911\",\"name\":\"2-1按钮的使用\",\"url\":[\"http://v3.mukewang.com/59140140b3fee3a0768b460a/L.mp4\",\"http://v3.mukewang.com/59140140b3fee3a0768b460a/M.mp4\",\"http://v3.mukewang.com/59140140b3fee3a0768b460a/H.mp4\"]},{\"vid\":\"14912\",\"name\":\"2-2滑块组件\",\"url\":[\"http://v3.mukewang.com/5914014bb3fee3b7768b460d/L.mp4\",\"http://v3.mukewang.com/5914014bb3fee3b7768b460d/M.mp4\",\"http://v3.mukewang.com/5914014bb3fee3b7768b460d/H.mp4\"]},{\"vid\":\"14913\",\"name\":\"2-3开关组件\",\"url\":[\"http://v3.mukewang.com/59140156b3fee389768b45ea/L.mp4\",\"http://v3.mukewang.com/59140156b3fee389768b45ea/M.mp4\",\"http://v3.mukewang.com/59140156b3fee389768b45ea/H.mp4\"]},{\"vid\":\"14914\",\"name\":\"3-1表单验证组件\",\"url\":[\"http://v3.mukewang.com/5914018bb3fee3ce768b4670/L.mp4\",\"http://v3.mukewang.com/5914018bb3fee3ce768b4670/M.mp4\",\"http://v3.mukewang.com/5914018bb3fee3ce768b4670/H.mp4\"]},{\"vid\":\"14915\",\"name\":\"3-2equalizer\",\"url\":[\"http://v3.mukewang.com/59140199b3fee38d768b45da/L.mp4\",\"http://v3.mukewang.com/59140199b3fee38d768b45da/M.mp4\",\"http://v3.mukewang.com/59140199b3fee38d768b45da/H.mp4\"]},{\"vid\":\"14916\",\"name\":\"3-3interchange插件\",\"url\":[\"http://v3.mukewang.com/591401a4b3fee395768b45ea/L.mp4\",\"http://v3.mukewang.com/591401a4b3fee395768b45ea/M.mp4\",\"http://v3.mukewang.com/591401a4b3fee395768b45ea/H.mp4\"]},{\"vid\":\"14917\",\"name\":\"3-4toggler\",\"url\":[\"http://v3.mukewang.com/591401b2b3fee39e768b460a/L.mp4\",\"http://v3.mukewang.com/591401b2b3fee39e768b460a/M.mp4\",\"http://v3.mukewang.com/591401b2b3fee39e768b460a/H.mp4\"]},{\"vid\":\"14918\",\"name\":\"3-5sticky插件\",\"url\":[\"http://v3.mukewang.com/591401bcb3fee3d6768b463b/L.mp4\",\"http://v3.mukewang.com/591401bcb3fee3d6768b463b/M.mp4\",\"http://v3.mukewang.com/591401bcb3fee3d6768b463b/H.mp4\"]},{\"vid\":\"14919\",\"name\":\"4-1Sass&Foundation\",\"url\":[\"http://v3.mukewang.com/591401e0b3fee393768b461b/L.mp4\",\"http://v3.mukewang.com/591401e0b3fee393768b461b/M.mp4\",\"http://v3.mukewang.com/591401e0b3fee393768b461b/H.mp4\"]},{\"vid\":\"14920\",\"name\":\"5-1模板，主题和样板模板组合\",\"url\":[\"http://v3.mukewang.com/591401fdb3fee3a6768b461a/L.mp4\",\"http://v3.mukewang.com/591401fdb3fee3a6768b461a/M.mp4\",\"http://v3.mukewang.com/591401fdb3fee3a6768b461a/H.mp4\"]},{\"vid\":\"14921\",\"name\":\"5-2MotionUI\",\"url\":[\"http://v3.mukewang.com/5914020cb3fee386768b45bc/L.mp4\",\"http://v3.mukewang.com/5914020cb3fee386768b45bc/M.mp4\",\"http://v3.mukewang.com/5914020cb3fee386768b45bc/H.mp4\"]}]";
        String projectName="UI框架foundation6入门（下）";
        MuKeService  mk = new MuKeService(projectName,jsonString);
    }

}
