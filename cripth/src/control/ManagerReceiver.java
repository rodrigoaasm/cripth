/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.TreeWord;

/**
 *
 * @author Rodrigo Maia
 */
public class ManagerReceiver extends Thread{
    private ServerSocket sPeer;
    private ControlUi ctr;
    private TreeWord tWord;
    private InetAddress listCont;
    private boolean waitRespInit;

    public ManagerReceiver(ControlUi ctr) throws IOException {
        sPeer = new ServerSocket(4505);
        this.ctr=ctr;
        tWord = new TreeWord("INIT\nINITOK\nMSG\nCLOSEC");//Instaciado leitor de protocolo mySCRM
        listCont=null;
        waitRespInit=false;
    }
    
    void waitingRespInit() {
        waitRespInit=true;
    }
    
    @Override
    public void run(){
        Socket cPeer = null;
        Scanner scanner = null;
        String dataSem = "";
        
        while(true){
            try {
                cPeer = sPeer.accept();
            } catch (IOException ex) {
                Logger.getLogger(ManagerReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                scanner = new Scanner(cPeer.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(ManagerReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }                
            
            dataSem = "";
            while (scanner.hasNextLine()) {//Recebendo semantica do mySCRM
                dataSem += scanner.nextLine();
            }
            
            String [] sem = dataSem.split(" ");//Dividido semántica
            
            sem[0]+=" ";  
            System.out.println(tWord.scanWord(sem[0]));
            switch(tWord.scanWord(sem[0])){//Intepretando comando mySCRM                
                case 0:{
                    if(listCont==null && !waitRespInit){
                        try {                            
                            ctr.initConversation(cPeer.getInetAddress(),sem[1],true);
                            listCont = cPeer.getInetAddress();
                        } catch (IOException ex) {
                            Logger.getLogger(ManagerReceiver.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                    }else{
                        try {
                        cPeer.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ManagerReceiver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }                    
                    break;
                    
                case 1:{
                    if(listCont==null && waitRespInit){
                        try {
                            ctr.initConversation(cPeer.getInetAddress(),sem[1],false);
                            listCont = cPeer.getInetAddress();
                        } catch (IOException ex) {
                            Logger.getLogger(ManagerReceiver.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                    }
                    break;
                }    
                case 2:
                    break;
                    
                case 3:
                    break;
                
                default:break;
            }
            
        }
    }


    
    
}
