package br.com.juliafealves.agenda.daos;

import java.util.ArrayList;
import java.util.List;

import br.com.juliafealves.agenda.models.Student;

public class StudentDAO {
    private final static List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }
}
