/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rodrigo Maia
 */
public class Contact {
    private String name;
    private int codigo;
    private String status;
    private PublicKey pk; 
    
    public Contact(PublicKey pk) throws UnsupportedEncodingException{
        this.pk=pk;
        String t = null; 
        
         System.out.println(this.pk);
        
        byte[] teste = this.pk.getEncoded();
        try {              
            t = new String(teste,"ISO-8859-1");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            try {
                PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(t.getBytes("ISO-8859-1")));
                System.out.println(publicKey);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (InvalidKeySpecException ex) {
                Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}