package com.example.erecepta.backend.client;

import com.example.erecepta.backend.dto.request.LekarzDaneRequest;
import com.example.erecepta.backend.dto.response.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LekarzService {

    private final ApiClient apiClient = new ApiClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public LekarzService() {
    }

    public List<PacjentLekarzResponse> szukajDanePacjenta(String pesel) throws Exception {
        String response = apiClient.get("/lekarz/" + pesel + "/danePacjenta");
        PacjentLekarzResponse[] lekarze = mapper.readValue(
                response,
                PacjentLekarzResponse[].class
        );
        return Arrays.stream(lekarze)
                .map(lekarz -> new PacjentLekarzResponse(
                        lekarz.getImieLekarza() + " " + lekarz.getNazwiskoLekarza()
                ))
                .toList();
    }

    /**
     * Pobiera pełne dane lekarza na podstawie numeru PESEL.
     */
    public LekarzDaneResponse getDaneLekarza(String pesel) throws Exception {
        String response = apiClient.get("/lekarz/" + pesel + "/dane");
        String trimmed = response.trim();

        if (trimmed.startsWith("[")) {
            LekarzDaneResponse[] lekarze = mapper.readValue(trimmed, LekarzDaneResponse[].class);
            return lekarze.length > 0 ? lekarze[0] : new LekarzDaneResponse();
        } else {
            return mapper.readValue(trimmed, LekarzDaneResponse.class);
        }
    }

    /**
     * Wyszukuje lekarza po frazie (imię + nazwisko) i zwraca jego pełne dane.
     * Używane przy wyborze lekarza w formularzu nowej wizyty.
     */
    public LekarzDaneResponse getDaneLekarzaByFraza(String fraza) throws Exception {
        String encoded = URLEncoder.encode(fraza, StandardCharsets.UTF_8);
        String response = apiClient.get("/pacjent/lekarze/szukaj?fraza=" + encoded + "&limit=1");
        String trimmed = response.trim();

        if (trimmed.startsWith("[")) {
            LekarzResponse[] lekarze = mapper.readValue(trimmed, LekarzResponse[].class);
            if (lekarze.length == 0) return new LekarzDaneResponse();
            LekarzResponse l = lekarze[0];
            LekarzDaneResponse dane = new LekarzDaneResponse();
            dane.setImie(l.getImie());
            dane.setNazwisko(l.getNazwisko());
            dane.setNrPzw(l.getNrPzw());
            dane.setEmail(l.getEmail());
            dane.setNumerTelefonu(l.getNumerTelefonu());
            dane.setOpis(l.getOpis());
            dane.setPesel(l.getPesel());
            return dane;
        } else if (trimmed.startsWith("{")) {
            LekarzResponse l = mapper.readValue(trimmed, LekarzResponse.class);
            LekarzDaneResponse dane = new LekarzDaneResponse();
            dane.setImie(l.getImie());
            dane.setNazwisko(l.getNazwisko());
            dane.setNrPzw(l.getNrPzw());
            dane.setEmail(l.getEmail());
            dane.setNumerTelefonu(l.getNumerTelefonu());
            dane.setOpis(l.getOpis());
            dane.setPesel(l.getPesel());
            return dane;
        }
        return new LekarzDaneResponse();
    }

    /**
     * Pobiera identyfikator ID lekarza po PESEL.
     */
    public String getIdLekarza(String pesel) throws Exception {
        try {
            String response = apiClient.get("/lekarz/" + pesel + "/id");
            String trimmed = response.trim();
            if (trimmed.startsWith("{")) {
                LekarzDaneResponse resp = mapper.readValue(trimmed, LekarzDaneResponse.class);
                return resp.getIdLekarza() != null ? resp.getIdLekarza() : "";
            }
            if (trimmed.startsWith("\"") && trimmed.endsWith("\"")) {
                return trimmed.substring(1, trimmed.length() - 1);
            }
            return trimmed;
        } catch (Exception e) {
            // W razie braku osobnego endpointu /id, pobieramy z profilu
            LekarzDaneResponse dane = getDaneLekarza(pesel);
            return dane != null && dane.getIdLekarza() != null ? dane.getIdLekarza() : "";
        }
    }


    public List<ReceptaResponse> getRecepty(String idLekarza) throws Exception {
        String response = apiClient.get("/lekarz/" + idLekarza + "/recepty");
        String trimmed = response.trim();

        if (trimmed.startsWith("[")) {
            ReceptaResponse[] array = mapper.readValue(trimmed, ReceptaResponse[].class);
            return new ArrayList<>(Arrays.asList(array));
        } else if (trimmed.startsWith("{")) {
            ReceptaResponse single = mapper.readValue(trimmed, ReceptaResponse.class);
            return List.of(single);
        } else {
            return List.of(new ReceptaResponse(trimmed));
        }
    }

    /**
     * Zwraca recepty lekarza w formacie tekstowym dla widoku 'mojeRecepty'.
     */
    public String getReceptyString(String idLekarza) throws Exception {
        List<ReceptaResponse> recepty = getRecepty(idLekarza);
        StringBuilder sb = new StringBuilder();
        for (ReceptaResponse r : recepty) {
            sb.append(r.formatRow()).append("\n");
        }
        return sb.toString();
    }


    /**
     * Pobiera historię wizyt lekarza na podstawie ID lekarza.
     */
    public List<WizytyResponse> getHistoria(String idLekarza) throws Exception {
        String response = apiClient.get("/lekarz/" + idLekarza + "/historia");
        String trimmed = response.trim();

        if (trimmed.startsWith("[")) {
            WizytyResponse[] array = mapper.readValue(trimmed, WizytyResponse[].class);
            return new ArrayList<>(Arrays.asList(array));
        } else if (trimmed.startsWith("{")) {
            WizytyResponse single = mapper.readValue(trimmed, WizytyResponse.class);
            return List.of(single);
        } else {
            return List.of(new WizytyResponse(trimmed, "", "", ""));
        }
    }

    /**
     * Zwraca historię wizyt w formacie tekstowym dla widoku 'historiaPac'.
     */
    public String getHistoriaString(String idLekarza) throws Exception {
        List<WizytyResponse> historia = getHistoria(idLekarza);

        StringBuilder sb = new StringBuilder();

        for (WizytyResponse w : historia) {
            if (w.getIdWizyty() != null) sb.append(w.getIdWizyty()).append(" ");
            if (w.getDataWizyty() != null) sb.append(w.getDataWizyty()).append(" ");
            if (w.getIdLekarza() != null) sb.append(w.getIdLekarza()).append(" ");
            if (w.getIdPacjenta() != null) sb.append(w.getIdPacjenta()).append(" ");
            if (w.getNrRecepty() != null) sb.append(w.getNrRecepty());

            sb.append("\n");
        }

        return sb.toString();
    }

    public List<LekarzResponse> szukajLekarzy(String fraza) throws Exception {
        String encoded = URLEncoder.encode(fraza, StandardCharsets.UTF_8);
        String response = apiClient.get("/pacjent/lekarze/szukaj?fraza=" + encoded + "&limit=10");
        LekarzResponse[] lekarze = mapper.readValue(response, LekarzResponse[].class);

        return Arrays.stream(lekarze)
                .map(lekarzResponse -> new LekarzResponse(
                        lekarzResponse.getImie(),
                        lekarzResponse.getNazwisko()))
                .toList();
    }

    public List<LekarzResponse> getSpecjalizacja(String fraza) throws Exception {
        String encoded = URLEncoder.encode(fraza, StandardCharsets.UTF_8);
        String response = apiClient.get("/pacjent/lekarze/specjalizacja/szukaj?fraza=" + encoded + "&limit=10");
        LekarzResponse[] lekarze = mapper.readValue(response, LekarzResponse[].class);

        return Arrays.stream(lekarze)
                .map(lekarzResponse -> new LekarzResponse(
                        lekarzResponse.getSpecjalizacja()))
                .toList();
    }

    public void updateDaneLekarza(LekarzDaneRequest request) throws Exception {
        String json = mapper.writeValueAsString(request);
        apiClient.post("/lekarz/update", json);
    }
}
