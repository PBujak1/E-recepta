package com.example.erecepta.backend;

public class LoginRequest {

    private String nazwisko;
    private String PESEL;

    public LoginRequest() {
    }

    public LoginRequest(String nazwisko, String PESEL) {
        this.nazwisko = nazwisko;
        this.PESEL = PESEL;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }
}
