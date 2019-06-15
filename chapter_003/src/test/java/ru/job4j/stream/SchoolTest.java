package ru.job4j.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SchoolTest {
    private List<Student> students = new ArrayList<>();
    private School school = new School();

    @Before
    public  void generateStudent() {
        Random rnd = new Random();
        students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            students.add(new Student("Student" + rnd.nextInt(101), rnd.nextInt(101)));
        }
    }

    @Test
    public void getStudentsIn10A() {
        List<Student> result = school.collect(students, x -> (x.getScore() >= 70) && (x.getScore() <= 100));
        assertTrue(result.get(1).getScore() >= 70 && result.get(1).getScore() <= 100);
    }

    @Test
    public void getStudentsIn10B() {
        List<Student> result = school.collect(students, x -> (x.getScore() >= 50) && (x.getScore() < 70));
        assertTrue(result.get(1).getScore() >= 50 && result.get(1).getScore() < 70);
    }

    @Test
    public void getStudentsIn10V() {
        List<Student> result = school.collect(students, x -> (x.getScore() >= 0) && (x.getScore() < 50));
        assertTrue(result.get(1).getScore() >= 0 && result.get(1).getScore() < 50);
    }
}