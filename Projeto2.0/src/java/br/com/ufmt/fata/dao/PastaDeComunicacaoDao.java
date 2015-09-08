/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.dao;

import br.com.ufmt.fata.ent.PastaDeComunicacao;
import java.util.List;

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
