package ru.job4j.professions;

import java.util.Arrays;
/*
    Родительский класс для Учителя, Доктора и Инженера
 */
public class Profession extends Human {
    // множество специализаций
    enum Special {
        история, физкультура, алгебра, музыка, лор, хирург, проктолог, терапевт, архитектор, электрик, механик, электронщик
    };
    // множество квалификаций
    enum Qualification {
        стажер, младший_специалист, старший_специалист, бог
    };
    // множество состояний субъектов
    enum Status {
         начало, чуть_чуть, половина, почти_все, конец
    };

    private Special spec;   //специализация. доллжна совпадать со специализаций субъекта, чтобы на него воздействовать
    private Qualification qualif;   //квалификация профи. чем выше - тем лучше воздействие (не реализовано)
    private final int numsubject = 10;   // макс количетво субъектов на которые можно воздействовать
    private Subject[] subjects = new Subject[numsubject];    // массив субъектов
    private int qntsubject = 0;  //количество добавленных в список субъектов
    private String work;    // действие специалиста словами
    private String obspec; // название специальности (общее)

    // сет/геты для параметров класса
    public String getObSpec() {
        return this.obspec;
    }
    public void setObSpec(String obspec) {
        this.obspec = obspec;
    }

    public String getWork() {
        return this.work;
    }
    public void setWork(String ss) {
        this.work = ss;
    }
    public Special getSpec() {
        return this.spec;
    }
    public void setSpec(Special sspec) {
        this.spec = sspec;
    }
    public Qualification getQualif() {
        return this.qualif;
    }
    public void setQualif(Qualification qqualif) {
        this.qualif = qqualif;
    }
    public int getQSub() {
        return this.qntsubject;
    }
    public void setQSub(int i) {
        this.qntsubject = i;
    }
    public Subject[] showSubjectList() { // возращает массив субъектов
        return this.subjects;
    }
    public boolean addSubject(Subject ss) {  //добавляет субъект в список спеца
        if (this.spec == ss.getSpec()) {
            this.subjects[qntsubject++] = ss;
            System.out.println(this.getObSpec() + " " + this.getName() + " берет " + ss.getWho() + " " + ss.getName());
            return true;
        }
        System.out.println(this.getObSpec() + " " + this.getName() + " НЕ берет " + ss.getWho() + " " + ss.getName());
        return false;
    }
    public boolean delSubject(Subject ss) { //удаляет субъекта из списка спеца
        for (int i = 0; i <= qntsubject; i++) {
            if (subjects[i].equals(ss)) {
                for (int j = i; j <= qntsubject - 1; j++) {
                    subjects[j] = subjects[j + 1];
                }
                System.out.println(this.getObSpec() + " " + this.getName() + " отказывается от " + ss.getWho() + " " + ss.getName());
                return true;
            }
        }
        System.out.println(this.getObSpec() + " " + this.getName() + " НЕ может отказаться от " + ss.getWho() + " " + ss.getName());
        return false;
    }
    public boolean doSubject(Subject ss) {   // спец воздействует на субъект повышая его статус
        for (int i = 0; i <= qntsubject; i++) {
            if (subjects[i] != null && subjects[i].equals(ss)) {
                System.out.println(this.getObSpec() + " " + this.getName() + " " + this.getWork() + " " + ss.getWho() + " " + ss.getName());
                this.upStatusSubject(ss);
                return true;
            }
        }
        System.out.println(ss.getWho() + " " + ss.getName() + ": его нет в списках у" + this.getName());
        return false;
    }
    public Status statusSubject(Subject ss) {   //возращает статус субъекта из списка
        System.out.println("Приветствуем " + ss.getWho() + " " + ss.getName());
        System.out.println("Его состояние по специализации:" + ss.getSpec() + " " + ss.getStatus());
        return ss.getStatus();
    }
    public void clearSubjectList() { //очищает список субъектов у спеца
        Arrays.fill(this.subjects, null);
        System.out.println("Очищается список для: " + this.getObSpec() + " " + this.getName());
        this.qntsubject = 0;
    }

    public Status upStatusSubject(Subject ssubj) {   //повышает статус субъекта на след уровень
        switch (ssubj.getStatus()) {
            case чуть_чуть:
                ssubj.setStatus(Status.половина);
                break;
            case начало:
                ssubj.setStatus(Status.чуть_чуть);
                break;
            case половина:
                ssubj.setStatus(Status.почти_все);
                break;
            case почти_все:
                ssubj.setStatus(Status.конец);
                break;
            default: break;
        }
        System.out.println("Для  " + ssubj.getWho() + " " + ssubj.getName() + " изменение статуса: " + ssubj.getStatus());
        return ssubj.getStatus();
    }

    public Qualification upQualif() {    //повышает квалификацию спеца
        switch (this.qualif) {
            case стажер:
                this.qualif = Qualification.младший_специалист;
                break;
            case младший_специалист:
                this.qualif = Qualification.старший_специалист;
                break;
            case старший_специалист:
                this.qualif = Qualification.бог;
                break;
            default: break;
        }
        System.out.println("Для " + this.getObSpec() + " " + this.getName() + " изменение квалификации на:" + this.getQualif());
        return this.qualif;
    }

}
