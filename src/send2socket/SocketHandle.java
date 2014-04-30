/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send2socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author zeng
 */
public class SocketHandle implements Runnable {

    public static String input = null;
    public static StringBuffer output = null;
    public static String addr = null;
    public static String port = null;

    public SocketHandle() {

    }

    private void CheckValid() {
        if (addr == null) {
            addr = new String("127.0.0.1");
        }
        if (port == null) {
            port = new String("12345");
        }
        if (input == null) {
            input = new String("Empty input !!!");
        }
    }

    @Override
    public void run() {
        CheckValid();

        Socket socket = new Socket();
        InetSocketAddress socketaddr = new InetSocketAddress(addr, new Integer(port));
        int timeout = 5000;
        
        OutputStream ops = null;
        InputStream ips = null;
        try {
            socket.connect(socketaddr, timeout);
            ops = socket.getOutputStream();
            ops.write(new Byte(input));
            socket.close();
        } catch(IOException e){
            System.out.println("IOException!!");
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
