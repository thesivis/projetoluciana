/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.dao;

import br.com.ufmt.fata.obj.Verbo;
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
public class JDBCVerboDAO implements VerboDAO{
    
    Connection connection;
    
    public JDBCVerboDAO(){
        //connection = ConnectionFactory.getConnection();
    }

    @Override
    public void inserir(Verbo verbo) {
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = "INSERT INTO verbo (palavra,url,primPessoaPlural,primPessoaSingular,tercPessoaSingular) VALUES"
                    +"(?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setString(1, verbo.getPalavra());
                ps.setString(2, verbo.getUrl());
                ps.setString(3, verbo.getPrimPessoaPlural());
                ps.setString(4, verbo.getPrimPessoaSingular());
                ps.setString(5, verbo.getTercPessoaSingular());
                
                ps.executeUpdate();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCVerboDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir Verbo ", ex);
        }
    }

    @Override
    public void remover(Verbo verbo) {
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = "DELETE FROM verbo WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setInt(1, verbo.getId());
                ps.executeUpdate();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCVerboDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao remover registro de verbo ", ex);
        }
    }

    @Override
    public List<Verbo> listar() {
        List <Verbo> verbos = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            String SQL =    "SELECT * FROM verbo ORDER BY id";
            ResultSet rs;
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                rs = ps.executeQuery();
                while(rs.next()){
                    Verbo verbo = new Verbo();
                    verbo.setId(rs.getInt("id"));
                    verbo.setPalavra(rs.getString("palavra"));
                    verbo.setUrl(rs.getString("url"));
                    verbo.setPrimPessoaPlural(rs.getString("primPessoaPlural"));
                    verbo.setPrimPessoaSingular(rs.getString("primPessoaSingular"));
                    verbo.setTercPessoaSingular(rs.getString("tercPessoaSingular")); 
                    verbos.add(verbo);
                }
            }
            rs.close();
            connection.close();
            return verbos;
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCVerboDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao listar verbos em JDBCVerboDAO ",ex);
        }
    }

    @Override
    public void editar(Verbo verbo) {
         try {
            connection = ConnectionFactory.getConnection();
            String SQL = "UPDATE verbo SET palavra = ?,url = ?,primPessoaPlural = ?,primPessoaSingular = ?,tercPessoaSingular = ? WHERE id = ?";
             try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                 ps.setString(1, verbo.getPalavra());
                 ps.setString(2, verbo.getUrl());
                 ps.setString(3, verbo.getPrimPessoaPlural());
                 ps.setString(4, verbo.getPrimPessoaSingular());
                 ps.setString(5, verbo.getTercPessoaSingular());
                 ps.setInt(6, verbo.getId());
                 
                 ps.executeUpdate();
             }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCVerboDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao editar registro Verbo", ex);
        }
    }

   
    
}
