package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerTest {

    // тест методов add и update
    @Test
    public void whenUpdateNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.update(next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
    // тест метода findAll
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        // создаем заявку
        Item item = new Item("test1", "testDescription");
        // добавляем заявку в трекер
        tracker.add(item);
        // проверяем что она есть в списке заявок
        assertThat(tracker.findAll()[0], is(item));
    }
    // тест метода FindById
    @Test
    public void whenFindByIdThenTrackerHasTrue() {
        Item[] test_items = new Item[4];
        // создаем тестовые заявки
        test_items[0] = new Item("testname1", "testDesc1");
        test_items[1] = new Item("testname2", "testDesc2");
        test_items[2] = new Item("testname3", "testDesc3");
        test_items[3] = new Item("testname4", "testDesc5");
        Tracker tracker = new Tracker();
        // добавляем заявки в трекер
        tracker.add(test_items[0]);
        tracker.add(test_items[1]);
        tracker.add(test_items[2]);
        tracker.add(test_items[3]);
        // проверяем что элемнт найденный по id третей заявки является третьим элементом
        assertThat(tracker.findById(test_items[2].getId()), is(test_items[2]));
    }
    // тест метода FindByName
    @Test
    public void whenFindByNameThenTrackerHasTrue() {
        Item[] test_items = new Item[4];
        // создаем тестовые заявки
        test_items[0] = new Item("testname1", "testDesc1");
        test_items[1] = new Item("testname2", "testDesc2");
        test_items[2] = new Item("testname3", "testDesc3");
        test_items[3] = new Item("testname4", "testDesc5");
        Tracker tracker = new Tracker();
        // добавляем заявки в трекер
        tracker.add(test_items[0]);
        tracker.add(test_items[1]);
        tracker.add(test_items[2]);
        tracker.add(test_items[3]);
        // проверяем что элемнт найденный по имени второй заявки является вторым элементом
        assertThat(tracker.findByName(test_items[1].getName())[0], is(test_items[1]));
    }
    @Test
    // тест метода del
    public void whenDelItemThenTrackerDoesIt() {
        Item[] test_items = new Item[4];
        // создаем тестовые заявки
        test_items[0] = new Item("testname1", "testDesc1");
        test_items[1] = new Item("testname2", "testDesc2");
        test_items[2] = new Item("testname3", "testDesc3");
        test_items[3] = new Item("testname4", "testDesc5");
        Tracker tracker = new Tracker();
        // добавляем заявки в трекер
        tracker.add(test_items[0]);
        tracker.add(test_items[1]);
        tracker.add(test_items[2]);
        tracker.add(test_items[3]);
        // удаляем 3й элемент
        tracker.delete(test_items[2]);
        // проверяем встал ли на его место 4й
        assertThat(tracker.findAll()[2].getName(), is(test_items[3].getName()));
    }

}