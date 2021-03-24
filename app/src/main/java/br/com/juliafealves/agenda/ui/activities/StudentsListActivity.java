package br.com.juliafealves.agenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        setTitle(TITLE);
        configureButtonAdd();
        studentDAO.save(new Student("Júlia", "998021522", "juliafealves@gmail.com"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configureStudentsList();
    }

    private void configureButtonAdd() {
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(view -> openAddForm());
    }

    private void configureStudentsList() {
        ListView ltvStudents = findViewById(R.id.ltv_students);
        configureStudentListAdapter(ltvStudents, studentDAO.findAll());
        configureStudentListItemClickListener(ltvStudents);
    }

    private void configureStudentListAdapter(ListView listView, List<Student> students) {
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                students));
    }

    private void configureStudentListItemClickListener(ListView listView) {
        listView.setOnItemClickListener((parent, view, position, id) ->
                openEditForm((Student) parent.getItemAtPosition(position)));
    }

    private void openAddForm() {
        startActivity(new Intent(StudentsListActivity.this, StudentsFormActivity.class));
    }

    private void openEditForm(Student student) {
        Intent intent = new Intent(StudentsListActivity.this, StudentsFormActivity.class);
        intent.putExtra(ConstantsActivities.KEY_STUDENT, student);
        startActivity(intent);
    }
}
