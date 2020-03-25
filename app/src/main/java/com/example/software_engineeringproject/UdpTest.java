package com.example.software_engineeringproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

public class UdpTest extends Thread {
    //ip地址
    private String ip = "84.32.16.105";
    //端口号
    private int port = 12345;
    //要发送的信息
    private String Udp_Message = "";

    //初始化
    public UdpTest() {

    }

    //获取要发送的信息
    public void get_message(String s) {
        this.Udp_Message = s;
    }

    public void send_message() throws IOException {
        Log.e("socket", "start sending message");
        InetAddress address = InetAddress.getByName(ip);
        byte[] Udp_Message_bytes = Udp_Message.getBytes();
        DatagramPacket packet = new DatagramPacket(Udp_Message_bytes, Udp_Message_bytes.length, address, port);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        Log.e("socket", "finish sending message");
    }

    @Override
    public void run() {
        super.run();
        try {
            //1.创建监听指定服务器地址以及指定服务器监听的端口号
            Socket socket = new Socket("84.32.16.105", 12345);
            //2.拿到客户端的socket对象的输出流发送给服务器数据
            OutputStream os = socket.getOutputStream();
            //写入要发送给服务器的数据
            os.write(Udp_Message.getBytes());
            os.flush();
            socket.shutdownOutput();
            //拿到socket的输入流，这里存储的是服务器返回的数据
            InputStream is = socket.getInputStream();
            //解析服务器返回的数据
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufReader = new BufferedReader(reader);
            String s = null;
            final StringBuffer sb = new StringBuffer();
            while((s = bufReader.readLine()) != null){
                sb.append(s);
            }
            //3、关闭IO资源（注：实际开发中需要放到finally中）
            bufReader.close();
            reader.close();
            is.close();
            os.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}