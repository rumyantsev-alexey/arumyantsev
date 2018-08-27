package ru.job4j.position;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс предоставляет инструменты для работы с БД task
 */
public class Service {
    private static String driver;
    private static String url;
    private static final Logger log = Logger.getLogger(Service.class.getName());

    /**
     * Конструктор читает свойства, инициализирует драйвера и проверяет наличие таблицы
     * @param opt файл параметров
     */
    public Service(final String opt) {
        Properties prt = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(opt)) {
            prt.load(inputStream);
            driver = prt.getProperty("driver");
            url = prt.getProperty("url");
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url);
                 Statement st = con.createStatement()) {
                st.execute("create table if not exists task (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), position INTEGER UNIQUE );");
            } catch (SQLException e) {
                log.log(Level.WARNING, "SQL error", e);
            }
        } catch (IOException | ClassNotFoundException e) {
            log.log(Level.WARNING, e.getMessage(), e);
        }
    }

    /**
     * Метод генерирует требуеме количество записей в БД
     * (для тестов)
     * @param count количество
     */
    public void sampleGenerate(final int count) {
        Random rnd = new Random();
        String name;
        int  pos;
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement st = con.prepareStatement("insert into task (name, position) values (?,?)")) {
            for (int i = 0; i < count; i++) {
                name = "name" + rnd.nextInt(10000);
                pos = rnd.nextInt(10000);
                st.setString(1, name);
                st.setInt(2, pos);
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
    }

    /**
     * Метод очищает БД
     * (для тестов)
     */
    public void clearData() {
        try (Connection con = DriverManager.getConnection(url);
             Statement st = con.createStatement()) {
            st.executeUpdate("delete from task;");
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
    }

    /**
     * Метод добавляет запись в БД
     * @param name название задачи
     * @param position позиция
     * @return успех
     */
    public boolean add(final String name, final int position) {
        return action(1, name, position);
    }

    /**
     * Метод удаляет запись из БД
     * @param name название задачи
     * @param position позиция
     * @return успех
     */
    public boolean delete(final String name, final int position) {
        return action(2, name, position);
    }

    /**
     * Метод реализцет действия над БД
     * @param action требуемое действие (1 - добавление, остальные - удаление)
     * @param name название задачи
     * @param position позиция
     * @return успех
     */
    private boolean action(final int action, final String name, final int position) {
        boolean result = false;
        String sql = action == 1 ? "insert into task (name, position) values (?,?)" : "delete from task where name = ? and position = ?";
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, name);
            st.setInt(2, position);
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод поднимает задачу в списке позиций
     * @param name  название задачи
     * @param position позиция
     * @return успех
     */
    public boolean up(final String name, final int position) {
        String name_prv = null;
        int position_prv = 0;
        boolean result = false;
        try (Connection con = DriverManager.getConnection(url);
             Statement st = con.createStatement();
             ResultSet rst = st.executeQuery("select * from task order by position")) {
            while (rst.next()) {
                if (position_prv > 0 && rst.getString(2).equals(name) && rst.getInt(3) == position) {
                    try (PreparedStatement st2 = con.prepareStatement("update task set name = ? where position = ?")) {
                        st2.setString(1, name);
                        st2.setInt(2, position_prv);
                        st2.executeUpdate();
                        st2.setString(1, name_prv);
                        st2.setInt(2, position);
                        st2.executeUpdate();
                        result = true;
                        break;
                    } catch (SQLException e) {
                        log.log(Level.WARNING, "SQL error", e);
                    }
                } else {
                    name_prv = rst.getString(2);
                    position_prv = rst.getInt(3);
                }
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод опускает задачу в списке позиций
     * @param name  название задачи
     * @param position позиция
     * @return успех
     */
    public boolean down(final String name, final int position) {
        boolean result = false;
        boolean flag = false;
        try (Connection con = DriverManager.getConnection(url);
             Statement st = con.createStatement();
             ResultSet rst = st.executeQuery("select * from task order by position")) {
            if (rst.next()) {
                do {
                    if (rst.getString(2).equals(name) && rst.getInt(3) == position) {
                        flag = rst.next();
                        if (flag) {
                            try (PreparedStatement st2 = con.prepareStatement("update task set name = ? where position = ?")) {
                                st2.setString(1, name);
                                st2.setInt(2, rst.getInt(3));
                                st2.executeUpdate();
                                st2.setString(1, rst.getString(2));
                                st2.setInt(2, position);
                                st2.executeUpdate();
                                result = true;
                                break;
                            } catch (SQLException e) {
                                log.log(Level.WARNING, "SQL error", e);
                            }
                        }
                    } else {
                        flag = rst.next();
                    }
                } while (flag);
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }

    /**
     * Метод выводит в консоль текущую таблицу задач
     * (для теста)
     */
    public void show() {
        try (Connection con = DriverManager.getConnection(url);
             Statement st = con.createStatement();
             ResultSet rst = st.executeQuery("select * from task order by position")) {
            while (rst.next()) {
                System.out.println(rst.getInt(1) + " " + rst.getString(2) + " " + rst.getInt(3));
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
    }

    /**
     * Метод ищет задачу по имени и выводит ее позицию
     * @param name название здачи
     * @return позиция
     */
    public int findByName(final String name) {
        int result = 0;
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement st = con.prepareStatement("select position from task where name = ?")) {
            st.setString(1, name);
            try (ResultSet rst = st.executeQuery()) {
                if (rst.next()) {
                    result = rst.getInt(1);
                }
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "SQL error", e);
        }
        return result;
    }
}
