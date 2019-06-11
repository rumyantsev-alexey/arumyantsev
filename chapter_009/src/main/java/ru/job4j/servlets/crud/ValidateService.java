package ru.job4j.servlets.crud;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий слой логики
 */
public class ValidateService {

    public static final int ADMIN_ID = 9999;
    public static final int USER_ID = 1111;

    private static final ValidateService VSERV = new ValidateService();
//    private MemoryStore ms = MemoryStore.getInstance();
    private DbStore ms = DbStore.getInstance();
    protected ValidateService() {
    initData();
    }

    public static ValidateService getInstance() {
        return VSERV;
    }

    private void initData() {
        ms.addRole(USER_ID, "User");
        ms.addRole(ADMIN_ID, "Admin");
        ms.addPage("/chapter_009/list");
        ms.addPage("/chapter_009/login");
        ms.addPage("/chapter_009/create");
        ms.addPage("/chapter_009/edit");
        ms.addPage("/chapter_009/ajax");
        ms.addLink("Admin", "/chapter_009/list");
        ms.addLink("Admin", "/chapter_009/create");
        ms.addLink("Admin", "/chapter_009/edit");
        ms.addLink("Admin", "/chapter_009/login");
        ms.addLink("Admin", "/chapter_009/ajax");
        ms.addLink("User", "/chapter_009/list");
        ms.addLink("User", "/chapter_009/edit");
        ms.addLink("User", "/chapter_009/login");
        ms.addLink("User", "/chapter_009/ajax");
        ms.addCityCountry("Russian", "Moscow");
        ms.addCityCountry("Russian", "Piter");
        ms.addCityCountry("Russian", "Samara");
        ms.addCityCountry("Ukraine", "Kiev");
        ms.addCityCountry("Ukraine", "Lvov");
        ms.addCityCountry("Ukraine", "Dnepr");
        ms.addCityCountry("USA", "Boston");
        ms.addCityCountry("USA", "LA");
        ms.addCityCountry("USA", "NY");
//        generate(10);
    }

    /**
     * Метод генерирует определенное количество записей в списке
     * @param count количество
     */
    public void generate(final int count) {
        if (count > 0) {
            ms.generate(count);
        }
    }

    /**
     * Метод реализует добавление записи в списоке
     * @param name имя
     * @return успех
     */
    public boolean add(String name) {
        return name != null && ms.add(new User(name, null, null,  null, null, null));
    }

    /**
     * Метод реализует изменение записи в списке
     * @param id айди записи
     * @param newname новое имя
     * @return успех
     */
    public boolean update(int id, String newname) {
        return id > -1 && newname != null && ms.findById(id) != null && ms.update(new User(id, newname));
    }

    /**
     * Метод реализуют удаление записи из списка
     * @param id
     * @return успех
     */
    public boolean delete(int id) {
        return id > -1 && ms.findById(id) != null && ms.delete(id);
    }

    /**
     * Метод возращает все записи в списке
     * @return список
     */
    public ArrayList<User> findAll() {
        return ms.findAll();
    }

    /**
     * Метод реализует пооиск пользлвателя по айди
     * @param id
     * @return
     */
    public User findById(final int id) {
        return id > -1 ? ms.findById(id) : null;
    }


    /**
     * Метод реализует изменение записи с помощью другой записи
     * @param usr новая запись
     * @return успех
     */
    public boolean updateByUser(final User usr) {
        return usr != null && ms.update(usr);
    }

    /**
     * Метод реализует создние записи с помощью заданных 5х полей
     * @param name имя
     * @param login логин
     * @param email емейл
     * @return
     */
    public boolean addFull(final String name, final String login, final String pass, final String email, final Integer roleid, final Integer cityid) {
        return name != null && ms.add(new User(name, login, pass, email, roleid, cityid));
    }

    /**
     * Метод реализует аутентификацию пользователя
     * @param login логин
     * @param pass пароль
     * @return уровень доступа
     */
    public Integer checkLogin(final String login, final String pass) {
        return  (login != null &&  pass != null) ? ms.checkLogin(login, pass) : 0;
    }

    /**
     * Метод реализует поиск роли по ее айди
     * @param roleid айди
     * @return имя роли
     */
    public String roleByRoleId(Integer roleid) {
        return ms.roleByRoleId(roleid);
    }

    /**
     * Метод реализует поиск айди роли по ее названию
     * @param role роль
     * @return айди роли
     */
    public Integer roleidByRole(String role) {
        return ms.roleidByRole(role);
    }

    /**
     * Метод возвращает список ролей
     * @return список ролей
     */
    public ArrayList<String> findAllRoles() {
        return ms.findAllRoles();
    }

    /**
     * Метод реализует авторизацию пользовтеля по его роли
     * @param roleid айди роли
     * @param link пусть к странице
     * @return успех
     */
    public boolean access(Integer roleid, String link) {
        return ms.accessToPage(roleid, link);
    }

    /**
     * Метод реализует поиск города по его айди
     * @param cityid
     * @return
     */
    public String cityByCityId(int cityid) {
        return ms.cityByCityId(cityid);
    }

    /**
     * Метод реализует поиск айди города по его имени
     * @param city
     * @return
     */
    public Integer cityidByCity(String city) {
        return ms.cityidByCity(city);
    }

    /**
     * Метод ищет все города определенной страны
     * @param country
     * @return
     */
    public ArrayList<String> findAllCityByCountry(String country) {
        return ms.findAllCityByCountry(country);
    }

    /**
     * Метод выдает список всех стран
     * @return
     */
    public ArrayList<String> findAllCountry() {
        return ms.findAllCountry();
    }

    /**
     * Метод выдает страну по айди города
     * @param cityid
     * @return
     */
    public String countryByCityid(Integer cityid) {
        return ms.countryByCityid(cityid);
    }
    }
