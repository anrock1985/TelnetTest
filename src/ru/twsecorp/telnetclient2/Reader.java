package ru.twsecorp.telnetclient2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Reader {
    private BufferedReader in;

    Reader(Socket clientSocket) {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public String readLine() {
        int ch;
        StringBuilder line = null;
        try {
            for (ch = in.read(); ch != -1 && ch != 10; ch = in.read()) {
                if (line == null) {
                    line = new StringBuilder().append((char) ch);
                } else
                    line = line.append((char) ch);
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return (line == null) ? null : line.toString();
    }
}