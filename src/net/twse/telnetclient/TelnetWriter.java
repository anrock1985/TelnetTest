package net.twse.telnetclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TelnetWriter {
    private PrintWriter out;

    TelnetWriter(Socket socket) throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    void write(String command) {
        out.write(command);
    }
}
