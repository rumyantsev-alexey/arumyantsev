package ru.job4j.professions;
/*
    Класс Пациент подкласс класса Субъект, характеризуется признаком "больного"
 */

public class Patient extends Subject {
    // конструктор для создания объекта Пациент с "визуализацией"

    public void Patient(String nname, int aage, boolean iisMan, Profession.Special sspec, Profession.Status sstatus){
        this.setName(nname);
        this.setIsMan(iisMan);
        this.setAge(aage);
        this.setSpec(sspec);
        this.setStatus(sstatus);
        this.setWho("больного");
        System.out.println("Приветствуем "+this.getWho()+" "+this.getName()+" в нашей базе");
        System.out.println("Возраст: "+this.getAge()+" Пол: "+(getIsMan()?"муж":"жен"));
        System.out.println("Специализация: "+this.getSpec()+" Состояние по специализации: "+this.getStatus());
    }

}
