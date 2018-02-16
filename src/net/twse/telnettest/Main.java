package net.twse.telnettest;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client dlink = new Client("192.168.1.1");
//        Client dlink = new Client("54.201.255.163");
//        Client dlink = new Client("82.200.18.194");
//        Client dlink = new Client("35.160.169.47");
        dlink.login();
    }
}
