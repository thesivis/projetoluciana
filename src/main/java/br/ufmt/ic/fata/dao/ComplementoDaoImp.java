/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.ic.fata.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import br.ufmt.ic.fata.ent.Complemento;
import br.ufmt.ic.fata.util.HibernateUtil;

/**
 *
 * @author vicentejr
 */
public class ComplementoDaoImp implements ComplementoDao{

    @Override
    public void save(Complemento complemento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(complemento);
        t.commit();
        session.close();
    }

    @Override
    public void remove(Complemento complemento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(complemento);
        t.commit();
        session.close();
    }

    @Override
    public void update(Complemento complemento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(complemento);
        t.commit();
        session.close();
    }

    @Override
    public List<Complemento> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Complemento").list();
        t.commit();
        session.close();
        return lista;
    }
    
}
