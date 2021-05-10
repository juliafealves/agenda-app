package br.com.juliafealves.agenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.juliafealves.agenda.R;
import br.com.juliafealves.agenda.models.Student;

public class StudentListActivity extends AppCompatActivity {
    private static final String TITLE = "Students List";
    private final StudentListView studentListView = new StudentListView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        setTitle(TITLE);
        configureButtonAdd();
        configureStudentsList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_students_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mi_remove) {
            studentListView.confirmRemove(item);
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentListView.refreshList();
    }

    private void configureButtonAdd() {
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(view -> openAddForm());
    }

    private void configureStudentsList() {
        ListView ltvStudents = findViewById(R.id.ltv_students);
        studentListView.configureStudentListAdapter(ltvStudents);
        configureStudentListItemClickListener(ltvStudents);
        registerForContextMenu(ltvStudents);
    }

    private void configureStudentListItemClickListener(ListView listView) {
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) ->
                openEditForm((Student) parent.getItemAtPosition(position)));
    }

    private void openAddForm() {
        startActivity(new Intent(StudentListActivity.this, StudentFormActivity.class));
    }

    private void openEditForm(Student student) {
        Intent intent = new Intent(StudentListActivity.this, StudentFormActivity.class);
        intent.putExtra(ConstantsActivities.KEY_STUDENT, student);
        startActivity(intent);
    }
}
