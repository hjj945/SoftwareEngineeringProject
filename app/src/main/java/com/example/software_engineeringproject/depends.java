package com.example.software_engineeringproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*************************************************************************************
 * 用于处理发送与接受的的信息
 * 所有信息统一生成为.json格式，方便后续的解析工作
 * 信息的格式如下(可以选择填入，未填入的部分设置为缺省值null)：
 * Create_msg_for_client(
 *      int function_num     功能序号 (1-网络聊天，2-五子棋，3-...,...)
 *      String type，        指令名称 (自行定义)
 *      String id,           用户账号
 *      String target_id，   传送目标
 *      String data          传输数据 (data内的数据也可以转为.json格式)
 * )
 *************************************************************************************/

public class depends {
    public depends(){

    }

    public String Create_msg_for_client(int function_num,String type,String id, String target_id,String data) throws JSONException {
        JSONObject msg=new JSONObject();
        msg.put("function_num",function_num);
        msg.put("type",type);
        msg.put("id",id);
        msg.put("target_id",target_id);
        msg.put("data",data);
        String s=msg.toString();
        return s;
    }

    public String Get_info_from_message(String message,String item){
        String s="";
        return s;
    }
}




