package com.example.demo;

public class Visitors {
    public int idVisitor;
    public String surname;
    public String name;
    public String middleName;
    public String phoneNumber;
    public int ticketsNumber;

    public Visitors(int idVisitor, String surname, String name, String middleName, String phoneNumber, int ticketsNumber) {
        this.setIdVisitor(idVisitor);
        this.setSurname(surname);
        this.setName(name);
        this.setMiddleName(middleName);
        this.setPhoneNumber(phoneNumber);
        this.setTicketsNumber(ticketsNumber);
    }

    @Override
    public String toString() {
        return String.format(String.valueOf(this.getIdVisitor()), this.getSurname(), this.getName(), this.getMiddleName(), this.getPhoneNumber(),
                this.getTicketsNumber());
    }

    public int getIdVisitor() {
        return idVisitor;
    }

    public void setIdVisitor(int idVisitor) {
        this.idVisitor = idVisitor;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTicketsNumber() {
        return ticketsNumber;
    }

    public void setTicketsNumber(int ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

}
