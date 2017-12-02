package net.twse.telnettest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private final int TELNET_PORT = 23;
    private Socket socket;
    private final String LOGIN = "a.pokazanov";
    private final String PASSWORD = "celokoD)";

    Client(String ip) throws IOException {
        this.socket = new Socket(ip, TELNET_PORT);
    }

    void login() throws IOException {
        if (socket.isConnected()) {
            System.out.println("Socket connected to " + socket.getInetAddress() + ":" + socket.getPort());
            InputStream sockin = socket.getInputStream();
            OutputStream sockout = socket.getOutputStream();
            while (sockin.read() != -1)
                System.out.println(sockin.read());
            sockin.close();
            sockout.close();
        }
    }
}
