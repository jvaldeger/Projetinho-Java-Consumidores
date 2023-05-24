package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Negocio.Bairro;

public class DaoBairro implements DaoBasico {

    public DaoBairro()  { //contructor
        String inst = "CREATE TABLE IF NOT EXISTS Bairro"
                + " (Codigo INT NOT NULL"
                + ", Descricao VARCHAR(45) NOT NULL"
                + ", PRIMARY KEY (Codigo)"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
  
        try {
        	try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                pS.execute();
                 
             }
        	DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
          }
    }

    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Bairro b = (Bairro) o;
        String inst = "Insert Into Bairro (Codigo, Descricao) values(?, ?)";
        try {
        	try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                    pS.setInt(1, b.getCodigo());
                    pS.setString(2, b.getDescricao());
                    pS.execute();
              }
          DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
          } catch (SQLException e) {
               result = false;
               throw new RuntimeException(e.getMessage());             
            }
        return (result);
    }

    @Override
    public boolean alterar(Object o) {
        boolean result = true;
        Bairro b = (Bairro) o;
        String inst = "Update Bairro set Nome = ? where Numero = ?";
        try {
        	  try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
            	    pS.setString(1, b.getDescricao());
                   pS.setInt(2, b.getCodigo());
                   pS.execute();
               }    
        	  DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
        } catch (SQLException e) {
                    result = false;
                   throw new RuntimeException(e.getMessage());
          }
       return (result);
    }

    @Override
    public boolean excluir(Object o) {
        boolean result = true;
        Bairro b = (Bairro) o;
        String inst = "Delete From Bairro where Codigo = ?";
        try {
        	  try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                    pS.setInt(1, b.getCodigo());
                   pS.execute();
            }
          DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
        } catch (SQLException e) {
              result = false;
              throw new RuntimeException(e.getMessage());
          }
        return (result);
    }

    @Override
    public Object busca(int codigo, int nada) {
        String inst = "Select * from Bairro where Codigo = ?";
        Bairro b = null; 
        ResultSet rS;
        
        try {
        	  try (PreparedStatement pS
                     = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                     pS.setInt(1, codigo);
                     rS = pS.executeQuery();
                     DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                     if (rS.next()) {
                         b = new Bairro();
                         b.setCodigo(rS.getInt("Codigo"));
                         b.setDescricao(rS.getString("Descricao"));
                     }
              }
        } catch (SQLException e) {
              throw new RuntimeException(e.getMessage());
          }
        return (b);
    }
    
    @Override
    public List<Object> carrega() {
        String inst = "Select * From Bairro order by Descricao";
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;

        try {
            try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                    rS = pS.executeQuery(inst);
                    DaoConexao.getInstancia().setCon
                                                             (DaoConexao.getInstancia().getCon());
                    if (rS != null) 
                        while (rS.next()) {
                        o = busca(rS.getInt("Codigo"), 0);
                        lista.add(o);
                    }               
                   pS.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return (lista);
    }

}
