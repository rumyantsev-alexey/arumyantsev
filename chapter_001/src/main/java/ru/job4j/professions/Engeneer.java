package ru.job4j.professions;
/*
    Класс Инженер подкласс класса Профессия, характеризуется специализацией "инженер" и действием "делает"
 */

public class Engeneer extends Profession{

    // конструктор для создания объекта Инженер с "визуализацией"
    public void Engeneer(String nname,  int aage, boolean iisMan,Profession.Special sspec, Profession.Qualification qqualif){
        this.setName(nname);
        this.setSpec(sspec);
        this.setAge(aage);
        this.setIsMan(iisMan);
        this.setQualif(qqualif);
        this.setWork("делает");
        this.setObSpec("инженер");
        System.out.println(this.getObSpec()+" "+this.getName()+" введен в базу");
        System.out.println("Возраст: "+this.getAge()+" Пол: "+(getIsMan()?"муж":"жен"));
        System.out.println("Специалзация: "+this.getSpec()+" Квалификация: "+this.getQualif());
    }

}
