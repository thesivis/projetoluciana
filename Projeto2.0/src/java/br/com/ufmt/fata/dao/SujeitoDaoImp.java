/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.dao;

import br.com.ufmt.fata.ent.Sujeito;
import br.com.ufmt.fata.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author vicentejr
 */
public class SujeitoDaoImp implements SujeitoDao{

    @Override
    public void save(Sujeito sujeito) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(sujeito);
        t.commit();
        session.close();
    }

    @Override
    public void remove(Sujeito sujeito) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(sujeito);
        t.commit();
        session.close();
    }

    @Override
    public void update(Sujeito sujeito) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(sujeito);
        t.commit();
        session.close();
    }

    @Override
    public List<Sujeito> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Sujeito").list();
        t.commit();
        session.close();
        return lista;
    }
    
    public List<Sujeito> listById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Sujeito where sujeitoId="+id).list();
        t.commit();
        session.close();
        return lista;
        
    }
    
}
