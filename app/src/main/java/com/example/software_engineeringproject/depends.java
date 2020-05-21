package com.example.software_engineeringproject;

import android.util.Log;

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

    public String function(String s){
       char[] t=s.toCharArray();
       String result="";
        for(int i=0;i<s.length();i++){
            if (t[i]=='[' || t[i]==']'){

            }else if(t[i]==','){
                result+='\n';
            }else {
                result+=t[i];
            }
        }
        Log.e("function: ",result );
        return result;
    }

    public String show_message(String s){
        String result="";
        char[] t=s.toCharArray();
        int flag=0;
        for(int i=0;i<s.length();i++){
            if (t[i]=='[' || t[i]==']'||t[i]==')'){

            }else if(t[i]=='('){
                result+="From";
            }else if(t[i]==','&&flag==0){
                result+=":";
                flag=1;
            }else if(t[i]==','&&flag==1){
                result+='\n';
                flag=0;
            }else {
                result+=t[i];
            }
        }
        return result;
    }

}




