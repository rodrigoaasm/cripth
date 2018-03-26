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

    public ManagerReceiver(ControlUi ctr,TreeWord tWord) throws IOException {
        sPeer = new ServerSocket(4505);
        this.ctr=ctr;
        this.tWord = tWord;       
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
            
            String [] sem = dataSem.split(" ",2);//Dividido semántica
            System.out.println("--------Recebido------\n" + dataSem +"\n-------------------");
            sem[0]+=" ";            
            switch(tWord.scanWord(sem[0])){//Intepretando comando mySCRM                
                case 0:{//Recebendo um pedido de conexão
                    if(listCont==null && !waitRespInit){/*Se o sistema não estiver esperando
                        uma resposta de conexão e não tiver nenhuma conexão ativa*/
                        try {                            
                            ctr.initConversation(cPeer.getInetAddress(),sem[1],true);//Inicia uma conversão e retorna positivo
                            listCont = cPeer.getInetAddress();
                        } catch (IOException ex) {
                            Logger.getLogger(ManagerReceiver.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                    }else{//fecha conexão sem resposta
                        try {
                        cPeer.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ManagerReceiver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }                    
                    break;
                    
                case 1:{//Recebendo confirmação de conexão
                    if(listCont==null && waitRespInit){
                        try {
                            ctr.initConversation(cPeer.getInetAddress(),sem[1],false);//Inicia conversa 
                            listCont = cPeer.getInetAddress();
                        } catch (IOException ex) {
                            Logger.getLogger(ManagerReceiver.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                    }
                    break;
                }    
                
                default:{
                    if(listCont == cPeer.getInetAddress()){
                        ctr.toAnalyzePossibleMessage(dataSem);
                    }
                }
                    break;
            }
            
        }
    }


    
    
}
