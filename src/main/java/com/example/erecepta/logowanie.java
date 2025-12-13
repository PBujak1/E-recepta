package com.example.erecepta;

public class logowanie {
    String login;
    String password;
    String imie;
    String nazwisko;

    logowanie(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getImie(String login) {
        if (login.equals(this.login)) {
            return "imie";
        } else {
            return "error";
        }
    }

    public String getNazwisko(String login) {
        if (login.equals(this.login)) {
            return "nazwisko";
        }
        return "error";
    }
}
