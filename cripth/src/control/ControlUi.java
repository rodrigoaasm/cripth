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
import javax.swing.JOptionPane;
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
            ui.msg("Erro na entrada da rede!", "Não foi possivel inicializar o modulo de receptor de rede ",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void requestConnection(String ipStr) throws IOException{ 
        InetAddress ip;
        ip = InetAddress.getByName(ipStr);
        mr.waitingRespInit();
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
    
    public void initConversation(InetAddress ip,String pk,boolean needInitOk) throws IOException{        
        arrConv = new Conversation(pk,ip);
        if(needInitOk){
            ms.first(ip,RSAkey.getPublicKey(),ManagerSend.INITOK);
        }
        concOk=true;
        System.out.print("Estabelecida conexão.");
        ui.msg("Conexão estabelecida!","Estabelecida com sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String [] args){
       new ControlUi();
    }
}
