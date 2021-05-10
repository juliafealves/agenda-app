package br.com.juliafealves.agenda.daos;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.juliafealves.agenda.models.Student;

public class StudentDAO {
    private final static List<Student> students = new ArrayList<>();
    private static int countId = 1;

    public void save(Student student) {
        student.setId(countId);
        students.add(student);
        incrementId();
    }

    public void edit(Student student) {
        Student studentFound = findById(student.getId());

        if(studentFound != null) {
            int position = students.indexOf(studentFound);
            students.set(position, student);
        }
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Nullable
    public Student findById(int id) {
        for (Student student : students) {
            if(student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    private void incrementId() {
        countId++;
    }

    public void removeById(int id) {
        Student student = findById(id);

        if(student != null) {
            students.remove(student);
        }
    }
}
