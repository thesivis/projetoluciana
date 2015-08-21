/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.util;

import br.com.ufmt.fata.dao.JDBCSujeitoDAO;
import br.com.ufmt.fata.dao.SujeitoDAO;

/**
 *
 * @author vicentejr
 */
public class DAOFactory {
    public static SujeitoDAO createSujeitoDAO(){
        return new JDBCSujeitoDAO();
    }
    
}
