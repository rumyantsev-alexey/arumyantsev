package ru.job4j.professions;

/*
    Не совсем понял как написать тест для всего проекта а не для отдельных классов
    поэтому здесть мейн с несколькими примерами использования
 */
import static ru.job4j.professions.Profession.Special.*;
import static ru.job4j.professions.Profession.Qualification.*;
import static ru.job4j.professions.Profession.Status.*;

public class Sample {

    public static void main(String[] args) {
        Teacher iv = new Teacher("Иван Иванович",  25, true, история, стажер);
        Doctor vi = new Doctor("Петр Петрович", 37,  true, хирург, стажер);
        Engeneer oo = new Engeneer("Олег", 22, false, электрик, старший_специалист);
        Student iv1 = new Student("Марсик", 18, true, история, начало);
        Patient vi1 = new Patient("Ольга Павловна", 66, false, лор, чуть_чуть);
        Project oo1 = new Project("Проект электрики здания", 0, false, электрик, половина);
        iv.upQualif(); // повышение квалификации Учителя
        iv.doSubject(iv1); // попытка обучить Учителем Студента, которого нет в списке
        iv.doSubject(oo1); // попытка обучить Учителем Проект
        iv.addSubject(iv1); // добавление Студента в список Учителя
        iv.addSubject(vi1); // попытка добавить в список Учителя Пациента
        iv.doSubject(iv1); // обучение Учителем Студента из списка
    }
}
