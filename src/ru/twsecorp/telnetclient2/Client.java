package ru.twsecorp.telnetclient2;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private Reader reader;
    private Writer writer;
    private Socket socket;
    private Debugger debugger;

    public Client(String ip) {
        try {
            socket = new Socket(ip, 23);
            socket.setSoTimeout(500);
            reader = new Reader(socket);
            writer = new Writer(socket);
            debugger = new Debugger();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void read() {
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (socket.isConnected() && line != null) {
            sb.append(line).append("\n");
            line = reader.readLine();
        }
        System.out.println(sb);
    }

    public void login() {
        read();
        writer.writeLine("\n");
        read();
        writer.writeLine("3");
        read();
        writer.writeLine("2");
        read();
        writer.writeLine("1");
        read();
        writer.writeLine("ON");
        read();
    }
}
