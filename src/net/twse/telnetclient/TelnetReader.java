package net.twse.telnetclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TelnetReader {
    private BufferedReader in;

    TelnetReader(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
        System.out.println("TARGET REACHED!");
    }
}