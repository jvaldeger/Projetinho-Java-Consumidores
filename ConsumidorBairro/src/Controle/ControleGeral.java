/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Persistencia.DaoBairro;
import Persistencia.DaoBasico;
import Persistencia.DaoConsumidor;
import Persistencia.DaoManutencao;

import java.util.List;

public class ControleGeral implements ControleBasico {
    private DaoBasico dG;
    final String Banco = "BdConsumidor";
    public ControleGeral(int op) { //construtor
        this.dG = op == 1 ? new DaoConsumidor() : 
        	       op == 2 ? new DaoBairro() : null;     
    }
    
    @Override
    public boolean setManipular(Object o, char tarefa) {
        boolean oK = false; 
        if(dG instanceof DaoBasico) 
           oK =  (tarefa == 'A' ? dG.alterar(o) : tarefa == 'E'
                                        ? dG.excluir(o) : dG.incluir(o));       
        return(oK);
    }
    
    @Override
    public Object getBusca(int numero, int nada) {
       Object o = null;
        if(dG instanceof DaoBasico)
          o = dG.busca(numero, 0);
        return (o); 
    }
    
    @Override
    public List<Object> lista() {
        List<Object> lista;
        lista = null;
        if(dG instanceof DaoBasico) 
            lista = dG.carrega();            
        return lista;
    }
    
    public void manutencao () {
        DaoManutencao dM =	new DaoManutencao(Banco); 
    }
}
