/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Util.Diversos;

/**
 *
 * @author usuario
 */
public class Consumidor {
   private int numero; 
   private String nome;
   private Bairro bairro;
   private int quantKwh;
   private char tipo;
   private String dataV;
   private float precoKwh;
   private boolean atraso;

   public int getNumero() {
       return numero;
   }

   public void setNumero(int numero) {
       this.numero = numero;
   }

   public String getNome() {
       return nome;
   }

   public void setNome(String nome) {
       this.nome = nome;
   }

   public Bairro getBairro() {
	   return bairro;
   }

   public void setBairro(Bairro bairro) {
	   this.bairro = bairro;
   }

   public int getQuantKwh() {
       return quantKwh;
   }

   public void setQuantKwh(int quantKwh) {
       this.quantKwh = quantKwh;
   }

   public char getTipo() {
       return tipo;
   }

   public void setTipo(char tipo) {
       this.tipo = tipo;
   }

    public String getDataV() {
        return dataV;
    }

    public void setDataV(String dataV) {
        this.dataV = dataV;
    }

   public float getPrecoKwh() {
       return precoKwh;
   }

   public void setPrecoKwh(float precoKwh) {
       this.precoKwh = precoKwh;
   }

   public boolean isAtraso() {
       return atraso;
   }

   public void setAtraso(boolean atraso) {
       this.atraso = atraso;
   }

   public float precoReal() {
       float preco = getPrecoKwh() * getQuantKwh();
       
       if (!isAtraso()) //== false
    	   switch(tipo) {
              case 'C' : preco -= preco * 2.01 / 100;
                              break;
              case 'I' : preco -= preco * 2.99 / 100; 
                             break;
              case 'R' :  preco -= preco * 1.09/100;
           } 
       else
           preco += preco * 10 / 100;
       
       return preco;
   }
  

   public String relatorio(){
      String resp = "";
        
      resp = nome + " com o número " + numero + '\n';
      resp += "Localizado no bairro " + bairro.getDescricao(); 
      resp += " com o código " + bairro.getCodigo() + '\n';  
      resp += "Sendo do tipo ";
      resp += (tipo == 'C' ? "Comercial" : tipo == 'I' ? "Industrial" : "Residencial");
      resp += " com a quantidade consumida em kWh(s) de " + quantKwh +'\n';
      resp += "O preço do consumo é de " + 
                    Diversos.doisDigitos(1).format(precoKwh) +'\n';
      resp += "Sendo o preço a pagar de " + 
                    Diversos.doisDigitos(1).format(precoReal()) +'\n'; 
      resp +=(atraso ? "Estando em atraso\n" : "Não estando em atraso\n"); 
      resp += "\n_____________________________________________________________\n\n";
               
       return resp;
    }
    
}
    

