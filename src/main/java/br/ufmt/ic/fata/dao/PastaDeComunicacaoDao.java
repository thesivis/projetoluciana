/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.ic.fata.dao;

import java.util.List;

import br.ufmt.ic.fata.ent.PastaDeComunicacao;

/**
 *
 * @author vicentejr
 */
public interface PastaDeComunicacaoDao {
    
    public void save(PastaDeComunicacao pastaDeComunicacao);
    public void remove(PastaDeComunicacao pastaDeComunicacao);
    public void update(PastaDeComunicacao pastaDeComunicacao);
    public List<PastaDeComunicacao> list();
}
