/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.dao;

import br.com.ufmt.fata.obj.Sujeito;
import java.util.List;

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
