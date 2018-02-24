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

class TelnetReader {
    private BufferedReader in;
    //    Testing RAW read from socket
    private Socket rawSocket;
    private int ch;

    TelnetReader(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //    Testing RAW read from socket
        rawSocket = socket;
    }

    boolean readUntil(String target) {
        String output = null;
        try {
            do {
                output = in.readLine();
//            Skip empty lines
                if (!(output.equals("")) && !(output.equals(" ")))
                    System.out.println(output);
            } while (!(output.contains(target)));
        } catch (IOException e) {
            e.getStackTrace();
        }
        return output.contains(target);
    }

    void readChar(boolean wordWrap, boolean rawData, boolean negotiateOptions) throws IOException {
        for (ch = in.read(); ch != -1; ) {
            if (negotiateOptions) {
                while (isOptions(ch)) {
                    ch = in.read();
                }
            }
            if (wordWrap && ch == 10 && !rawData) {
                System.out.print("\n");
                ch = in.read();
                while (ch == 10 || ch == 13)
                    ch = in.read();
            } else if (rawData) {
                System.out.print(ch + "'");
                ch = in.read();
            } else {
                System.out.print((char) ch);
                ch = in.read();
            }
        }
    }

    StringBuilder sbRead() {
        StringBuilder sb = new StringBuilder();
        try {
            for (ch = in.read(); ch != -1 && ch != 255; ) {
                while (ch != 10 && ch != 13 && !isOptions(ch)) {
                    sb.append((char) ch);
                    ch = in.read();
                }
                while (isOptions(ch)) {
                    ch = in.read();
                }
                if (ch == 10 || ch == 13) {
                    while (ch == 10 || ch == 13)
                        ch = in.read();
                    break;
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return sb;
    }

    StringBuilder sbReadSock() throws Exception {
        InputStreamReader in = new InputStreamReader(rawSocket.getInputStream());
        StringBuilder sb = new StringBuilder();
        for (ch = in.read(); in.ready() && ch != -1; ch = in.read()) {
            if (ch != 10 && ch != 13) {
                sb.append((char) ch);
            }
            while (ch == 10 || ch == 13) {
                ch = in.read();
            }
        }
        return sb;
    }

    StringBuilder sbReadX() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(ch);
        return sb;
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
        if (ch == 0 || ch == 10 || ch == 13 || (ch >= 32 && ch <= 63) || (ch >= 65 && ch <= 90) || (ch >= 96 && ch <= 122))
            return false;
        else
            return true;
    }
}