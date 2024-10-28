package com.example.demo;

public class Workers {
    public int kod;
    public String surname;
    public String name;
    public String middleName;
    public String post;
    public String phoneNumber;
    public String adress;
    public int passportSeries;
    public int passportNumber;

    public Workers(int kod, String surname, String name, String middleName, String post, String phoneNumber, String adress, int passportSeries, int passportNumber) {
        this.setKod(kod);
        this.setSurname(surname);
        this.setName(name);
        this.setMiddleName(middleName);
        this.setPost(post);
        this.setPhoneNumber(phoneNumber);
        this.setAdress(adress);
        this.setPassportSeries(passportSeries);
        this.setPassportNumber(passportNumber);
    }

    @Override
    public String toString() {
        return String.format(String.valueOf(this.getKod()), this.getSurname(), this.getName(), this.getMiddleName(), this.getPost(), this.getPhoneNumber(),
                this.getAdress(), this.getPassportSeries(), this.getPassportNumber());
    }

    public int getKod() {
        return kod;
    }

    public void setKod(int kod) {
        this.kod = kod;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(int passportSeries) {
        this.passportSeries = passportSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

}
