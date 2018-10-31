package ru.job4j.servlets.crud;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс реализует постоянный слой и является реализацией интерфейса Story
 */
public class DbStore implements Store<User> {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();
    private static final Logger log = Logger.getLogger(DbStore.class.getName());

    /**
     * Конструктор с инициализаций DB
     */
    private DbStore() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/servlet");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("tester");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try (Connection con = SOURCE.getConnection();
             Statement st = con.createStatement()) {
            st.execute("create table if not exists country (id serial PRIMARY KEY, name VARCHAR );");
            st.execute("create table if not exists city (id serial PRIMARY KEY, name VARCHAR, " +
                    "country_id integer REFERENCES country (id) );");
            st.execute("create table if not exists role (id serial PRIMARY KEY, name VARCHAR );");
            st.execute("create table if not exists pages (id serial PRIMARY KEY, name VARCHAR );");
            st.execute("create table if not exists rights (role_id integer REFERENCES role (id)," +
                    "pages_id integer REFERENCES pages (id) , UNIQUE (role_id, pages_id));");
            st.execute("create table if not exists users (id INTEGER PRIMARY KEY ," +
                    "name VARCHAR , login VARCHAR , pass VARCHAR, email VARCHAR , createDate TIMESTAMP," +
                    "role_id integer REFERENCES role (id), city_id integer REFERENCES city (id) );");
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * Метод реализует добавление записи в списоке
     * @param model пользователь
     * @return успех
     */
    @Override
    public boolean add(User model) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("insert into users (id, name, login, pass, email, createDate, role_id, city_id)" +
                     "values (?,?,?,?,?,?,?,?)")) {
            st.setInt(1, model.getId());
            st.setString(2, model.getName());
            st.setString(3, model.getLogin());
            st.setString(4, model.getPass());
            st.setString(5, model.getEmail());
            st.setTimestamp(6, model.getRes());
            st.setInt(7, model.getRole_id());
            st.setInt(8, model.getCity_id());
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод реализует изменение записи в списке
     * @param model запись
     * @return успех
     */
    @Override
    public boolean update(User model) {
        boolean result = false;
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("update users set name = ?, login = ?, email = ?, role_id = ?, pass = ?, city_id = ? where id = ?")) {
            st.setInt(7, model.getId());
            st.setString(1, model.getName());
            st.setString(2, model.getLogin());
            st.setString(3, model.getEmail());
            st.setInt(4, model.getRole_id());
            st.setString(5, model.getPass());
            st.setInt(6, model.getCity_id());
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод реализуют удаление записи из списка
     * @param id айди записи
     * @return успех
     */
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("delete from users where id = ?")) {
            st.setInt(1, id);
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод возращает все записи в списке
     * @return список
     */
    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> result = new ArrayList<>();
        try (Connection con = SOURCE.getConnection();
             Statement st = con.createStatement();
             ResultSet rst = st.executeQuery("select id, name, login, email, role_id, createDate, city_id from users")) {
            while (rst.next()) {
                User user = new User();
                user.setId(rst.getInt(1));
                user.setName(rst.getString(2));
                user.setLogin(rst.getString(3));
                user.setEmail(rst.getString(4));
                user.setRole_id(rst.getInt(5));
                user.setRes(rst.getTimestamp(6));
                user.setCity_id(rst.getInt(7));
                result.add(user);
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод возвращает запись по ее айди
     * @param id айди записи
     * @return запись
     */
    @Override
    public User findById(int id) {
        User user = new User();
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("select id,name,login,email,role_id,createdate, pass, city_id from users where id = ?")){
            st.setInt(1, id);
             try (ResultSet rst = st.executeQuery()) {
                 if (rst.next()) {
                     user.setId(rst.getInt(1));
                     user.setName(rst.getString(2));
                     user.setLogin(rst.getString(3));
                     user.setEmail(rst.getString(4));
                     user.setRole_id(rst.getInt(5));
                     user.setRes(rst.getTimestamp(6));
                     user.setPass(rst.getString(7));
                     user.setCity_id(rst.getInt(8));
                 }
             }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return user;
    }

    /**
     * Метод генерирует определенное количество записей в списке
     * @param count количество
     */
    public void generate(final int count) {
        Random rnd = new Random();
        User user;
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            st.executeUpdate("delete from users");
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        for (int i = 0; i < count; i++) {
            user = new User("name" + rnd.nextInt(10000),"login" + rnd.nextInt(10000),
                    "111", "email" + rnd.nextInt(10000) + "@test.com", 1111, rnd.nextInt(6)+1 );
//            user.setRole_id(1111);
            this.add(user);
        }
    }

    /**
     * Метод реализует аутентификацию пользователя
     * @param login логин
     * @param pass пароль
     * @return уровень доступа
     */
    @Override
    public Integer checkLogin(final String login, final String pass) {
        Integer result = 0;
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("select role_id from users where login = ? and pass = ?")){
            st.setString(1, login);
            st.setString(2, pass);
            try (ResultSet rst = st.executeQuery()) {
                if (rst.next()) {
                    result = rst.getInt(1);}
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод реализует поиск роли по ее айди
     * @param role_id айди роли
     * @return Имя роли
     */
    @Override
    public String roleByRoleId(int role_id) {
        return nameById(role_id, "role");
    }

    /**
     * Метод возвращает список ролей
     * @return список ролей
     */
    @Override
    public ArrayList<String> findAllRoles() {
        return findAllNames("role");
    }

    /**
     * Метод реализует поиск айди роли по ее названию
     * @param role роль
     * @return айди роли
     */
    @Override
    public Integer roleidByRole(String role) {
        return idByName(role, "role", "id");
    }

    /**
     * Метод реализует авторизацию пользовтеля по его роли
     * @param role_id айди роли
     * @param link пусть к странице
     * @return успех
     */
    @Override
    public boolean accessToPage(Integer role_id, String link) {
        boolean result = false;
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("select * from rights where role_id = ? and pages_id = ?")){
            st.setInt(1, role_id);
            st.setInt(2, this.pageidByPage(link));
            try (ResultSet rst = st.executeQuery()) {
                if (rst.next()) {
                    result = true;}
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод реализует добавление новой роди
     * @param id айди роли
     * @param name имя роли
     * @return успех
     */
    @Override
    public boolean addRole(Integer id, String name) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("insert into role (id, name) values (?,?)")) {
            st.setInt(1, id);
            st.setString(2, name);
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод реализует добавление пути к странице для определения прав ролей
     * @param name путь
     * @return успех
     */
    @Override
    public boolean addPage(String name) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("insert into pages (name) values (?)")) {
            st.setString(1, name);
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод добавляет связь между ролью и определенной страницей
     * @param role роль
     * @param page страница
     * @return успех
     */
    @Override
    public boolean addLink(String role, String page) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("insert into rights (role_id, pages_id) values (?, ?)")) {
            st.setInt(1, this.roleidByRole(role));
            st.setInt(2, this.pageidByPage(page));
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод реализует поиск айди страницы по ее имени
     * @param page
     * @return
     */
    @Override
    public Integer pageidByPage(String page) {
        return idByName(page, "pages", "id");
    }

    /**
     * Метод добавляет пару страна-город
     * @param country страна
     * @param city город
     * @return
     */
    @Override
    public boolean addCityCountry(String country, String city) {
        boolean result = false;
        if (countryidByCountry(country) == null) {
            addCountry(country);
        }
        if (cityidByCity(city) == null || idByName(city, "city", "country_id") != countryidByCountry(country)) {
            addCity(city, countryidByCountry(country));
            result = true;
        }
        return result;
    }

    private boolean addCity(String city, Integer country_id) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("insert into city (name, country_id) values (?,?)")) {
            st.setString(1, city);
            st.setInt(2, country_id);
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    private boolean addCountry(String country) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("insert into country (name) values (?)")) {
            st.setString(1, country);
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод ищет город по айди
     * @param city_id
     * @return
     */
    @Override
    public String cityByCityId(int city_id) {
        return nameById(city_id, "city");
    }

    /**
     * Метод ищет айди города по имени
     * @param city
     * @return
     */
    @Override
    public Integer cityidByCity(String city) {
        return idByName(city, "city", "id");
    }

    /**
     * Метод возвращает список все стран
     * @return
     */
    @Override
    public ArrayList<String> findAllCountry() {
        return findAllNames("country");
    }

    /**
     * Метод ищет айди страны по имени
     * @param country
     * @return
     */
    @Override
    public Integer countryidByCountry(String country) {
        return idByName(country, "country", "id");
    }

    /**
     * Метод ищет все города данной страны
     * @param country
     * @return
     */
    @Override
    public ArrayList<String> findAllCityByCountry(String country) {
        ArrayList<String> result = new ArrayList<>();
        Integer country_id = countryidByCountry(country);
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("select name from city where country_id = ?")){
            st.setInt(1, country_id);
            try (ResultSet rst = st.executeQuery()) {
                while (rst.next()) {
                    result.add(rst.getString(1));
                }
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод ищет страну по айди города
     * @param city_id
     * @return
     */
    @Override
    public String countryByCityid(Integer city_id) {
        String country = null;
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("select country_id from city where id = ?")){
            st.setInt(1, city_id);
            try (ResultSet rst = st.executeQuery()) {
                if (rst.next()) {
                    country = nameById(rst.getInt(1), "country");
                }
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return country;
    }

    private Integer idByName(String name, String table, String col) {
        Integer result = null;
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("select " + col + " from " + table + " where name = ?")){
            st.setString(1, name);
            try (ResultSet rst = st.executeQuery()) {
                if (rst.next()) {
                    result = rst.getInt(1);}
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    private String nameById(Integer id, String table) {
        String result = null;
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("select name from " + table + " where id = ?")){
            st.setInt(1, id);
            try (ResultSet rst = st.executeQuery()) {
                if (rst.next()) {
                    result = rst.getString(1);}
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    public ArrayList<String> findAllNames(String table) {
        ArrayList<String> result = new ArrayList<>();
        try (Connection con = SOURCE.getConnection();
             Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("select name from " + table)) {
                while (rst.next()) {
                    result.add(rst.getString(1));
                }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }
}

