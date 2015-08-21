/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.dao;

import br.com.ufmt.fata.obj.Complemento;
import br.com.ufmt.fata.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vicentejr
 */
public class JDBCComplementoDAO implements ComplementoDAO{
    
    Connection connection;

    public JDBCComplementoDAO() {
           
    }
  
    @Override
    public void inserir(Complemento complemento) {
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = "INSERT INTO complemento (palavra,sexo,url) VALUES"
                    +"(?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setString(1, complemento.getPalavra());
                ps.setString(2, complemento.getSexo());
                ps.setString(3, complemento.getUrl());
                
                ps.executeUpdate();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCComplementoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir Complemento ", ex);
        }
    }

    @Override
    public void remover(Complemento complemento) {
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = "DELETE FROM complemento WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setInt(1, complemento.getId());
                ps.executeUpdate();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCComplementoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao remover registro de complemento ", ex);
        }
    }

    @Override
    public List<Complemento> listar() {
        List <Complemento> complementos = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            String SQL =    "SELECT * FROM complemento ORDER BY id";
            ResultSet rs;
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                rs = ps.executeQuery();
                while(rs.next()){
                    Complemento complemento = new Complemento();
                    complemento.setId(rs.getInt("id"));
                    complemento.setPalavra(rs.getString("palavra"));
                    complemento.setSexo(rs.getString("sexo"));
                    complemento.setUrl(rs.getString("url")); 
                    complementos.add(complemento);
                }
            }
            rs.close();
            connection.close();
            return complementos;
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCComplementoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao listar complementos em JDBCComplementoDAO ",ex);
        }
    }

    @Override
    public void editar(Complemento complemento) {
         try {
            connection = ConnectionFactory.getConnection();
            String SQL = "UPDATE complemento SET palavra=?,sexo=?,url=? WHERE id = ?";
             try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                 ps.setString(1, complemento.getPalavra());
                 ps.setString(2, complemento.getSexo());
                 ps.setString(3, complemento.getUrl());
                 ps.setInt(4, complemento.getId());
                 
                 ps.executeUpdate();
             }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCComplementoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao editar registro Complemento", ex);
        }
    }
  
}
