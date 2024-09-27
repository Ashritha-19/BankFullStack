package com.neoteric.fullstackdemo_31_08_2024.hibernate;

import com.neoteric.fullstackdemo_31_08_2024.model.AadharEntity;
import com.neoteric.fullstackdemo_31_08_2024.model.AccountAddressEntity;
import com.neoteric.fullstackdemo_31_08_2024.model.AccountEntity;
import com.neoteric.fullstackdemo_31_08_2024.model.AddressEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import java.util.Properties;

public class HibernateUtils {

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){

        if (sessionFactory == null){
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
            properties.put(Environment.URL,"jdbc:mysql://localhost:3306/bank");
            properties.put(Environment.USER,"root");
            properties.put(Environment.PASS,"root");
            properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
            properties.put(Environment.SHOW_SQL,true);

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(AccountEntity.class).
                    addAnnotatedClass(AccountAddressEntity.class)
                    .addAnnotatedClass(AddressEntity.class)
                    .addAnnotatedClass(AadharEntity.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        }
        return sessionFactory;
    }
}
