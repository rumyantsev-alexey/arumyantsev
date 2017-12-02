package ru.job4j.professions;
/*
    Класс Проект подкласс класса Субъект, характеризуется признаком "проект"
 */

public class Project extends Subject {
    // конструктор для создания объекта Проект с "визуализацией"

    public void Project(String nname, int aage, boolean iisMan, Profession.Special sspec, Profession.Status sstatus){
        this.setName(nname);
        this.setIsMan(iisMan);
        this.setAge(aage);
        this.setSpec(sspec);
        this.setStatus(sstatus);
        this.setWho("проект");
        System.out.println("Приветствуем "+this.getWho()+" "+this.getName()+" в нашей базе");
        System.out.println("Возраст: "+this.getAge()+" Пол: "+(getIsMan()?"муж":"жен"));
        System.out.println("Специализация: "+this.getSpec()+" Состояние по специализации: "+this.getStatus());
    }

}
