package com.example.erecepta;

import javafx.scene.image.Image;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Base64;

public class ServerConnection {

    private static final String server = "localhost";
    private static final int port = 12345;
    private String PESEL;
    private String haslo;
    private String response;
    private StringBuilder responseBuilder = new StringBuilder();
    private String IDlekarza;

    public ServerConnection(String PESEL, String haslo) {
        this.PESEL = PESEL;
        this.haslo = haslo;
    }

    public String getPacjent(String data, String PESEL) throws IOException {
        try {
            Socket socket = new Socket(server, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            switch (data) {
                case "loginPacjent":
                    out.println("loginPacjent");
                    out.println(PESEL);
                    out.println(haslo);

                    String response = in.readLine();

                    if ("BRAK_DANYCH".equals(response)) {
                        return "Brak danych";
                    }

                    String imie = response;
                    String nazwisko = in.readLine();

                    return imie + " " + nazwisko;
                case "loginLekarz":
                    out.println("loginLekarz");
                    out.println(PESEL);
                    out.println(haslo);

                    response = in.readLine();

                    if ("BRAK_DANYCH".equals(response)) {
                        return "Brak danych";
                    }

                    imie = response;
                    nazwisko = in.readLine();

                    return imie + " " + nazwisko;
                case "getImiePacjent":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getNazwiskoPacjent":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getImieLekarz":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getNazwiskoLekarz":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getAlergia":
                    out.println(data);
                    out.println(PESEL);
                    String line;
                    StringBuilder response1 = new StringBuilder();

                    while (!(line = in.readLine()).equals("END")) {
                        response1.append(line + "\n");
                    }
                    System.out.println(response1);
                    return response1.toString();
                case "getIDLekarza":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getEmail":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getAdres":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getTelefon":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getPlec":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getWiek":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getEmailLekarz":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getAdresLekarz":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getTelefonLekarz":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getPlecLekarz":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getWiekLekarz":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getRecepta":
                    out.println(data);
                    out.println(PESEL);
                    responseBuilder = new StringBuilder();

                    while (!(response = in.readLine()).equals("END")) {
                        responseBuilder.append(response).append("\n");
                    }
                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                case "getWizyta":
                    out.println(data);
                    out.println(PESEL);
                    responseBuilder = new StringBuilder();

                    while (!(response = in.readLine()).equals("END")) {
                        responseBuilder.append(response).append("\n");
                    }
                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                case "getWizytaPacjenta":
                    out.println(data);
                    out.println(PESEL);
                    responseBuilder = new StringBuilder();

                    while (!(response = in.readLine()).equals("END")) {
                        responseBuilder.append(response).append("\n");
                    }
                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                case "getHistoriaWizyt":
                    out.println(data);
                    out.println(PESEL);
                    responseBuilder = new StringBuilder();

                    while (!(response = in.readLine()).equals("END")) {
                        responseBuilder.append(response).append("\n");
                    }
                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                case "getNadchodzaceWizyty":
                    out.println(data);
                    out.println(PESEL);
                    responseBuilder = new StringBuilder();

                    while (!(response = in.readLine()).equals("END")) {
                        responseBuilder.append(response).append("\n");
                    }
                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                case "getHistoria":
                    out.println(data);
                    out.println(PESEL);
                    responseBuilder = new StringBuilder();

                    while (!(response = in.readLine()).equals("END")) {
                        responseBuilder.append(response).append("\n");
                    }
                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                case "getHistoriaPac":
                    out.println(data);
                    out.println(PESEL);
                    responseBuilder = new StringBuilder();

                    while (!(response = in.readLine()).equals("END")) {
                        responseBuilder.append(response).append("\n");
                    }
                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                case "getLekarzePacjenta":
                    out.println(data);
                    out.println(PESEL);
                    responseBuilder = new StringBuilder();
                    while (!(line = in.readLine()).equals("END")) {
                        responseBuilder.append(line).append("\n");
                    }
                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                case "getLekarze":
                    out.println(data);
                    responseBuilder = new StringBuilder();

                    while (!(line = in.readLine()).equals("END")) {
                        responseBuilder.append(line).append("\n");
                    }

                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                case "getLeki":
                    out.println(data);
                    responseBuilder = new StringBuilder();

                    while (!(line = in.readLine()).equals("END")) {
                        responseBuilder.append(line).append("\n");
                    }

                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                case "imageSetter":
                    out.println(data);
                    out.println(PESEL);
                    out.println(haslo);
                    responseBuilder = new StringBuilder();

                    while (!(line = in.readLine()).equals("END")) {
                        responseBuilder.append(line).append("\n");
                    }

                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                case "imageGetter":
                    out.println(data);
                    out.println(PESEL);
                    out.println(haslo);
                    responseBuilder = new StringBuilder();

                    while (!(line = in.readLine()).equals("END")) {
                        responseBuilder.append(line).append("\n");
                    }

                    response = responseBuilder.toString();
                    System.out.println(response);
                    return response;
                default:
                    response = "Valid request!";
                    System.out.println(response);
                    return response;
            }
        } catch (IOException e) {
            return "Brak danych";
        }
    }


    public void getUpdateLek(String data, String PESEL, String noweImie, String noweNazwisko
    , String nowyPesel, String nowyAdres, String nowyTelefonStr, String nowyEmail, String nowyWiek, String nowaPlec) throws IOException {
        try {
            Socket socket = new Socket(server, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            int nowyTelefon = Integer.parseInt(nowyTelefonStr);

            switch (data) {
                case "updateWszystkoLek":
                    out.println(data);
                    out.println(PESEL);
                    out.println(noweImie);
                    out.println(noweNazwisko);
                    out.println(nowyPesel);
                    out.println(nowyAdres);
                    out.println(nowyTelefon);
                    out.println(nowyEmail);
                    out.println(nowyWiek);
                    out.println(nowaPlec);

                default:
                    response = "Valid request!";
                    System.out.println(response);
            }

        } catch (IOException e) {
            System.out.println( "Brak danych" + e.getMessage());
        }
    }

    public void getNowyLek(String data, String noweImie, String noweNazwisko, String PZW
            , String nowyPesel, String nowyAdres, String nowyTelefonStr, String nowyEmail, String nowyWiek, String nowaPlec) throws IOException {
        try {
            Socket socket = new Socket(server, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            int nowyTelefon = Integer.parseInt(nowyTelefonStr);

            switch (data) {
                case "nowyLek":
                    System.out.println("ok");
                    out.println(data);
                    out.println(noweImie);
                    out.println(noweNazwisko);
                    out.println(PZW);
                    out.println(nowyPesel);
                    out.println(nowyAdres);
                    out.println(nowyTelefon);
                    out.println(nowyEmail);
                    out.println(nowyWiek);
                    out.println(nowaPlec);
                    break;

                default:
                    response = "Valid request!";
                    System.out.println(response);
            }

        } catch (IOException e) {
            System.out.println( "Brak danych" + e.getMessage());
        }
    }

    public void getUpdateRec(String data, String PESEL, String lek, String opakowania
    , String odplatnosc, String PESELLek) throws IOException {
        try {
            Socket socket = new Socket(server, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            switch (data) {
                case "updateWszystkoRec":
                    System.out.println("tu działa");
                    out.println(data);
                    out.println(PESEL);
                    out.println(lek);
                    out.println(opakowania);
                    out.println(odplatnosc);
                    out.println(PESELLek);

            default:
                    response = "Valid request!";
                    System.out.println(response);
            }

        } catch (IOException e) {
            System.out.println( "Brak danych" + e.getMessage());
        }
    }

    public String getLekarz(String data, String imie, String nazwisko) throws IOException {
        try {
            Socket socket = new Socket(server, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            switch (data) {
                case "getEmailLekarza":
                    out.println(data);
                    out.println(imie);
                    out.println(nazwisko);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getPZWLekarza":
                    out.println(data);
                    out.println(imie);
                    out.println(nazwisko);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getTelefonLekarza":
                    out.println(data);
                    out.println(imie);
                    out.println(nazwisko);

                    response = in.readLine();
                    System.out.println(response);
                    return response;

                default:
                    response = "Valid request!";
                    System.out.println(response);
            }
        } catch (IOException e) {
            System.out.println( "Brak danych" + e.getMessage());
        }
        return response;
    }

    public void setImage(String data, File file, int idLekarza) throws IOException {
        try {
            Socket socket = new Socket(server, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("imageSetter");

            byte[] imageBytes = Files.readAllBytes(file.toPath());
            String base64 = Base64.getEncoder().encodeToString(imageBytes);

            out.println(idLekarza);
            out.println(base64);
        } catch (IOException e) {
            System.out.println( "Brak danych" + e.getMessage());
        }
    }

    public Image getImage(String data, int idLekarza) throws IOException {
        try {
            Socket socket = new Socket(server, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("imageGetter");
            out.println(idLekarza);
            String base64 = in.readLine();
            byte[] imageBytes = Base64.getDecoder().decode(base64);
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            return image;

        } catch (IOException e) {
            System.out.println( "Brak danych" + e.getMessage());
        }
        return null;
    }
}
