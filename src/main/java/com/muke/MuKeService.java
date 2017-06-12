package com.muke;
import com.alibaba.fastjson.*;
import com.web.SaveFile;
import com.web.Util;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuweimei on 2017/6/9.
 */
public class MuKeService {
    protected  String downFile="/Volumes/work/down/";
    protected  String downMir = "";
    protected  String jsonString = "";
    protected  List<MukeBean> mbs=new ArrayList<MukeBean>();

    public MuKeService(String projectName,String jsonString) {
        try {
            this.downMir = Util.StringFilter(projectName);
            this.jsonString = jsonString;
            JSONArray array = JSON.parseArray(this.jsonString);
            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                MukeBean mb = new MukeBean(
                        jsonObject.getString("vid"),
                        jsonObject.getString("name"),
                        jsonObject.getJSONArray("url")
                );
                mbs.add(mb);
            }
            this.down();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected  void down() throws Exception {
        SaveFile sf = new SaveFile();
        String path=this.downFile+this.downMir+"/";
        for(int i=0;i<mbs.size();i++){
            MukeBean mb=mbs.get(i);
            String type=Util.getFileType(mb.getUrl());
            String fullPath=path+mb.getName()+"."+type;
            Util.createFile(fullPath);
            sf.addItem(mb.getUrl(),fullPath);
        }
        sf.downLoadByList();
    }


}
