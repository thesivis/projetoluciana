    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.dao;

import br.com.ufmt.fata.obj.Complemento;
import java.util.List;

/**
 *
 * @author vicentejr
 */
public interface ComplementoDAO{
    public void inserir(Complemento complemento);
    public void remover(Complemento complemento);
    public List<Complemento> listar();
    public void editar(Complemento complemento);   
}
