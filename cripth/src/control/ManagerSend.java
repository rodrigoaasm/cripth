/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import model.MyMessage;

/**
 *
 * @author Rodrigo Maia
 */
class ManagerSend {
    
    public final static String INIT = "INIT ";
    public final static String INITOK = "INITOK ";
    
    private Socket client;
    private InetAddress ipServer;    
    
    public void first(InetAddress ip, String key, String cab) throws IOException{
        ipServer = ip;
        client = new Socket(ipServer,4505);
        
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        
        out.println(cab + key);
        out.flush();        
        client.close();
    }
    
    public void setAddress(String ip) throws UnknownHostException{
        ipServer = InetAddress.getByName(ip);
    }
    
    public void sendMsg(String msg) throws IOException {
       
    }    
}
