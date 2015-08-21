/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata.dao.impl;

import br.ufmt.fata.dao.DAO;
import br.ufmt.fata.entities.Entity;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author raphael
 * @param <T>
 */
public class DAOJPA<T extends Entity> implements DAO<T> {

    @Inject
    private EntityManager em;
    private final Class classe;

    public DAOJPA(Class classe) {
        this.classe = classe;
    }

    @Override
    public boolean salvar(T t) {
        em.getTransaction().begin();
        if (t.getId() == null) {
            em.persist(t);
        } else {
            em.merge(t);
        }
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(int id) {
        em.getTransaction().begin();
        Object o = em.find(classe, id);
        em.remove(o);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public List<T> select() {
        return select(null);
    }

    @Override
    public List<T> select(String orderBy) {
        String sql = "SELECT c FROM " + classe.getSimpleName() + " c ";
        if (orderBy != null) {
            sql = sql + "ORDER BY c." + orderBy + " ASC";
        }
        Query query = em.createQuery(sql);
        return query.getResultList();
    }

}
