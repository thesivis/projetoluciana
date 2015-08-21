/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata.dao;

import br.ufmt.fata.entities.Entity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author raphael
 * @param <T>
 */
public interface DAO<T extends Entity> extends Serializable {

    public boolean salvar(T t);

    public boolean delete(int id);

    public List<T> select();

    public List<T> select(String orderBy);
}
