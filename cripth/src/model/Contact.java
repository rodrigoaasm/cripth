/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.security.PublicKey;


/**
 *
 * @author Rodrigo Maia
 */
public class Contact {
    private String name;
    private int codigo;
    private String status;
    private PublicKey pk; 
    
    public Contact(PublicKey pk){
        this.pk=pk;
         System.out.println(this.pk);
        
     /*   byte[] teste = this.pk.getEncoded();
        try {
            System.out.println(new String(teste,"ISO-8859-1"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        try {
            try {
                PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(teste));
                System.out.println(publicKey);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (InvalidKeySpecException ex) {
                Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
        }*/
      
    }
}