package br.com.juliafealves.agenda.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.juliafealves.agenda.R;
import br.com.juliafealves.agenda.models.Student;

public class StudentsListAdapter extends BaseAdapter {
    private final List<Student> students = new ArrayList<>();
    private final Context context;

    public StudentsListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = createView(parent);
        Student student = getItem(position);
        personalizeAdapter(view, student);

        return view;
    }

    public void refresh(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
        notifyDataSetChanged();
    }

    public void remove(Student student) {
        students.remove(student);
        notifyDataSetChanged();
    }

    private View createView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_listview_student, viewGroup, false);
    }

    private void personalizeAdapter(View view, Student student) {
        TextView txvName = view.findViewById(R.id.txv_name);
        TextView txvPhone = view.findViewById(R.id.txv_phone);
        txvName.setText(student.getName());
        txvPhone.setText(student.getPhone());
    }
}
