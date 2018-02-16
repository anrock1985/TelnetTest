package net.twse.telnettest;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
//    final String LOGIN = "admin";
//    final String PASSWORD = "dEsKtOpF1685";

    Client(String ip) throws IOException {
//        final int TELNET_PORT = 22;
        final int TELNET_PORT = 23;
//        final int TELNET_PORT = 80;
        this.socket = new Socket(ip, TELNET_PORT);
    }

    void login() throws IOException {
        final int lfCmd = 10;
        final int crCmd = 13;
        boolean rawBytes = false;
        boolean reading = true;

        if (socket.isConnected()) {
            BufferedInputStream sockIn = new BufferedInputStream(socket.getInputStream());
//            BufferedOutputStream sockOut = new BufferedOutputStream(socket.getOutputStream());

            while (reading) {
                if (sockIn.read() != -1) {
                    int inputByte;
                    System.out.println(sockIn.available() + " bytes available");
                    while (sockIn.available() > 0) {
                        inputByte = sockIn.read();
                        if (!rawBytes) {
                            switch (inputByte) {
                                case lfCmd:
                                    sockIn.read();
                                    System.out.println("LF CR");
                                    break;
                                case crCmd:
                                    System.out.println("CR");
                                    break;
                                default:
//                                    System.out.print(inputByte);
//                                    if (sockIn.available() != 0)
//                                        System.out.print(" ");
                                    System.out.print((char) inputByte);
                            }
                        } else {
                            System.out.print(inputByte);
                            if (sockIn.available() != 0)
                                System.out.print(" ");
                        }
                    }
                    System.out.println();
                } else {
                    reading = false;
                }
            }
            sockIn.close();
            System.out.println("Socket closed!");
        }
    }
}
