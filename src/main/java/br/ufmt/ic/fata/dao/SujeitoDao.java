/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.ic.fata.dao;

import java.util.List;

import br.ufmt.ic.fata.ent.Sujeito;

/**
 *
 * @author vicentejr
 */
public interface SujeitoDao {
    
    public void save(Sujeito sujeito);
    public void remove(Sujeito sujeito);
    public void update(Sujeito sujeito);
    public List<Sujeito> list();
    
}
