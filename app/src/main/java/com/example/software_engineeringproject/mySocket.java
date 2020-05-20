package com.example.software_engineeringproject;
/************************************************************************************************
 * 该类功能是作为网络传输接口
 * 1.初始化：mySocket xxx = new mySocket(address,port) address设置为84.32.16.105,port 设置为12345
 * 2.消息发送函数 send_msg. 使用方法：xxx.send_msg(message); message为需要发送的信息，类型为String
 * 3.消息接收函数 receive_msg 消息接收函数，返回一个InputStream对象。以下为示例：
 *                             is=mysocket.receive_msg();     //mysocket为之前创建的socket对象，此处接收inputstream对象is
 *                             //解析服务器返回的数据
 *                             reader = new InputStreamReader(is);
 *                             bufReader = new BufferedReader(reader);
 *                             s = null;
 *                             while((s = bufReader.readLine()) != null) {
 *                                 Log.e("socket", s);
 *                             }
 * 4.结束函数 finish_sending.使用方法：xxx.finish_sending(); 注销socket对象，停止发送与接收消息
 */

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class mySocket {
    //socket初始化
    private Socket socket;
    private OutputStream os;
    private InputStream is;
    private BufferedReader bufReader;
    private InputStreamReader reader;
    private String s;
    public String id;
    public String target_id;
    public mySocket()
    {
        new Thread() {
            public void run(){
                super.run();
                try{
                    String host="84.32.16.106";
                    //String host=InetAddress.getAllByName("iamhwj.f3322.net").toString();
                    socket = new Socket(host,12345);
                    Log.e("socket","socket created");
                    os = socket.getOutputStream();
                    is = socket.getInputStream();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void send_msg(final String msg){
        new Thread() {
            public void run(){
                super.run();
                try{
                    Log.e("socket","sending message");
                    Log.e( "socket sending ",msg );
                    os.write(msg.getBytes());
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public InputStream receive_msg() throws IOException {
        return is;
    }

    public void finish_sending(){
        new  Thread(){
            public void run(){
                super.run();
                try {
                    Log.e("socket", "finish sending message");
                    os.write("{'type':'Finish'}".getBytes());
                    os.flush();
                    /*
                    bufReader.close();
                    reader.close();
                    is.close();
                     */
                    os.close();
                    socket.shutdownOutput();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
