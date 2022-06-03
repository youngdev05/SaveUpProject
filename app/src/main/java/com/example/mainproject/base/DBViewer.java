package com.example.mainproject.base;

public class DBViewer {
    String category;
    String date;
    int expenses;
    int id;


    public DBViewer(int id, String date, String category, int expenses) {
        this.category = category;
        this.date = date;
        this.expenses = expenses;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
