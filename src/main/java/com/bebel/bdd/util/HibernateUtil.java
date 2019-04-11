package com.bebel.bdd.util;

import com.bebel.bdd.dto.YouLoseDto;
import com.bebel.soclews.util.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {
    private static HibernateUtil instance;

    private Logger logger = new Logger(getClass());
    private SessionFactory sessionFactory;

    private HibernateUtil() {
        initSessionFactory();
    }

    public static synchronized HibernateUtil getInstance() {
        if (instance == null) {
            instance = new HibernateUtil();
        }
        return instance;
    }

    public void initSessionFactory() {
        try {
            String url = "jdbc:postgresql://%host%:%port%/%name%?useSSL=false";
            url = url.replaceAll("%host%", System.getenv("DB_HOST"));
            url = url.replaceAll("%port%", System.getenv("DB_PORT"));
            url = url.replaceAll("%name%", System.getenv("DB_NAME"));

            final String user = System.getenv("DB_USER");
            final String password = System.getenv("DB_PASSWORD");

            final Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, url);
            settings.put(Environment.USER, user);
            settings.put(Environment.PASS, password);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "create");

            final Configuration configuration = new Configuration();
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(YouLoseDto.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (final Exception e) {
            logger.err("Impossible de configurer la database", e);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
