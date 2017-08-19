
import mycriprsa.MyRsaKey;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rodrigo Maia
 */
public class TheMain {

    public static void main(String [] args){
        MyRsaKey myRsaKey = MyRsaKey.getInstance();
        myRsaKey.storeKey("Rsa001");
    }
    
}
