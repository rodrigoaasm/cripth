/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;



import cript.MyRsaKey;
import cript.limit.InterfaceMessage;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Contact;
import model.Conversation;


/**
 *
 * @author Rodrigo Maia
 */
public class ControlUi {
    private InterfaceMessage ui;
    private ManagerSend ms;
    private ManagerReceiver mr;
    private Conversation arrConv;
    private MyRsaKey RSAkey;
    private boolean concOk;
    
    /*Contrutor da classe*/
    public ControlUi (){
        
        ui = new InterfaceMessage(this);//Iniciado interface do app 
        ui.setVisible(true);
        
        RSAkey = MyRsaKey.newInstance();
        concOk= false;
        ms = new ManagerSend();
        try {
            mr = new ManagerReceiver(this);
            mr.start();
        } catch (IOException ex) {
            ui.msgError();
        }
    }
    
    public void establishConnection(String ipStr) throws IOException{ 
        InetAddress ip;
        ip = InetAddress.getByName(ipStr);
        ms.first(ip,RSAkey.getPublicKey(),ManagerSend.INIT);
    }
    /*Método responsavel por acionar o envio da mensagem*/
    public void sendMsgReq(String txt){        
        try {
            ms.sendMsg("");
        } catch (Exception ex) {
            Logger.getLogger(ControlUi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initConversation(InetAddress ip,String pk) throws IOException{        
        arrConv = new Conversation(pk,ip);
        ms.first(ip,RSAkey.getPublicKey(),ManagerSend.INITOK);
        concOk=true;
    }
    
    public static void main(String [] args){
       new ControlUi();
    }
}
