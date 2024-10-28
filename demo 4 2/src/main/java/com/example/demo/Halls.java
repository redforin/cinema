package com.example.demo;

public class Halls {
    public int number;
    public int seats;
    public String format;

    public Halls(int number, int seats, String format) {
        this.setNumber(number);
        this.setSeats(seats);
        this.setFormat(format);
    }

    @Override
    public String toString() {
        return String.format(String.valueOf(this.getNumber()), this.getSeats(), this.getFormat());
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
