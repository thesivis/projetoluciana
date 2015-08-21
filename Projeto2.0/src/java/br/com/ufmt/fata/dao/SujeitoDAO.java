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
public interface SujeitoDAO { 
    public void inserir(Sujeito sujeito);
    public void remover(Sujeito sujeito);
    public List<Sujeito> listar();
    public Sujeito listaById(int id);
    public void editar(Sujeito sujeito); 
}
