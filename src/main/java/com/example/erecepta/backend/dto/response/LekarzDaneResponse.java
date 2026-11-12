package com.example.erecepta.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LekarzDaneResponse {

    @JsonProperty("idLekarza")
    @JsonAlias({"id", "IDLekarza", "idlekarza"})
    private String idLekarza;

    @JsonProperty("imie")
    @JsonAlias({"Imie"})
    private String imie;

    @JsonProperty("nazwisko")
    @JsonAlias({"Nazwisko"})
    private String nazwisko;

    @JsonProperty("pesel")
    @JsonAlias({"Pesel", "PESEL"})
    private String pesel;

    @JsonProperty("adres")
    @JsonAlias({"Adres", "adresLekarz", "AdresLekarz"})
    private String adres;

    @JsonProperty("telefon")
    @JsonAlias({"numerTelefonu", "NumerTelefonu", "Telefon", "telefonLekarz", "TelefonLekarz"})
    private String telefon;

    @JsonProperty("email")
    @JsonAlias({"Email", "emailLekarz", "EmailLekarz"})
    private String email;

    @JsonProperty("wiek")
    @JsonAlias({"Wiek", "wiekLekarz", "WiekLekarz"})
    private String wiek;

    @JsonProperty("plec")
    @JsonAlias({"Plec", "plecLekarz", "PlecLekarz"})
    private String plec;

    @JsonProperty("nrPzw")
    @JsonAlias({"NrPzw", "nrPZW", "NrPZW"})
    private String nrPzw;

    @JsonProperty("specjalizacja")
    @JsonAlias({"Specjalizacja"})
    private String specjalizacja;

    @JsonProperty("opis")
    @JsonAlias({"Opis"})
    private String opis;

    public LekarzDaneResponse() {
    }

    public LekarzDaneResponse(String idLekarza, String imie, String nazwisko, String pesel, String adres,
                              String telefon, String email, String wiek, String plec) {
        this.idLekarza = idLekarza;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.adres = adres;
        this.telefon = telefon;
        this.email = email;
        this.wiek = wiek;
        this.plec = plec;
    }

    public String getIdLekarza() {
        return idLekarza;
    }

    public void setIdLekarza(String idLekarza) {
        this.idLekarza = idLekarza;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNumerTelefonu() {
        return telefon;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.telefon = numerTelefonu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWiek() {
        return wiek;
    }

    public void setWiek(String wiek) {
        this.wiek = wiek;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getNrPzw() {
        return nrPzw;
    }

    public void setNrPzw(String nrPzw) {
        this.nrPzw = nrPzw;
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
