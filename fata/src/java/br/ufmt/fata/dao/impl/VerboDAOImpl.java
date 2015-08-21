/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata.dao.impl;

import br.ufmt.fata.dao.VerboDAO;
import br.ufmt.fata.entities.Verbo;

/**
 *
 * @author raphael
 */
public class VerboDAOImpl extends DAOJPA<Verbo> implements VerboDAO {

    public VerboDAOImpl() {
        super(Verbo.class);
    }

}
