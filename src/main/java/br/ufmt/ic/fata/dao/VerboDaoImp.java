/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.ic.fata.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import br.ufmt.ic.fata.ent.Verbo;
import br.ufmt.ic.fata.util.HibernateUtil;

/**
 *
 * @author vicentejr
 */
public class VerboDaoImp implements VerboDao{

    @Override
    public void save(Verbo verbo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(verbo);
        t.commit();
        session.close();
    }

    @Override
    public void remove(Verbo verbo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(verbo);
        t.commit();
        session.close();
    }

    @Override
    public void update(Verbo verbo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(verbo);
        t.commit();
        session.close();
    }

    @Override
    public List<Verbo> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Verbo").list();
        t.commit();
        session.close();
        return lista;
    }
    
}
