package net.twse.telnetclient;

public class Tester {
    public static void main(String[] args) throws Exception {
//        TelnetClient dlink = new TelnetClient("192.168.1.1");
//        TelnetClient dlink = new TelnetClient("54.201.255.163");
//        TelnetClient dlink = new TelnetClient("82.200.18.194");
//        TelnetClient dlink = new TelnetClient("35.160.169.47"); //Telnet Weather
//        TelnetClient dlink = new TelnetClient("10.70.111.8"); //DES-3200-26 A1/B1
//        TelnetClient dlink = new TelnetClient("10.43.126.13");  //DES-1210-28/ME
//        TelnetClient dlink = new TelnetClient("10.43.126.28");  //DES-3200-26 C1

        TelnetClient test = new TelnetClient();
        TelnetReader tReader = new TelnetReader(test.connect("192.168.1.1"));
//        TelnetReader tReader = new TelnetReader(test.connect("35.160.169.47"));
//        tReader.readUntil("tacacs+ login:"); ////DES-3200
//        tReader.readUntil("Username:"); //DES-1210-28/ME

//        tReader.readUntil();
        tReader.readChar(true, false);
//        tReader.readChar(true, true);
//        tReader.rawRead();
    }
}
