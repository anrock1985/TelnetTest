package net.twse.telnetclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TelnetReader {
    private BufferedReader in;
    //Testing RAW Reader
    private Socket rawSocket;

    TelnetReader(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //Testing RAW Reader
        rawSocket = socket;
    }

    void readUntil(String target) throws IOException {
        String output;
        do {
            output  = in.readLine();
            //Убираем из вывода пустые строки
            if (!(output.equals("")))
                System.out.println(output);
        } while (!(output.equals("null")) && !(output.contains(target)));
        in.close();
        System.out.println("\nTARGET REACHED!");
    }

    void rawRead() throws Exception {
        final int lfCmd = 10;
        final int crCmd = 13;
        boolean rawBytes = true;
        boolean reading = true;

        if (rawSocket.isConnected()) {
            InputStream sockInputStream = rawSocket.getInputStream();
            int inputByte = sockInputStream.read();

            while (reading) {
                if (inputByte > 0) {
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