
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import mycriprsa.MyEncrypt;
import mycriprsa.MyRsaKey;
 

/**
 *
 * @author Rodrigo Maia
 */
public class TheMain {

    public static void main(String [] args){
       /* MyRsaKey rsa = MyRsaKey.newInstance();
        rsa.storePrivateKey();
        rsa.storePublicKey();*/
        
        PublicKey pk = MyRsaKey.loadInstancePublicKey();
        PrivateKey prk = MyRsaKey.loadInstancePrivateKey();
        byte[][] text =null;
        
        try {
            text = MyEncrypt.encode(pk,"rodrigo aparecido silva maia");
        } catch (Exception ex) {
            Logger.getLogger(TheMain.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        
         try {
            System.out.println(new String(text[0],"ISO-8859-1"));
            System.out.println(new String(text[1],"ISO-8859-1"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TheMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            text[0] = MyEncrypt.Decode(prk, text[0],text[1]);
        } catch (Exception ex) {
            Logger.getLogger(TheMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println(new String(text[0],"ISO-8859-1"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TheMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
