package ru.job4j.professions;
/*
    Класс Доктор подкласс класса Профессия, характеризуется специализацией "врач" и действием "лечит"
 */
public class Doctor extends Profession {

    // конструктор для создания объекта Доктор с "визуализацией"
    public void Doctor(String nname,int aage,boolean iisMan,Special sspec,Qualification qqualif){
        this.setName(nname);
        this.setSpec(sspec);
        this.setAge(aage);
        this.setIsMan(iisMan);
        this.setQualif(qqualif);
        this.setWork("лечит");
        this.setObSpec("врач");
        System.out.println(this.getObSpec()+" "+this.getName()+" введен в базу");
        System.out.println("Возраст: "+this.getAge()+" Пол: "+(getIsMan()?"муж":"жен"));
        System.out.println("Специалзация: "+this.getSpec()+" Квалификация: "+this.getQualif());
    }
}
