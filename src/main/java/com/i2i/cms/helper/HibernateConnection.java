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

        String dbDriver = dotenv.get("DB_DRIVER");
        String dbUrl = dotenv.get("DB_URL");
        String dbUsername = dotenv.get("DB_USERNAME");
        String dbPassword = dotenv.get("DB_PASSWORD");
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setProperty("hinernate.connection.driver_class", dbDriver);
        configuration.setProperty("hibernate.connection.url", dbUrl);
        configuration.setProperty("hibernate.connection.username", dbUsername);
        configuration.setProperty("hibernate.connection.password", dbPassword);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}