/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycriprsa;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo Maia
 */
public class MyRsaKey {

    public final static String ALGORITHM = "RSA";
    private PrivateKey aPrivate;
    private PublicKey aPublic;

    public MyRsaKey(PrivateKey aPrivate, PublicKey aPublic) {
        this.aPrivate = aPrivate;
        this.aPublic = aPublic;
    }    

    /*Instancia um objeto MyRsaKey, e gera uma chave publica e privada*/
    public static MyRsaKey getInstance(){
        KeyPairGenerator keyGen = null;
        try {  
            keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MyRsaKey.class.getName()).log(Level.SEVERE, null, ex);
        }
        keyGen.initialize(1024);//Definindo o tamanho da chave publica    
        KeyPair key = keyGen.generateKeyPair();//gerado as chaves
        
        return (new MyRsaKey(key.getPrivate(),key.getPublic()));
    }
    
    public void storeKey(String namefile){
         //Abrindo arquivo e salvando ultimo codigo gerado
        FileOutputStream fileOutput;       
        try {//Se o houve algum erro ele não grava a informação
            fileOutput = new FileOutputStream(namefile+".public");
            ObjectOutputStream objfileOutput = new ObjectOutputStream(fileOutput);
            objfileOutput.writeBytes(aPublic.toString());
            objfileOutput.flush();
            objfileOutput.close();
        } catch (Exception ex) {
            return;
        }
    }
}
