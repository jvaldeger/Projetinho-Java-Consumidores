/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Negocio.Bairro;
import Negocio.Consumidor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoConsumidor implements DaoBasico {

    public DaoConsumidor()  { //cCntructor
        String inst = "CREATE TABLE IF NOT EXISTS Consumidor"
                + " (Numero INT NOT NULL"
                + ", Codigo INT NOT NULL"
                + ", Nome VARCHAR(45) NOT NULL"
                //+ ", Bairro VARCHAR(25) NOT NULL"
                + ", QuantKwh INT NOT NULL"
                + ", Tipo VARCHAR(12) NOT NULL"
                + ", DataV VARCHAR(10) NOT NULL"
                + ", PrecCKwh FLOAT NOT NULL"
                + ", Atraso TINYINT(1) NOT NULL"
                + ", PRIMARY KEY (Numero)"
                + ", CONSTRAINT Codigo FOREIGN KEY (Codigo) REFERENCES Bairro (Codigo)"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
  
        try {
            Connection con =  DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.execute();
            }
            DaoConexao.getInstancia().setCon(con);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
          }
    }

    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Consumidor cC = (Consumidor) o;
        String inst = "Insert Into Consumidor ";
        inst += "(Numero, Codigo, Nome, QuantKwh, Tipo, DataV, PrecoKwh, Atraso) ";
        inst += "values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, cC.getNumero());
                pS.setInt(2, cC.getBairro().getCodigo());
                pS.setString(3, cC.getNome());
               // pS.setString(3, cC.getBairro());
                pS.setInt(4, cC.getQuantKwh());
                pS.setString(5, cC.getTipo() == 'C' ? "Comercial" : cC.getTipo() == 'I' ?
                		"Industrial": "Residencial");
                pS.setString(6, cC.getDataV());
                pS.setFloat(7, cC.getPrecoKwh());
                pS.setBoolean(8, cC.isAtraso());
                pS.execute();
            }
            DaoConexao.getInstancia().setCon(con);
        } catch (SQLException e) {
               result = false;
               throw new RuntimeException(e.getMessage());             
          }
        return (result);
    }

    @Override
    public boolean alterar(Object o) {
        boolean result = true;
        Consumidor cC = (Consumidor) o;
        String inst = "Update Consumidor set Codigo= ?, Nome = ? ";
        inst += "QuantKwh = ?, Tipo = ?, DataV = ?, PrecoKwh = ?, Atraso = ? ";
        inst += "where Numero = ?";
        try {
        	Connection con = DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1,cC.getBairro().getCodigo());
            	pS.setString(2, cC.getNome());
               // pS.setString(2, cC.getBairro());
                pS.setInt(3, cC.getQuantKwh());
                pS.setString(4, cC.getTipo() == 'C' ? "Comercial" : cC.getTipo() == 'I' ?
                		"Industrial" : "Residencial");
                pS.setString(5, cC.getDataV());
                pS.setFloat(6, cC.getPrecoKwh());
                pS.setBoolean(7, cC.isAtraso());
                pS.setInt(8, cC.getNumero());
                pS.execute();
            }
            DaoConexao.getInstancia().setCon(con);
        } catch (SQLException e) {
                    result = false;
            throw new RuntimeException(e.getMessage());
        }
        return (result);
    }

    @Override
    public boolean excluir(Object o) {
        boolean result = true;
        Consumidor cC = (Consumidor) o;
        String inst = "Delete From Consumidor where Numero = ?";
        try {
            Connection cCn = DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = cCn.prepareStatement(inst)) {
                pS.setInt(1, cC.getNumero());
                pS.execute();
            }
            DaoConexao.getInstancia().setCon(cCn);
        } catch (SQLException e) {
            result = false;
            throw new RuntimeException(e.getMessage());
          }
        return (result);
    }

    @Override
    public Object busca(int numero, int nada) {
        String inst = "Select * from Consumidor where Numero = ?";
        Consumidor cC = null; 
        ResultSet rS;
        
        try {
            Connection cCn = DaoConexao.getInstancia().getCon();
            try (PreparedStatement pS = cCn.prepareStatement(inst)) {
                pS.setInt(1, numero);
                rS = pS.executeQuery();
                DaoConexao.getInstancia().setCon(cCn);
                if (rS.next()) {
                    cC = new Consumidor();
                    DaoBairro dB = new DaoBairro();
                    cC.setNumero(rS.getInt("Numero"));
                    Object o = dB.busca(rS.getInt("Codigo"), 0);
                    Bairro b = (Bairro) o;
                    cC.setBairro(b); 
                    cC.setNome(rS.getString("Nome"));
                    //cC.setBairro(rS.getString("Bairro"));
                    cC.setQuantKwh(rS.getInt("QuantKwh"));
                    cC.setTipo(rS.getString("Tipo").charAt(0));
                    cC.setDataV(rS.getString("DataV"));
                    cC.setPrecoKwh(rS.getFloat("PrecoKwh"));
                    cC.setAtraso(rS.getBoolean("Atraso"));
               }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
          }
        return (cC);
    }
    
    @Override
    public List<Object> carrega() {
        String inst = "Select * From Consumidor order by Nome";
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;

        try {
            try (PreparedStatement pS
                    = DaoConexao.getInstancia().getCon().prepareStatement(inst)) {
                rS = pS.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if (rS != null) 
                    while (rS.next()) {
                        o = busca(rS.getInt("Numero"), 0);
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
