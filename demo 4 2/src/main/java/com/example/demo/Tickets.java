package com.example.demo;

public class Tickets {
    public int number;
    public int price;

    public Tickets(int number, int price) {
        this.setNumber(number);
        this.setPrice(price);
    }

    @Override
    public String toString() {
        return String.format(String.valueOf(this.getNumber()), this.getPrice());
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
