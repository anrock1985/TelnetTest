package net.twse.telnetclient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class TelnetWriter {
    private BufferedWriter out;

    TelnetWriter(Socket socket) throws IOException {
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    void write(String command) throws IOException {
        out.write(command);
    }
}