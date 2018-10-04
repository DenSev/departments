package by.dsev.departments.rest.application;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Created on: 10/04/18
 */
@Configuration
@ComponentScan("by.dsev.departments.rest")
@EnableTransactionManagement
public class ApplicationConfig {


    @Bean
    public Properties properties(@Value("${spring.jpa.properties.hibernate.dialect}") String dialect,
                                 @Value("${spring.jpa.show-sql}") String showSQL,
                                 @Value("${spring.jpa.hibernate.ddl-auto}") String dllAuto) {
        Properties properties = new Properties();
        properties.put(AvailableSettings.DIALECT, dialect);
        properties.put(AvailableSettings.SHOW_SQL, showSQL);
        properties.put(AvailableSettings.HBM2DDL_AUTO, dllAuto);
        return properties;
    }

    @Bean
    public DataSource dataSource(@Value("${db.driver.class}") String driverClass,
                                 @Value("${db.url}") String dbUrl,
                                 @Value("${db.username}") String dbUsername,
                                 @Value("${db.password}") String dbPassword) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }


    @Bean
    public SessionFactory sessionFactory(@Autowired DataSource dataSource,
                                         @Autowired Properties properties) throws IOException {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("by.dsev.departments.rest.entity");
        factoryBean.setHibernateProperties(properties);
        factoryBean.setBootstrapExecutor(new SimpleAsyncTaskExecutor());
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    @Bean
    public ObjectMapper objectMapper() {

        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(NON_NULL);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
            .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
            .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        return mapper;
    }
}
