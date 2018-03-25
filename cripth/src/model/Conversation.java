/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.InetAddress;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 *
 * @author Rodrigo Maia
 */
public class Conversation {
    private ArrayList<MyMessage> listHistory;
    private PublicKey pKeyCont;
    private InetAddress ip;
    
    public Conversation(String pk,InetAddress ip ) {
        listHistory = new ArrayList<MyMessage>();
        this.ip = ip;
    }
    
    public void addMessage(MyMessage m){
        listHistory.add(m);
    }
    
}
