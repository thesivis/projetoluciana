/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.dao;

import br.com.ufmt.fata.util.ConnectionFactory;
import br.com.ufmt.fata.obj.Sujeito;
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
public class JDBCSujeitoDAO implements SujeitoDAO{

    Connection connection;
    
    public JDBCSujeitoDAO() {
        
    }
    
    @Override
    public void inserir(Sujeito sujeito) {
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = "INSERT INTO sujeito (palavra,sexo,url,conjugacao) VALUES"
                    +"(?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setString(1, sujeito.getPalavra());
                ps.setString(2, sujeito.getSexo());
                ps.setString(3, sujeito.getUrl());
                ps.setString(4, sujeito.getConjugacao());
                
                ps.executeUpdate();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCSujeitoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir Sujeito ", ex);
        }
    }

    @Override
    public void remover(Sujeito sujeito) {
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = "DELETE FROM sujeito WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setInt(1, sujeito.getId());
                ps.executeUpdate();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCSujeitoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao remover registro de sujeito ", ex);
        }
    }

    @Override
    public List<Sujeito> listar() {
        List <Sujeito> sujeitos = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            String SQL =    "SELECT * FROM sujeito ORDER BY id";
            ResultSet rs;
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                rs = ps.executeQuery();
                while(rs.next()){
                    Sujeito sujeito = new Sujeito();
                    sujeito.setId(rs.getInt("id"));
                    sujeito.setPalavra(rs.getString("palavra"));
                    sujeito.setSexo(rs.getString("sexo"));
                    sujeito.setUrl(rs.getString("url"));
                    sujeito.setConjugacao(rs.getString("conjugacao")); 
                    sujeitos.add(sujeito);
                }
            }
            rs.close();
            connection.close();
            return sujeitos;
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCSujeitoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao listar sujeitos em JDBCSujeitoDAO ",ex);
        }
    }

    @Override
    public void editar(Sujeito sujeito) {
        
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = "UPDATE sujeito SET palavra = ?,sexo = ?,url = ?,conjugacao = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setString(1, sujeito.getPalavra());
                ps.setString(2, sujeito.getSexo());
                ps.setString(3, sujeito.getUrl());
                ps.setString(4, sujeito.getConjugacao());
                ps.setInt(5, sujeito.getId());
                
                ps.executeUpdate();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCSujeitoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao editar registro Sujeito", ex);
        }
        
    }    
     
}
