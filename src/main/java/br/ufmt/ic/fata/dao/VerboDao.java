/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.ic.fata.dao;

import java.util.List;

import br.ufmt.ic.fata.ent.Verbo;

/**
 *
 * @author vicentejr
 */
public interface VerboDao {
    
   public void save(Verbo verbo);
    public void remove(Verbo verbo);
    public void update(Verbo verbo);
    public List<Verbo> list();
    
}
