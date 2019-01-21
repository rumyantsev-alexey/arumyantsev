package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.todo.HibernateSessionFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Класс тестирует основные действия с сущностью объявления о продаже автомобиля
 * В качестве обязательных параметров оставлены: текст объявления, пользователь,
 * город нахождения автомобиля, марка и модель авто
 */
public class DBStoreTest {
    Store<CarEntity> carsdb = new DbStore<>(CarEntity.class);
    CarEntity car = null;

    @Before
    /**
     * Метод создает сущность объявления для тестов
     */
    public void createCarEntity() {
        car = new CarEntity("test");
        Store<CityEntity> citydb = new DbStore<>(CityEntity.class);
        Store<UsersEntity> userdb = new DbStore<>(UsersEntity.class);
        Store<MarkEntity> markdb = new DbStore<>(MarkEntity.class);
        Store<ModelEntity> modeldb = new DbStore<>(ModelEntity.class);
        car.setCity(citydb.findById(1));
        car.setUser(userdb.findById(1));
        car.setModel(modeldb.findById(1));
        car.setMark(markdb.findById(1));
    }

    @Test
    /**
     * Метод проверяет добавление сущности в хранилище
     */
    public void addEntityTest() {
        int idx = carsdb.add(car);
        car = carsdb.findById(idx);
        assertThat(car.getNote(), is("test"));
        carsdb.delete(idx);
    }

    @Test
    /**
     * Метод проверяет изменение сущности в хранилище
     */
    public void updateEntityTest() {
        car.setNote("test2");
        int idx = carsdb.add(car);
        car.setNote("test3");
        carsdb.update(car);
        car = carsdb.findById(idx);
        assertThat(car.getNote(), is("test3"));
        carsdb.delete(idx);
    }

    @Test
    /**
     * Метод проверяет удаление сущности из хранилище
     */
    public void deleteEntityTest() {
        car.setNote("test4");
        int idx = carsdb.add(car);
        carsdb.delete(idx);
        assertThat(carsdb.findById(idx), is(nullValue()));
    }

}