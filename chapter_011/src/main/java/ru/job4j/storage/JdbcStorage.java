package ru.job4j.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcStorage implements Storage<User> {
    private final JdbcTemplate jt;

    public JdbcStorage(JdbcTemplate jt) {
        this.jt = jt;
    }

    private void createTable() {
        jt.execute("CREATE TABLE IF NOT EXISTS USERS(NAME VARCHAR (20) NOT NULL);");
    }

    private void clearTable() {
        jt.execute("TRUNCATE TABLE USERS;");
    }

    @Override
    public void add(User e) {
        jt.update("insert into USERS (NAME) VALUES(?)", e.getName());
    }

    @Override
    public List<User> getAll() {
        return jt.query("select * from USERS", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User(resultSet.getString(1));
            }
        });
    }
}
