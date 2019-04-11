package com.bebel.bdd.dao;

import com.bebel.bdd.util.HibernateUtil;
import org.hibernate.SessionFactory;

public abstract class AbstractDao {
    public SessionFactory sessionFactory() {
        return HibernateUtil.getInstance().getSessionFactory();
    }
}
