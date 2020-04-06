package com.example.software_engineeringproject;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class mySocket {
    //socket初始化
    private Socket socket;
    private OutputStream os;
    private InputStream is;
    private BufferedReader bufReader;
    private InputStreamReader reader;
    private String s;

    public mySocket(final String address, final int port)
    {
        new Thread() {
            public void run(){
                super.run();
                try{
                    socket = new Socket(address, port);
                    Log.e("socket","socket created");
                    os = socket.getOutputStream();
                    is = socket.getInputStream();
                    //解析服务器返回的数据
                    reader = new InputStreamReader(is);
                    bufReader = new BufferedReader(reader);
                    s = null;
                    while((s = bufReader.readLine()) != null) {
                        Log.e("socket", s);
                    }
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
                    os.write(msg.getBytes());
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public InputStream receive_msg(){
        return is;
    }

    public void finish_sending(){
        new  Thread(){
            public void run(){
                super.run();
                try {
                    Log.e("socket", "finish sending message");
                    os.write("Finish".getBytes());
                    os.flush();
                    bufReader.close();
                    reader.close();
                    is.close();
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
