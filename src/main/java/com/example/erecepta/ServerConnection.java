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

    public ServerConnection(String PESEL, String haslo) {
        this.PESEL = PESEL;
        this.haslo = haslo;
    }

    public String getPacjent(String data, String PESEL) throws IOException {
        try {
            Socket socket = new Socket(server, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            switch (data){
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
                case "getImie":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
                    System.out.println(response);
                    return response;
                case "getNazwisko":
                    out.println(data);
                    out.println(PESEL);

                    response = in.readLine();
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
