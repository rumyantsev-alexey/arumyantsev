package ru.job4j.professions;
/*
    Класс Учитель подкласс класса Профессия, характеризуется специализацией "учитель" и действием "учит"
 */

public class Teacher extends Profession {

    // конструктор для создания объекта Учитель с "визуализацией"

    public void Teacher(String nname,int aage,boolean iisMan,Special sspec,Qualification qqualif){
        this.setName(nname);
        this.setSpec(sspec);
        this.setAge(aage);
        this.setIsMan(iisMan);
        this.setQualif(qqualif);
        this.setWork("учит");
        this.setObSpec("учитель");
        System.out.println(this.getObSpec()+" "+this.getName()+" введен в базу");
        System.out.println("Возраст: "+this.getAge()+" Пол: "+(getIsMan()?"муж":"жен"));
        System.out.println("Специалзация: "+this.getSpec()+" Квалификация: "+this.getQualif());
    }
}
