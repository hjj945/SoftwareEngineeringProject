package com.example.software_engineeringproject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.util.Log;

public class UdpTest extends Thread{
    //ip地址
    private String ip="84.32.16.105";
    //端口号
    private int port=12345;
    //要发送的信息
    private String Udp_Message="";
    //初始化
    public UdpTest(){

    }
    //获取要发送的信息
    public void get_message(String s){
        this.Udp_Message=s;
    }

    public void send_message() throws IOException {
        Log.e("socket","start sending message");
        InetAddress address=InetAddress.getByName(ip);
        byte[] Udp_Message_bytes=Udp_Message.getBytes();
        DatagramPacket packet =new DatagramPacket(Udp_Message_bytes,Udp_Message_bytes.length,address,port);
        DatagramSocket socket =new DatagramSocket();
        socket.send(packet);
        Log.e("socket","finish sending message");
    }


}
