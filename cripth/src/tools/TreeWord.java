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
public class TreeWord {   
    
    public final static int ERROR_RECOGNITION = -1;
    public final static int IN_RECOGNITION = -2;    
    public final static int RECOGNIZED = -3;
    
    private NodeTreeWord p;
    private int statusScan;
    private NodeTreeWord lastNode;
    private String [] words;
    
    /**Contrutor da classe**/
    public TreeWord (String dic){    
        NodeTreeWord tP;
        NodeTreeWord newNode = null;
        
        p =  new NodeTreeWord('\0');//Instaciando primeiro nó da arvore 
        lastNode = p;
        statusScan = 0;
        words = dic.split("\n");  //Subdividido o dicionário em palavras      
        
        
        for(String w: words){ //Varrendo todas as palavras   
            int i =0;
            char [] wordsPrimitiveType = w.toCharArray();//Convertendo a String em Vetor de primitivas
            tP = p;
            for( char a : wordsPrimitiveType){//Varredo letras de uma palavra
               //
                try{//Verificando se a letra ja existe na arborecencia, se sim aproveita os nós de palavras similares
                   tP= linearSearch(tP, a);
                }catch(Exception e){//caso não exista adiciona nó com a nova letra
                   tP.addNodeProx((newNode = new NodeTreeWord(a)));      
                   tP = newNode;
                }                
            }
            newNode.setFinal(i++);//Seta id da palavra em nó final
        }
    }
    
    /**Busca nós na lista de nós filhos.**/
    private NodeTreeWord linearSearch(NodeTreeWord no,char key) throws Exception{
        ArrayList<NodeTreeWord> listNo = no.getListProx(); 
        for(NodeTreeWord ntw : listNo){//Verificado a lista de proximos da arvore 
            if(ntw.getLet()==key) return ntw;//se a letra ja existir retorna o nó onde ela está presente
        }
        throw new Exception("Erro 1: Não encontrado");
    }
 
    /**Exibi a estrutura da arvore **/
    @Override
    public String toString(){
        String text = super.toString()+ "\n< #";       
        return this.assistToString(p,text); 
    }
    
    /**Auxilia na contrução da String que contém a estrutura da arvore **/
    private String assistToString(NodeTreeWord node, String text){
        ArrayList<NodeTreeWord> listNode;
        
        while((listNode = node.getListProx()).size()>0){ //Busca por listas de nós filhos não vazias. 
            int i =0;
            text += " < ";
            NodeTreeWord ntw = listNode.get(i); //Varre a lista de nós filhos
            text +=  ntw.getLet()+ " ";
            text = assistToString(ntw, text);//Chamada recursiva.
            i++;
        } 
        text+= " > ";
        return text;
    }    
    
    /*Faz a identificação da palavra de forma integral**/
    public int scanWord(String word){
        
        int tStatusScan = 0;
        for(char c: word.toCharArray()){//Varre as letras da palvra e faz a entrada letra a letra
            tStatusScan = scanSimple(c);
        }
        resetRecognition();
        return tStatusScan;
    }
    
    /*Faz a comparação se uma letra está contida em uma palavra, a entrada da palavra é 
    feita de chamada a chamada*/
    public int scanSimple(char c){     
        try{
            lastNode = linearSearch(lastNode, c);//Busca a letra na lista de nós filhos
            statusScan = IN_RECOGNITION;//Se o nó em questão não for final, defini o estado como "Em Reconhecimento"
        }catch(Exception e){//Caso seja final defini se a palavra é conhecida ou não.   
            
            if(c == ' ' && statusScan == IN_RECOGNITION && lastNode.isFinal()){
                statusScan = RECOGNIZED;
                int id = lastNode.getId();//Se reconhecida a palvra retona o id dela presente no ultimo nó
                lastNode = p;                
                return id;                
            }else{
                statusScan = ERROR_RECOGNITION;
                lastNode = p;//Se a palavra não existir volta a raiz da arvoré
            }
        }
        return statusScan;
    }
    
    /** Reinicia o reconhecimento de palavras*/
    public void resetRecognition(){
        statusScan=ERROR_RECOGNITION;
        lastNode = p;
    }

    /**Retorna o dicionario*/
    public String[] getWords() {
        return words;
    }    
    
}
