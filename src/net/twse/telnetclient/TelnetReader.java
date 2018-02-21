package net.twse.telnetclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/*
10  LF  (NL line feed, new line)
13  CR  (carriage return)
 */

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
            output = in.readLine();
            //Убираем из вывода пустые строки
            if (!(output.equals("")) && !(output.equals(" ")))
                System.out.println(output);
        } while (!(output.contains(target)));
        in.close();
        System.out.println("\nTARGET REACHED!");
    }

    void readUntil() {
        String output;
        try {
            for (; ; ) {
                output = in.readLine();
                System.out.println(output);
            }
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    void readChar(boolean wordWrap, boolean raw) {
        int ch;
        try {
            for (ch = in.read(); ; ) {
                while (isOptions(ch)) {
                    ch = in.read();
                }
                if (wordWrap && ch == 10 && !raw) {
                    ch = in.read();
                    System.out.print("\n");
                }
                if (raw) {
                    System.out.print(ch + "'");
                    ch = in.read();
                } else {
                    System.out.print((char) ch);
                    ch = in.read();
                }
            }
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    void optNegotiation() {
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
                                System.out.print("'");
                            inputByte = sockInputStream.read();
                        }
                    }
                    System.out.println("\n" + inputByte);
                } else {
                    reading = false;
                }

                if (rawBytes)
                    System.out.println();
                Thread.sleep(100);
            }
            sockInputStream.close();
            System.out.println("\nSocket closed!");
        }
    }

    private boolean isOptions(int ch) {
        if (ch == 0 || ch == 10 || ch == 13 || (ch >= 32 && ch <= 126))
            return false;
        else
            return true;
    }
}