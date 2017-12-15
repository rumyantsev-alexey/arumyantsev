package ru.job4j.tracker;

// базовый класс заявки
public class Item {
    private String id; // уникальное поле заявки (может вместо него использовать время создания заявки??)
    private String name;// имя заявки
    private String desc;// описание заявки
    private long created; // время создания заявки
    private String[] commets; // комментарии к заявке (пока не используется)

    public Item(String name,String desc){
        this.name=name;
        this.desc=desc;
        this.created=System.currentTimeMillis();  // генерим время создания заявки
    }

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setDesc(String desc){
        this.desc=desc;
    }
    public long getCreat(){
        return this.created;
    }
    public void setCreat(long creat){
        this.created=creat;
    }
}