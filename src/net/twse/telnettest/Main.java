package net.twse.telnettest;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client dlink = new Client("10.54.66.11");
        dlink.login();
    }
}
