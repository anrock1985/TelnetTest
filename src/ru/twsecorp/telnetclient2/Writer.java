package ru.twsecorp.telnetclient2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Writer {
    private PrintWriter out;

    Writer(Socket clientSocket) {
        try {
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void writeLine(String line) {
        out.println(line);
    }
}