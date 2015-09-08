/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.dao;

import br.com.ufmt.fata.ent.Complemento;
import br.com.ufmt.fata.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author vicentejr
 */
public class ComplementoDaoImp implements ComplementoDao{

    @Override
    public void save(Complemento complemento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(complemento);
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
