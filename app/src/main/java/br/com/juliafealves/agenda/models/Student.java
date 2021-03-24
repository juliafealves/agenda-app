package br.com.juliafealves.agenda.models;


import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {
    private int id = 0;
    private String name;
    private String phone;
    private String email;

    public Student() {

    }

    public Student(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public boolean isValidId() {
        return id > 0;
    }
}
