/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Rodrigo Maia
 */
public class MyMessage {
    private String cont;
    private int senderUser;
    private int receiverUser;
    private Date sendDate;

    
    public MyMessage(String cont,int receiverUser,int senderUser) {
        this.cont = cont;
        this.receiverUser = receiverUser;
        this.senderUser = senderUser;
        sendDate = new Date();
    }  

    public String getCont() {
        return cont;
    }

    public int getSenderUser() {
        return senderUser;
    }

    public int getReceiverUser() {
        return receiverUser;
    }

    public Date getSendDate() {
        return sendDate;
    }
    
    
    
    
}
