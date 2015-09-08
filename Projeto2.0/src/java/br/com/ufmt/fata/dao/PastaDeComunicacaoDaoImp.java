/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.dao;

import br.com.ufmt.fata.ent.PastaDeComunicacao;
import br.com.ufmt.fata.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author vicentejr
 */
public class PastaDeComunicacaoDaoImp implements PastaDeComunicacaoDao{

    @Override
    public void save(PastaDeComunicacao pastaDeComunicacao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(pastaDeComunicacao);
        t.commit();
        session.close();
    }

    @Override
    public void remove(PastaDeComunicacao pastaDeComunicacao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(pastaDeComunicacao);
        t.commit();
        session.close();
    }

    @Override
    public void update(PastaDeComunicacao pastaDeComunicacao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(pastaDeComunicacao);
        t.commit();
        session.close();
    }

    @Override
    public List<PastaDeComunicacao> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from PastaDeComunicacao").list();
        t.commit();
        session.close();
        return lista;
    }
}
