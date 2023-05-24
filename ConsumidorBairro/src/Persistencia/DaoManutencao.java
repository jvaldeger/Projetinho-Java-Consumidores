package Persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DaoManutencao {
     
	public DaoManutencao(String Banco)  { //contructor
        String inst = "DROP DATABASE IF EXISTS " + Banco;
  
        try {
        	  try (PreparedStatement pS
                     = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
        		  pS.execute();
            }
        	DaoConexao.setInstancia(null);//getInstancia().setCon(null);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
          }
    }
}

