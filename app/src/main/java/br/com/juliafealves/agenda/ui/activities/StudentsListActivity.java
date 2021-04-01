package br.com.juliafealves.agenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_students_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mi_remove) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Student student = adapter.getItem(menuInfo.position);
            removeStudent(student);
        }

        return super.onContextItemSelected(item);
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
        registerForContextMenu(ltvStudents);
    }

    private void configureStudentListAdapter(ListView listView) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
    }

    private void configureStudentListItemClickListener(ListView listView) {
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) ->
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

    private void removeStudent(Student student) {
        studentDAO.removeById(student.getId());
        adapter.remove(student);
    }
}
