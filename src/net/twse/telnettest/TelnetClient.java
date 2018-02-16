package net.twse.telnettest;

import java.io.*;
import java.net.Socket;

class TelnetClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    void connect(String ip) {
        try {
            socket = new Socket(ip, 23);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("CONNECTION ERROR!");
        }
    }

    void readUntil(String target) throws IOException {
        String output;
        while (socket.isConnected()) {
            output = in.readLine();
            if (output.equals(target))
                break;
            System.out.println(in.readLine());
        }
    }

    void write(String command) {
        out.print(command);
    }

    void log() {
    }

    void login() throws Exception {
        final int lfCmd = 10;
        final int crCmd = 13;
        boolean rawBytes = false;
        boolean reading = true;

        if (socket.isConnected()) {
            InputStream sockInputStream = socket.getInputStream();
            int inputByte = sockInputStream.read();

            while (reading) {
                if (inputByte > 0 && inputByte != 32) {
                    if (rawBytes) {
                        System.out.println(sockInputStream.available() + " bytes available");
                    }
                    while (sockInputStream.available() > 0) {
                        if (!rawBytes) {
                            switch (inputByte) {
                                case crCmd:
                                    while (inputByte == crCmd) {
                                        inputByte = sockInputStream.read();
                                    }
                                    System.out.println();
                                case lfCmd:
                                    while (inputByte == lfCmd) {
                                        inputByte = sockInputStream.read();
                                    }
                                    break;
                                default:
                                    System.out.print((char) inputByte);
                                    inputByte = sockInputStream.read();
                            }
                        } else {
                            System.out.print(inputByte);
                            if (sockInputStream.available() != 0)
                                System.out.print(" ");
                            inputByte = sockInputStream.read();
                        }
                    }
                    System.out.println("\n" + inputByte);
                } else {
                    reading = false;
                }

                if (rawBytes)
                    System.out.println();
                Thread.sleep(50);
            }
            sockInputStream.close();
            System.out.println("\nSocket closed!");
        }
    }
}