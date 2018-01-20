package ru.job4j.tracker;

/**
 * базовый класс заявки
 * @author Alex Rumyantcev
 * @version $Id$
 */
public class Item {
    private String id; // уникальное поле заявки (может вместо него использовать время создания заявки??)
    private String name; // имя заявки
    private String desc; // описание заявки
    private long created; // время создания заявки
    private String[] commets; // комментарии к заявке (пока не используется)

    /**
     * конструктор завки с инициализацией параметров
     * @param name имя заявки
     * @param desc описание заявки
     */
    public Item(final String name, final String desc) {
        this.name = name;
        this.desc = desc;
        this.created = System.currentTimeMillis();  // генерим время создания заявки
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    // геты/сеты для параметров класса
    public String getId() {
        return this.id;
    }
    public void setId(final String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(final String name){
        this.name = name;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(final String desc) {
        this.desc = desc;
    }
    public long getCreat() {
        return this.created;
    }
    public void setCreat(final long creat) {
        this.created = creat;
    }
}
