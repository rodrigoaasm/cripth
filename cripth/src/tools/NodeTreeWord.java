/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo Maia
 */
public class NodeTreeWord {
    private char let;
    private ArrayList<NodeTreeWord> listProx;
    private boolean fim;
    private int id;
   

    public NodeTreeWord(char let) {
        this.let = let;
        listProx = new ArrayList<NodeTreeWord>();
        fim=false;
    }

    public char getLet() {
        return let;
    }

    public void setLet(char let) {
        this.let = let;
    }   
    
    public void addNodeProx(NodeTreeWord ntw) {
        listProx.add(ntw);        
    }

    public ArrayList<NodeTreeWord> getListProx() {
        return listProx;
    }     

    void setFinal(int id) {
       this.fim=true;
       this.id = id;
    }

    public int getId() {
        if(fim) return id;
        else return -1;        
    }

    boolean isFinal() {
       return fim;
    }
}
