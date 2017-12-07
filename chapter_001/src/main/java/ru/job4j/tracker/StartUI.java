package ru.job4j.tracker;

public class StartUI {
    /**
     * Константы для  меню
     */
    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DEL = "3";
    private static final String FID = "4";
    private static final String FNM = "5";
    private static final String EXIT = "6";

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer))
                this.createItem();
            else if (SHOW.equals(answer))
                this.showItems();
            else if (EDIT.equals(answer))
                this.editItem();
            else if (DEL.equals(answer))
                this.delItem();
            else if (FID.equals(answer))
                this.findById();
            else if (FNM.equals(answer))
                this.findByName();
            else if (EXIT.equals(answer))
                exit = true;
            else
                this.showErr();
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с Id : " + item.getId() + "-----------");
    }

    /**
     * Метод реализует показ всех заявок в хранилище
     */
    private void showItems(){
        System.out.println("------------ Все зарегистрированные заявки --------------");
        for(Item item: this.tracker.findAll())
            System.out.println("Имя:"+item.getName()+" Описание:"+item.getDesc()+" Id:"+item.getId());
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Метод реализует редактирование заявки
     */
    private void editItem(){
        System.out.println("------------ Редактирование заявки --------------");
        String str_id=this.input.ask("Введите Id заявки:");
        Item item=this.tracker.findById(str_id);
        if(item==null)
            System.out.println("Нет такой заявки!!!!!");
        else {
            System.out.println("Имя заявки:"+item.getName()+" Описание:"+item.getDesc());
            item.setName(this.input.ask("Введите новое имя заявки:"));
            item.setDesc(this.input.ask("Введите новое описание:"));
            this.tracker.update(item);
            System.out.println("Заявка изменена!!!");
        }
    }

    /**
     * Метод реализует удаление заявки в хранилище.
     */
    private void delItem(){
        System.out.println("------------ Удаление заявки --------------");
        String str_id=this.input.ask("Введите Id заявки:");
        Item item=this.tracker.findById(str_id);
        if(item==null)
            System.out.println("Нет такой заявки!!!!!");
        else {
            this.tracker.delete(item);
            System.out.println("Заявка удалена!!!");
        }
    }

    /**
     * Метод реализует поиск заявки по Id в хранилище.
     */
    private void findById(){
        System.out.println("------------ Поиск заявки по Id --------------");
        String str_id=this.input.ask("Введите Id заявки:");
        Item item=this.tracker.findById(str_id);
        if(item==null)
            System.out.println("Нет такой заявки!!!!!");
        else {
            System.out.println("Заявка найдена!!!");
            System.out.println("Имя:"+item.getName()+" Описание:"+item.getDesc()+" Id:"+item.getId());
        }
    }


    /**
     * Метод реализует поиск заявки по имени в хранилище.
     */
    private void findByName(){
        System.out.println("------------ Поиск заявки по имени --------------");
        String str_id=this.input.ask("Введите имя заявки:");
        Item[] items=this.tracker.findByName(str_id);
        if(items[0]==null)
            System.out.println("Нет такой заявки!!!!!");
        else {
            System.out.println("Заявки найдены!!!");
            for(Item item: items)
                System.out.println("Имя:"+item.getName()+" Описание:"+item.getDesc()+" Id:"+item.getId());
        }
    }

    /**
     * Сообщение о неверном выборе в меню
     */
    private void showErr() {
        System.out.println("Нет такого пункта!!!!!");
    }

    /**
     * Печать менб
     */
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавить новую заяаку");
        System.out.println("1. Показать все заявки");
        System.out.println("2. Редактировать заявку");
        System.out.println("3. Удалить заявку");
        System.out.println("4. Найти заявку по Id");
        System.out.println("5. Найти заявку по Имени");
        System.out.println("6. Выход");
    }

   /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}