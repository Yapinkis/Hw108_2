package util;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class HibernateUtil {
    // @Getter мне IDE предлагало сгенерировать геттер-аннотацию для SessionFactory,но он же
    // вроде генерит только для нестатических полей?
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties();
            try (InputStream inputStream = HibernateUtil.class
                    .getClassLoader()
                    .getResourceAsStream("hibernate.properties")) {
                if (inputStream == null) {
                    throw new RuntimeException("Файл hibernate.properties не найден в classpath");
                }
                properties.load(inputStream);
            }
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(properties)
                    .build();
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(User.class);

            Metadata metadata = metadataSources.getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            log.error("Произошла ошибка в инициализации сессии sessionFactory", e);
            throw new RuntimeException(e);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
