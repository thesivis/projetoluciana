/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata.dao.impl;

import br.ufmt.fata.dao.ComplementoDAO;
import br.ufmt.fata.entities.Complemento;

/**
 *
 * @author raphael
 */
public class ComplementoDAOImpl extends DAOJPA<Complemento> implements ComplementoDAO {

    public ComplementoDAOImpl() {
        super(Complemento.class);
    }

}
