package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];
    private Random rn = new Random();

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реалезующий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setCreat(System.currentTimeMillis());
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        //Реализовать метод генерации.
        return String.valueOf(System.currentTimeMillis()+rn.nextInt());
    }


    /**
     * Метод реалезующий изменение заявки в хранилище
     * @param item измененная заявка
     */
    public void update(Item item){
        for(int i=0; i<position;i++)
            if(items[i].getId().equals(item.getId())){
                items[i]=item;
                break;
            }
    }
    /**
     * Метод реалезующий удаление заявки из хранилища
     * @param item существующая заявка
     */
    public void delete(Item item){
        Item[] temp_items=Arrays.copyOf(items,position);
        for(int i=0; i<position;i++)
            if(items[i].getId().equals(item.getId())){
                System.arraycopy(temp_items,i+1,items,i,position-i-1);
                items[--position]=null;
                break;
            }
    }

    /**
     * Метод реалезующий получение всех заявок из хранилища
     * @return Item[] все существующие заявки
     */
    public Item[] findAll(){
        return Arrays.copyOf(items,position);
    }

    /**
     * Метод реалезующий поиск заявки в хранилище по имени
     * @param key имя заявки
     * @return Item[]  все найденные заявки
     */
    public Item[] findByName(String key){
        Item[] result=new Item[100];
        int pos=0;
        for(Item item:items)
            if(item!=null&&item.getName().equals(key))
                result[pos++]=item;
        return Arrays.copyOf(result,pos);
    }

    /**
     * Метод реалезующий поиск заявки в хранилище по id
     * @param id id заявки
     * @return Item найденная заявка
     */
    public Item findById(String id){
        Item result=null;
        for(Item item:items)
            if(item!=null&&item.getId().equals(id)){
                result=item;
                break;
            }
        return result;
    }
}
