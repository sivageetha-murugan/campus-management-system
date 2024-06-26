package com.i2i.cms.helper;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * <p>
 * This class is implemented to handle the hibernate connection.
 * </p>
 */
public class HibernateConnection {

    private static SessionFactory sessionFactory;

    static{
        Dotenv dotenv = Dotenv.load();
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setProperty("hinernate.connection.driver_class", dotenv.get("DB_DRIVER"));
        configuration.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
        configuration.setProperty("hibernate.connection.username", dotenv.get("DB_USERNAME"));
        configuration.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}