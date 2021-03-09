package br.com.juliafealves.agenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.juliafealves.agenda.R;
import br.com.juliafealves.agenda.daos.StudentDAO;

public class StudentsListActivity extends AppCompatActivity {
    private static final String TITLE = "Students List";
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        setTitle(TITLE);
        configureButtonAdd();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configureStudentsList();
    }

    private void configureButtonAdd() {
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(view -> openStudentsForm());
    }

    private void configureStudentsList() {
        ListView ltvStudents = findViewById(R.id.ltv_students);
        ltvStudents.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.findAll()));
        ltvStudents.setOnItemClickListener((parent, view, position, id) -> Log.i("POSITION", position + ""));
    }

    private void openStudentsForm() {
        startActivity(new Intent(StudentsListActivity.this, StudentsFormActivity.class));
    }
}
