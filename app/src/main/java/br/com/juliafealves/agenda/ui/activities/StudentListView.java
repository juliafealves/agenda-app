package br.com.juliafealves.agenda.ui.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.juliafealves.agenda.daos.StudentDAO;
import br.com.juliafealves.agenda.models.Student;
import br.com.juliafealves.agenda.ui.adapters.StudentListAdapter;

public class StudentListView {
    private final Context context;
    private final StudentDAO dao;
    private final StudentListAdapter adapter;

    public StudentListView(Context context) {
        this.context = context;
        this.dao = new StudentDAO();
        this.adapter = new StudentListAdapter(context);
    }

    public void configureStudentListAdapter(ListView listView) {
        listView.setAdapter(adapter);
    }

    public void confirmRemove(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removing student...")
                .setMessage("Are you sure you want to remove the student?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Student student = adapter.getItem(menuInfo.position);
                    remove(student);
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void refreshList() {
        adapter.refresh(dao.findAll());
    }

    private void remove(Student student) {
        dao.removeById(student.getId());
        adapter.remove(student);
    }
}
