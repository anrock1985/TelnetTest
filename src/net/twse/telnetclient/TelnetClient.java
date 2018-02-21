package net.twse.telnetclient;

import java.io.*;
import java.net.Socket;

class TelnetClient {
    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    Socket connect(String ip) {
        try {
            socket = new Socket(ip, 23);
        } catch (IOException e) {
            System.out.println("CONNECTION ERROR!");
        }
        return socket;
    }

    boolean isAlive() {
        return true;
    }

    void log(String s) throws FileNotFoundException {
        File logFile = new File("c:\\TelnetClient.log");
        PrintWriter out = new PrintWriter(new FileOutputStream(logFile, true));
        out.print(s);
    }
}