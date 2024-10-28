package com.example.demo;

public class WorkersHasHalls {
    public int kod;
    public int number;

    public WorkersHasHalls(int kod, int number) {
        this.setKod(kod);
        this.setNumber(number);
    }

    @Override
    public String toString() {
        return String.format(String.valueOf(this.getKod()), this.getNumber());
    }

    public int getKod() {
        return kod;
    }

    public void setKod(int kod) {
        this.kod = kod;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
