package ru.twsecorp.telnetclient;

class Tester {
    public static void main(String[] args) {
        try {
//        TelnetClient dlink = new TelnetClient("192.168.1.1");
//        TelnetClient dlink = new TelnetClient("54.201.255.163");
//        TelnetClient dlink = new TelnetClient("82.200.18.194");
//        TelnetClient dlink = new TelnetClient("35.160.169.47"); //Telnet Weather
//        TelnetClient dlink = new TelnetClient("10.70.111.8"); //DES-3200-26 A1/B1
//        TelnetClient dlink = new TelnetClient("10.43.126.13");  //DES-1210-28/ME
//        TelnetClient dlink = new TelnetClient("10.43.126.28");  //DES-3200-26 C1

//        tReader.readUntil("tacacs+ login:"); ////DES-3200
//        tReader.readUntil("Username:"); //DES-1210-28/ME

            TelnetClient test = new TelnetClient("10.43.126.13");
//        for (int i = 0; i < 500; i++) {
//            test.test1();
//        }

            for (int i = 0; i < 50; i++) {
                test.testRaw();
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }
}
