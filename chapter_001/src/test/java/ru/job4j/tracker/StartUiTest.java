package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUiTest {

    // Тест пункта меню Создание заявки
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    // Тест пункта меню Редактирование заявки
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("2233","trrrw"));
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    // Тест пункта меню Удаление заявки
    @Test
    public void whenDelItemThenTrackerDoesIt() {
        Item[] items = new Item[4];
        // создаем тестовые заявки
        items[0] = new Item("testname1", "testDesc1");
        items[1] = new Item("testname2", "testDesc2");
        items[2] = new Item("testname3", "testDesc3");
        items[3] = new Item("testname4", "testDesc5");
        Tracker tracker = new Tracker();
        // добавляем заявки в трекер
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        tracker.add(items[3]);
        // удаляем 3й элемент
        Input input = new StubInput(new String[]{"3", items[2].getId(), "6"});
        new StartUI(input, tracker).init();
        // проверяем встал ли на его место 4й
        assertThat(tracker.findAll()[2].getId(), is(items[3].getId()));
    }

    // Тест пункта меню Найти по имени
    @Test
    public void whenFindByNameThenTrackerHasTrue() {
        Item[] items = new Item[4];
        // создаем тестовые заявки
        items[0] = new Item("testname1", "testDesc1");
        items[1] = new Item("testname2", "testDesc2");
        items[2] = new Item("testname3", "testDesc3");
        items[3] = new Item("testname4", "testDesc5");
        Tracker tracker = new Tracker();
        // добавляем заявки в трекер
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        tracker.add(items[3]);
        Input input = new StubInput(new String[]{"5",items[1].getName(), "6"});
        // Визуально видно что найден второй элемент
        new StartUI(input, tracker).init();
        // проверяем что элемент найденный по имени второй заявки является вторым элементом
        assertThat(tracker.findByName(items[1].getName())[0], is(items[1]));
    }

    // Тест пункта меню Найти по Id
    @Test
    public void whenFindByIdThenTrackerHasTrue() {
        Item[] items = new Item[4];
        // создаем тестовые заявки
        items[0] = new Item("testname1", "testDesc1");
        items[1] = new Item("testname2", "testDesc2");
        items[2] = new Item("testname3", "testDesc3");
        items[3] = new Item("testname4", "testDesc5");
        Tracker tracker = new Tracker();
        // добавляем заявки в трекер
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        tracker.add(items[3]);
        Input input = new StubInput(new String[]{"4",items[2].getId(), "6"});
        // Визуально видно что найден третий элемент
        new StartUI(input, tracker).init();
        // проверяем что элемент найденный по id третей заявки является третьим элементом
        assertThat(tracker.findById(items[2].getId()), is(items[2]));
    }

    // Тест пункта меню Показать все заявки не производился, т.к. в нем нет ввода информации


}
