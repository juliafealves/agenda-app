package br.com.juliafealves.agenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.juliafealves.agenda.R;
import br.com.juliafealves.agenda.daos.StudentDAO;
import br.com.juliafealves.agenda.models.Student;

public class StudentsListActivity extends AppCompatActivity {
    private static final String TITLE = "Students List";
    private final StudentDAO studentDAO = new StudentDAO();
    private ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        setTitle(TITLE);
        configureButtonAdd();
        configureStudentsList();
        studentDAO.save(new Student("Júlia", "998021522", "juliafealves@gmail.com"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList() {
        adapter.clear();
        adapter.addAll(studentDAO.findAll());
    }

    private void configureButtonAdd() {
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(view -> openAddForm());
    }

    private void configureStudentsList() {
        ListView ltvStudents = findViewById(R.id.ltv_students);
        configureStudentListAdapter(ltvStudents);
        configureStudentListItemClickListener(ltvStudents);
        configureStudentListItemLongClickListener(ltvStudents);
    }

    private void configureStudentListAdapter(ListView listView) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
    }

    private void configureStudentListItemClickListener(ListView listView) {
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) ->
                openEditForm((Student) parent.getItemAtPosition(position)));
    }

    private void configureStudentListItemLongClickListener(ListView listView) {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) parent.getItemAtPosition(position);
                StudentsListActivity.this.removeStudent(student);

                return true;
            }
        });
    }

    private void openAddForm() {
        startActivity(new Intent(StudentsListActivity.this, StudentsFormActivity.class));
    }

    private void openEditForm(Student student) {
        Intent intent = new Intent(StudentsListActivity.this, StudentsFormActivity.class);
        intent.putExtra(ConstantsActivities.KEY_STUDENT, student);
        startActivity(intent);
    }

    private void removeStudent(Student student) {
        studentDAO.removeById(student.getId());
        adapter.remove(student);
    }
}
