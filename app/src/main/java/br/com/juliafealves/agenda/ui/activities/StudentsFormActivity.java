package br.com.juliafealves.agenda.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.juliafealves.agenda.R;
import br.com.juliafealves.agenda.daos.StudentDAO;
import br.com.juliafealves.agenda.models.Student;

public class StudentsFormActivity extends AppCompatActivity {
    private static final String TITLE = "New Student";
    private final StudentDAO studentDAO = new StudentDAO();
    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_form);
        setTitle(TITLE);
        initializeFields();
        configureButtonSave();
    }

    private void initializeFields() {
        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);
        edtEmail = findViewById(R.id.edt_email);
    }

    private void configureButtonSave() {
        final Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(view -> save(createStudent()));
    }

    private void save(Student student) {
        studentDAO.save(student);
        finish();
    }

    @NonNull
    private Student createStudent() {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();

        return new Student(name, phone, email);
    }
}