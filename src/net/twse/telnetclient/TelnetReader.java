package net.twse.telnetclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TelnetReader {
    private BufferedReader in;

    TelnetReader(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    void readUntil(String target) throws IOException {
        String output = in.readLine();
        while (!output.equals(target)) {
            output = in.readLine();
            System.out.println(in.readLine());
        }
    }
}