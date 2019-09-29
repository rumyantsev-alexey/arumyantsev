package ru.job4j.javaconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean(name = "user")
    public User getUser() {
        return new User("Alex");
    }

    @Bean(name = "memoryS")
    public MemoryStorage<User> getMS() {
        return new MemoryStorage<User>();
    }

    @Bean(name = "jdbcS")
    public JdbcStorage<User> getJS() {
        return new JdbcStorage<User>();
    }

    @Bean(name = "userS")
    public UserStorage getUS() {
        return new UserStorage(getMS());
    }
}
