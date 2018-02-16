package net.twse.telnettest;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
//        Client dlink = new Client("192.168.1.1");
//        Client dlink = new Client("54.201.255.163");
//        Client dlink = new Client("82.200.18.194");
//        Client dlink = new Client("35.160.169.47"); //Telnet Weather
//        Client dlink = new Client("10.70.111.8"); //DES-3200-26 A1/B1
        Client dlink = new Client("10.43.126.13");  //DES-1210-28/ME
//        Client dlink = new Client("10.43.126.28");  //DES-3200-26 C1
        dlink.login();
    }
}
