package com.example.erecepta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection {

    private static final String server = "localhost";
    private static final int port = 12345;
    private String PESEL;
    private String haslo;
    private String response;
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
                        return "BRAK_DANYCH";
                    }

                    String imie = response;
                    String nazwisko = in.readLine();

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
                    StringBuilder responseBuilder = new StringBuilder();

                    while (!(response = in.readLine()).equals("END")) {
                        responseBuilder.append(response).append("\n");
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
            return "Brak danych" + e.getMessage();
        }
    }
}
