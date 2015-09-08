/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.dao;

import br.com.ufmt.fata.ent.Verbo;
import java.util.List;

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
