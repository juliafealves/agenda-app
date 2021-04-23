package br.com.juliafealves.agenda;

import android.app.Application;

import br.com.juliafealves.agenda.daos.StudentDAO;
import br.com.juliafealves.agenda.models.Student;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createData();
    }
    
    public void createData() {
        final StudentDAO studentDAO = new StudentDAO();
        studentDAO.save(new Student("Ada Lovelace", "(+55) 83 99999-0000", "ada@lovelace.com"));
    }
}
