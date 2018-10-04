package ru.job4j.servlets.crud;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий слой логики
 */
public class ValidateService {

    public static final int ADMIN_ID = 9999;
    public static final int USER_ID = 1111;

    private static final ValidateService vserv = new ValidateService();
//    private MemoryStore ms = MemoryStore.getInstance();
    private DbStore ms = DbStore.getInstance();
    private ValidateService() {
        initData();
    }

    public static ValidateService getInstance() {
        return vserv;
    }

    private void initData() {
        ms.addRole(USER_ID, "User");
        ms.addRole(ADMIN_ID, "Admin");
        ms.addPage("/chapter_009/list");
        ms.addPage("/chapter_009/login");
        ms.addPage("/chapter_009/create");
        ms.addPage("/chapter_009/edit");
        ms.addLink("Admin","/chapter_009/list" );
        ms.addLink("Admin","/chapter_009/create" );
        ms.addLink("Admin","/chapter_009/edit" );
        ms.addLink("Admin","/chapter_009/login" );
        ms.addLink("User","/chapter_009/list" );
        ms.addLink("User","/chapter_009/edit" );
        ms.addLink("User","/chapter_009/login" );
    }

    /**
     * Метод генерирует определенное количество записей в списке
     * @param count количество
     */
    public void generate (final int count) {
        if(count > 0) {
            ms.generate(count);
        }
    }

    /**
     * Метод реализует добавление записи в списоке
     * @param name имя
     * @return успех
     */
    public boolean add(String name) {
        return name != null && ms.add(new User(name,null, null,  null, null));
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
    public boolean addFull(final String name, final String login, final String pass, final String email, final Integer role_id) {
        return name != null && ms.add(new User(name, login, pass, email, role_id));
    }

    /**
     * Метод реализует аутентификацию пользователя
     * @param login логин
     * @param pass пароль
     * @return уровень доступа
     */
    public Integer checkLogin(final String login, final String pass) {
        return  (login != null &&  pass != null) ? ms.checkLogin(login, pass): 0;
    }

    /**
     * Метод реализует поиск роли по ее айди
     * @param role_id айди
     * @return имя роли
     */
    public String roleByRoleId(Integer role_id) {
        return ms.roleByRoleId(role_id);
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
     * @param role_id айди роли
     * @param link пусть к странице
     * @return успех
     */
    public boolean access(Integer role_id, String link) {
        return ms.accessToPage(role_id, link);
    }

}
