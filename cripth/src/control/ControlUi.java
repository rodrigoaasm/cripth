/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;



import cript.MyEncrypt;
import cript.MyRsaKey;
import cript.limit.InterfaceMessage;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Conversation;
import model.MyMessage;
import tools.TreeWord;


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
    private TreeWord tWord;
    
    /*Contrutor da classe*/
    public ControlUi (){
        
        ui = new InterfaceMessage(this);//Iniciado interface do app 
        ui.setVisible(true);
        
        RSAkey = MyRsaKey.newInstance();
        concOk= false;
        ms = new ManagerSend();
        tWord = new TreeWord("INIT\nINITOK\nMSG\nCLOSEC");//Instaciado leitor de protocolo mySCRM
        try {
            mr = new ManagerReceiver(this,tWord);
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
        ms.first(ip,RSAkey.getPublicKeyStringMode(),ManagerSend.INIT);        
    }
    /*Método responsavel por acionar o envio da mensagem*/
    public void sendMsgReq(String txt){
        MyMessage m = new MyMessage(txt,0,0);
//        arrConv.addMessage(m);
        try {
            ms.sendMsg(m,arrConv);
        } catch (Exception ex) {
            Logger.getLogger(ControlUi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initConversation(InetAddress ip,String pk,boolean needInitOk) throws IOException{        
        arrConv = new Conversation(pk,ip);
        if(needInitOk){
            ms.first(ip,RSAkey.getPublicKeyStringMode(),ManagerSend.INITOK);
        }
        concOk=true;
        ui.msg("Conexão estabelecida!","Estabelecida com sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String [] args){
       new ControlUi();
    }

    void toAnalyzePossibleMessage(String dataSem) {        
        String keyAES = dataSem.substring(0, 255);
        String cont = dataSem.substring(256);
        try {            
            cont = new String( MyEncrypt.decrypt(RSAkey.getPrivateKey(),
                    cont.getBytes(),keyAES.getBytes()),"ISO-8859-1");
        } catch (Exception ex) {
            Logger.getLogger(ControlUi.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(cont);
        String[] subCont = cont.split(" ");
        
        switch(tWord.scanWord(subCont[0])){
            case 2:{
                System.out.println(subCont[1]);
            }
            
            default:
                break;
        }
    }
}
