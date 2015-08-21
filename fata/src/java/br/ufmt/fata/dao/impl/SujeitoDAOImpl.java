/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata.dao.impl;

import br.ufmt.fata.dao.SujeitoDAO;
import br.ufmt.fata.entities.Sujeito;

/**
 *
 * @author raphael
 */
public class SujeitoDAOImpl extends DAOJPA<Sujeito> implements SujeitoDAO {

    public SujeitoDAOImpl() {
        super(Sujeito.class);
    }

}
