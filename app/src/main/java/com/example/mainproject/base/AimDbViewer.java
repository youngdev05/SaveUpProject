package com.example.mainproject.base;

public class AimDbViewer {
    String aim;
    int neededMoney;
    int id;
    int currentMoney;

    public AimDbViewer(int id, String aim, int neededMoney, int currentMoney) {
        this.aim = aim;
        this.neededMoney = neededMoney;
        this.id = id;
        this.currentMoney = currentMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public int getNeededMoney() {
        return neededMoney;
    }

    public void setNeededMoney(int neededMoney) {
        this.neededMoney = neededMoney;
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(int currentMoney) {
        this.currentMoney = currentMoney;
    }
}
