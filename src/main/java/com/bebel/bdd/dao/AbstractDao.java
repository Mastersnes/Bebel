package com.bebel.bdd.dao;

import com.bebel.bdd.util.HibernateUtil;
import com.bebel.soclews.util.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractDao {
    private final Logger log = new Logger(getClass());

    public SessionFactory sessionFactory() {
        return HibernateUtil.getInstance().getSessionFactory();
    }

    public <DTO> void save(final DTO dto) {
        try (final Session session = sessionFactory().openSession()) {
            session.saveOrUpdate(dto);
            session.flush();
        } catch (final Exception e) {
            log.err("Impossible d'ecrire la table.", e);
        }
    }

    protected <DTO> List<DTO> list(final String select, final Map<String, String> params, final Class<DTO> clazz) {
        List<DTO> dtos = new ArrayList<>();
        try (final Session session = sessionFactory().openSession()) {
            final Query<DTO> query = session.createQuery(select, clazz);
            for (final Map.Entry<String, String> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }

            dtos = query.list();
        } catch (final Exception e) {
            log.err("Impossible de lire la table.", e);
        }
        return dtos;
    }

    protected <DTO> DTO unique(final String select, final Map<String, String> params, final Class<DTO> clazz) {
        DTO dto = null;
        try (final Session session = sessionFactory().openSession()) {
            final Query<DTO> query = session.createQuery(select, clazz);
            for (final Map.Entry<String, String> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }

            dto = query.uniqueResult();
        } catch (final Exception e) {
            log.err("Impossible de lire la table.", e);
        }
        return dto;
    }
}
