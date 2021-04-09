package br.com.juliafealves.agenda.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.juliafealves.agenda.R;
import br.com.juliafealves.agenda.daos.StudentDAO;
import br.com.juliafealves.agenda.models.Student;

public class StudentsFormActivity extends AppCompatActivity {
    private static final String TITLE_NEW = "New Student";
    private static final String TITLE_EDIT = "Edit Student";

    private final StudentDAO studentDAO = new StudentDAO();
    private Student student;
    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_form);
        initializeFields();
        loadStudent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_students_form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mi_save) {
            finishForm();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeFields() {
        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);
        edtEmail = findViewById(R.id.edt_email);
    }

    private void loadStudent() {
        if (getIntent().hasExtra(ConstantsActivities.KEY_STUDENT)) {
            setTitle(TITLE_EDIT);
            student = (Student) getIntent().getSerializableExtra(ConstantsActivities.KEY_STUDENT);
            fillForm();
        } else {
            setTitle(TITLE_NEW);
            student = new Student();
        }
    }

    private void fillStudent() {
        student.setName(edtName.getText().toString());
        student.setPhone(edtPhone.getText().toString());
        student.setEmail(edtEmail.getText().toString());
    }

    private void fillForm() {
        edtName.setText(student.getName());
        edtEmail.setText(student.getEmail());
        edtPhone.setText(student.getPhone());
    }

    private void finishForm() {
        fillStudent();

        if(student.isValidId()) {
            studentDAO.edit(student);
        } else {
            studentDAO.save(student);
        }

        finish();
    }
}