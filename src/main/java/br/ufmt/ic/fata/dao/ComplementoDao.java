/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.ic.fata.dao;

import java.util.List;

import br.ufmt.ic.fata.ent.Complemento;

/**
 *
 * @author vicentejr
 */
public interface ComplementoDao {
    
    public void save(Complemento complemento);
    public void remove(Complemento complemento);
    public void update(Complemento complemento);
    public List<Complemento> list();
    
}
