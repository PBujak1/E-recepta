package com.example.erecepta.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WizytyResponse {

    @JsonProperty("idWizyty")
    @JsonAlias({"id", "IDWizyty", "id_wizyty"})
    private String idWizyty;

    @JsonProperty("dataWizyty")
    @JsonAlias({"data", "DataWizyty", "data_wizyty"})
    private String dataWizyty;

    @JsonProperty("nazwaLekarza")
    @JsonAlias({"lekarz", "NazwaLekarza", "imieNazwiskoLekarza"})
    private String nazwaLekarza;

    @JsonProperty("idLekarza")
    @JsonAlias({"IDLekarza", "lekarzId"})
    private String idLekarza;

    @JsonProperty("nazwaPacjenta")
    @JsonAlias({"pacjent", "NazwaPacjenta", "imieNazwiskoPacjenta"})
    private String nazwaPacjenta;

    @JsonProperty("idPacjenta")
    @JsonAlias({"IDPacjenta", "pacjentId"})
    private String idPacjenta;

    @JsonProperty("nrRecepty")
    @JsonAlias({"IDRecepty", "idRecepty", "receptaId", "NrRecepty"})
    private String nrRecepty;

    public WizytyResponse() {
    }

    public WizytyResponse(
            String dataWizyty,
            String nazwaLekarza,
            String nazwaPacjenta,
            String nrRecepty
    ) {
        this.dataWizyty = dataWizyty;
        this.nazwaLekarza = nazwaLekarza;
        this.nazwaPacjenta = nazwaPacjenta;
        this.nrRecepty = nrRecepty;
    }

    public WizytyResponse(
            String idWizyty,
            String dataWizyty,
            String idLekarza,
            String idPacjenta,
            String nrRecepty
    ) {
        this.idWizyty = idWizyty;
        this.dataWizyty = dataWizyty;
        this.idLekarza = idLekarza;
        this.idPacjenta = idPacjenta;
        this.nrRecepty = nrRecepty;
    }

    public String getIdWizyty() {
        return idWizyty;
    }

    public void setIdWizyty(String idWizyty) {
        this.idWizyty = idWizyty;
    }

    public String getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(String dataWizyty) {
        this.dataWizyty = dataWizyty;
    }

    public String getNazwaLekarza() {
        return nazwaLekarza;
    }

    public void setNazwaLekarza(String nazwaLekarza) {
        this.nazwaLekarza = nazwaLekarza;
    }

    public String getIdLekarza() {
        return idLekarza;
    }

    public void setIdLekarza(String idLekarza) {
        this.idLekarza = idLekarza;
    }

    public String getNazwaPacjenta() {
        return nazwaPacjenta;
    }

    public void setNazwaPacjenta(String nazwaPacjenta) {
        this.nazwaPacjenta = nazwaPacjenta;
    }

    public String getIdPacjenta() {
        return idPacjenta;
    }

    public void setIdPacjenta(String idPacjenta) {
        this.idPacjenta = idPacjenta;
    }

    public String getNrRecepty() {
        return nrRecepty;
    }

    public void setNrRecepty(String nrRecepty) {
        this.nrRecepty = nrRecepty;
    }

}
