/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import cript.MyEncrypt;
import cript.MyRsaKey;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import model.Conversation;
import model.MyMessage;

/**
 *
 * @author Rodrigo Maia
 */
class ManagerSend {
    
    public final static String INIT =  "INIT ";
    public final static String INITOK = "INITOK ";
    
    private Socket client;
    private InetAddress ipServer;    
    
    public void first(InetAddress ip, String key, String cab) throws IOException{
        ipServer = ip;
        client = new Socket(ipServer,4505);
        
       
        
        //System.out.print(key);
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
       // BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())); 
        out.writeUTF(cab);
        out.writeUTF(key);
        System.out.println(key);
        out.flush();        
        client.close();
    }
    
    public void setAddress(String ip) throws UnknownHostException{
        ipServer = InetAddress.getByName(ip);
    }

    void sendMsg(MyMessage m, Conversation arrConv) throws Exception {
        ipServer = arrConv.getIp();
        client = new Socket(ipServer,4505);
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            
        byte[][] msgEncrypt = MyEncrypt.encrypt(arrConv.getpKeyCont(),"MSG " + m.getCont());
        String pct = new String(msgEncrypt[1],"UTF-8") + new String(msgEncrypt[0],"UTF-8");
        System.err.println("***" + pct+ "***");
        
        out.print(pct);
        out.flush();
        out.close();
    }
}
