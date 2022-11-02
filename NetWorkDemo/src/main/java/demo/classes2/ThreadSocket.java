package demo.classes2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ThreadSocket {
    public void send(String ip, int port, String url, String sendData){
        try{
            Socket socket = new Socket(ip, port, InetAddress.getByName("127.0.0.1"), 10011);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
            StringBuffer stringBuffer = new StringBuffer();
            // http协议中请求行
            stringBuffer.append("Get" + url + " Http/1.1 \r\n");
            // http协议请求头
            stringBuffer.append("Host:" + ip + "\r\n");
            stringBuffer.append("Connection: Keep-Alive\r\n");
            stringBuffer.append("user-agent: 1601\r\n");
            stringBuffer.append("\r\n");
            outputStreamWriter.write(stringBuffer.toString());
            outputStreamWriter.flush();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ThreadSocket().send("127.0.0.1", 8080, "/", null);
    }
}
