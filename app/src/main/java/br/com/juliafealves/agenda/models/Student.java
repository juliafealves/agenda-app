package br.com.juliafealves.agenda.models;


import androidx.annotation.NonNull;

public class Student {
    private final String name;
    private final String phone;
    private final String email;

    public Student(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
