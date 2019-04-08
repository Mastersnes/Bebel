package util;

import bdd.youlose.YouLoseDTO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

/**
 * Utilitaire de configuration hibernate
 */
public class HibernateUtil {
//    private static Logger logger = new Logger(HibernateUtil.class);
    private static SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                final Configuration configuration = new Configuration();

                final Properties settings = new Properties();

                String url = "jdbc:postgresql://%host%:%port%/%name%?useSSL=false";
                url = url.replaceAll("%host%", System.getenv("DB_HOST"));
                url = url.replaceAll("%port%", System.getenv("DB_PORT"));
                url = url.replaceAll("%name%", System.getenv("DB_NAME"));

                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, url);
                settings.put(Environment.USER, System.getenv("DB_USER"));
                settings.put(Environment.PASS, System.getenv("DB_PASSWORD"));
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create");
                configuration.setProperties(settings);

				configuration.addAnnotatedClass(YouLoseDTO.class);

                sessionFactory = configuration.buildSessionFactory();
            } catch (final Exception e) {
//                logger.err("Impossible de configurer la database", e);
            }
        }
        return sessionFactory;
    }
}
