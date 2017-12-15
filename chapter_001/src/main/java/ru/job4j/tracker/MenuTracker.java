package ru.job4j.tracker;

// Внешний внутренний класс, реализующий редактирование заявок
class EditItem implements UserAction{
    public int key(){
        return 2;
    }
    public void execute(Input input,Tracker tracker){
        System.out.println("------------ Редактирование заявки --------------");
        String str_id=input.ask("Введите Id заявки:");
        Item item=tracker.findById(str_id);
        if(item==null)
            System.out.println("Нет такой заявки!!!!!");
        else {
            System.out.println("Имя заявки:"+item.getName()+" Описание:"+item.getDesc());
            item.setName(input.ask("Введите новое имя заявки:"));
            item.setDesc(input.ask("Введите новое описание:"));
            tracker.update(item);
            System.out.println("Заявка изменена!!!");
        }
        System.out.println("---------------------------------------------------------");
    }
    public String info(){
        return String.format("%s. %s", this.key(),"Редактировать заявку");
    }
}

// Внешний внутренний класс, реализующий поиск завки по имени
class FindByName implements UserAction{
    public int key(){
        return 5;
    }
    public void execute(Input input,Tracker tracker){
        System.out.println("------------ Поиск заявки по имени --------------");
        String str_id=input.ask("Введите имя заявки:");
        Item[] items=tracker.findByName(str_id);
        if(items[0]==null)
            System.out.println("Нет такой заявки!!!!!");
        else {
            System.out.println("Заявки найдены!!!");
            for(Item item: items)
                System.out.println("Имя:"+item.getName()+" Описание:"+item.getDesc()+" Id:"+item.getId());
        }
        System.out.println("---------------------------------------------------------");
    }
    public String info(){
        return String.format("%s. %s", this.key(),"Поиск заявки по имени");
    }
}


public class MenuTracker {
    // количество пунктов меню
    private final int menulenght=7;
    // возможные значения для выбора пунктов меню
    private int[] ranges =new int[menulenght];
    private Input input;
    private Tracker tracker;
    private UserAction[] actions=new UserAction[menulenght];
    // признак выхода из меню
    private boolean vihod=false;

    public MenuTracker(Input input, Tracker tracker){
        this.input=input;
        this.tracker=tracker;
    }

    public int[] getRanges(){
        return this.ranges;
    }

    public void setRanges(int[] ranges){
        this.ranges=ranges;
    }

    public boolean getExit(){
        return this.vihod;
    }

    public void setExit(boolean exit){
        this.vihod=exit;
    }

    // метод реализующий заполнение меню
    public void fillActions(){
        this.actions[0]=new AddItem();
        this.actions[1]=new ShowItems();
        this.actions[2]=new EditItem();
        this.actions[3]=new DelItem();
        this.actions[4]=new FindById();
        this.actions[5]=new FindByName();
        this.actions[6]=new Exit();
        for(int i=0;i<menulenght;i++)
            this.ranges[i]=this.actions[i].key();
    }

// метод реализующий различные действия при выборе из меню
   public void select(int key){
        if(key<7 && this.actions[key]!=null)
            this.actions[key].execute(this.input,this.tracker);
        else
            System.out.println("Неверный выбор!!");
    }

    // метод, реализующий показ меню
    public void show(){
        for(UserAction action:this.actions){
            if(action!=null) System.out.println(action.info());
        }
    }

// внутрений класс, реализующий Добавление заявки
   private class AddItem implements UserAction{
        public int key(){
            return 0;
        }
        public void execute(Input input,Tracker tracker){
            System.out.println("------------ Добавление новой заявки --------------");
            String name=input.ask("Введите имя заявки:");
            String desc=input.ask("Введите описание заявки:");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Введена новая заявка с Id : " + item.getId() + "-----------");
        }
        public String info(){
            return String.format("%s. %s", this.key(),"Добавить заявку");
        }
    }

// внутрений статический класс, реализующий Показ всех заявок
    private static class ShowItems implements UserAction{
        public int key(){
            return 1;
        }
        public void execute(Input input,Tracker tracker){
            System.out.println("------------ Все зарегистрированные заявки --------------");
            for (Item item:tracker.findAll())
                System.out.println(String.format("Имя заявки:%s Описание заявки:%s Код заявки:%s",item.getName(),item.getDesc(),item.getId()));
            System.out.println("---------------------------------------------------------");
        }
        public String info(){
            return String.format("%s. %s", this.key(),"Посмотреть существующие заявки");
        }
    }

// внутрений класс, реализующий Удаление заявки
    private class DelItem implements UserAction{
        public int key(){
            return 3;
        }
        public void execute(Input input,Tracker tracker){
            System.out.println("------------ Удаление заявки --------------");
            String str_id=input.ask("Введите Id заявки:");
            Item item=tracker.findById(str_id);
            if(item==null)
                System.out.println("Нет такой заявки!!!!!");
            else {
                tracker.delete(item);
                System.out.println("Заявка удалена!!!");
            }
            System.out.println("---------------------------------------------------------");
        }
        public String info(){
            return String.format("%s. %s", this.key(),"Удаление заявки");
        }
    }

// внутрений статический класс, реализующий Поиск по Id
    private static class FindById implements UserAction{
        public int key(){
            return 4;
        }
        public void execute(Input input,Tracker tracker){
            System.out.println("------------ Поиск заявки по Id --------------");
            String str_id=input.ask("Введите Id заявки:");
            Item item=tracker.findById(str_id);
            if(item==null)
                System.out.println("Нет такой заявки!!!!!");
            else {
                System.out.println("Заявка найдена!!!");
                System.out.println("Имя:"+item.getName()+" Описание:"+item.getDesc()+" Id:"+item.getId());
            }
            System.out.println("---------------------------------------------------------");
        }
        public String info(){
            return String.format("%s. %s", this.key(),"Поиск заявки по Id");
        }
    }

// внутрений класс , реализующий Выход из меню
    private class Exit implements UserAction{
        public int key(){
            return 6;
        }
        public void execute(Input input,Tracker tracker){
            System.out.println("------------ Выход из меню --------------");
            setExit(true);
        }
        public String info(){
            return String.format("%s. %s", this.key(),"Выход из меню");
        }
    }

}
