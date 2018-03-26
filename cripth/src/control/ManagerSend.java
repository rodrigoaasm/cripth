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

    void sendMsg(MyMessage m, Conversation arrConv,MyRsaKey rsakey) throws Exception {
        ipServer = arrConv.getIp();
        client = new Socket(ipServer,4505);
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
            
        byte[][] msgEncrypt = MyEncrypt.encrypt(rsakey.getPublicKey(),"MSG " + m.getCont());
        String pct = new String(msgEncrypt[1],"ISO-8859-1") + new String(msgEncrypt[0],"ISO-8859-1");
        System.err.println(pct);
        
        out.writeUTF(pct);
        out.flush();
        out.close();
    }
}
