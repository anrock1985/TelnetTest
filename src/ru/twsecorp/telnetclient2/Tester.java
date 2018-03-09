package ru.twsecorp.telnetclient2;

public class Tester {
    /*
                "192.168.1.1" //Home Router
                "35.160.169.47" //Telnet Weather
                "10.70.111.8" //DES-3200-26 A1/B1
                "10.43.126.13"  //DES-1210-28/ME
                "10.43.126.28"  //DES-3200-26 C1
                "tacacs+ login:" ////DES-3200
                "Username:" //DES-1210-28/ME
    */
    public static void main(String[] args) {
        Client client1 = new Client("35.160.169.47");
//        Client client2 = new Client("10.70.111.8");
//        client1.read();
//        client2.read();
        client1.login();
    }
}
