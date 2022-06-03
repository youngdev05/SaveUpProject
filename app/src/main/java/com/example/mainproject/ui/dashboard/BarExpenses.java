package com.example.mainproject.ui.dashboard;

public class BarExpenses {
    String date;
    int expenses;

    public BarExpenses(String date, int expenses) {
        this.date = date;
        this.expenses = expenses;
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

    public void setExpenses(int result) {
        this.expenses = result;
    }

}
