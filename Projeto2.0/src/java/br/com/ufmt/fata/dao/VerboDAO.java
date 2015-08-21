/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.dao;

import br.com.ufmt.fata.obj.Verbo;
import java.util.List;

/**
 *
 * @author vicentejr
 */
public interface VerboDAO {
    public void inserir(Verbo verbo);
    public void remover(Verbo verbo);
    public List<Verbo> listar();
    public void editar(Verbo verbo); 
    
}
