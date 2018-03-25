/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.InetAddress;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            pKeyCont = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pk.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Conversation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Conversation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addMessage(MyMessage m){
        listHistory.add(m);
    }

    public PublicKey getpKeyCont() {
        return pKeyCont;
    }

    public InetAddress getIp() {
        return ip;
    } 
}
