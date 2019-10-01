package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@PropertySource(value = "classpath:db.properties")
@Configuration
public class JavaConfig {

    @Autowired
    Environment environment;

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getConnect() {
        return new JdbcTemplate(getDS());
    }

    @Bean(name = "dataSource")
    public DriverManagerDataSource getDS() {
        return new DriverManagerDataSource(environment.getProperty("jdbc.url"), environment.getProperty("jdbc.username"), environment.getProperty("jdbc.password"));
    }

    @Bean(name = "user")
    public User getUser() {
        return new User("Alex");
    }

    @Bean(name = "memoryS")
    public MemoryStorage<User> getMS() {
        return new MemoryStorage<User>();
    }

    @Bean(name = "jdbcS", initMethod = "createTable")
    public JdbcStorage getJS() {
        return new JdbcStorage(getConnect());
    }

    @Bean(name = "userS")
    public UserStorage getUS() {
        return new UserStorage(getJS());
    }
}
