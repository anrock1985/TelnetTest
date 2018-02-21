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
        TelnetReader tReader = new TelnetReader(test.connect("10.43.126.13"));
        tReader.readUntil("login");
    }

//    void login() throws Exception {
//        final int lfCmd = 10;
//        final int crCmd = 13;
//        boolean rawBytes = false;
//        boolean reading = true;
//
//        if (socket.isConnected()) {
//            InputStream sockInputStream = socket.getInputStream();
//            int inputByte = sockInputStream.read();
//
//            while (reading) {
//                if (inputByte > 0 && inputByte != 32) {
//                    if (rawBytes) {
//                        System.out.println(sockInputStream.available() + " bytes available");
//                    }
//                    while (sockInputStream.available() > 0) {
//                        if (!rawBytes) {
//                            switch (inputByte) {
//                                case crCmd:
//                                    while (inputByte == crCmd) {
//                                        inputByte = sockInputStream.read();
//                                    }
//                                    System.out.println();
//                                case lfCmd:
//                                    while (inputByte == lfCmd) {
//                                        inputByte = sockInputStream.read();
//                                    }
//                                    break;
//                                default:
//                                    System.out.print((char) inputByte);
//                                    inputByte = sockInputStream.read();
//                            }
//                        } else {
//                            System.out.print(inputByte);
//                            if (sockInputStream.available() != 0)
//                                System.out.print(" ");
//                            inputByte = sockInputStream.read();
//                        }
//                    }
//                    System.out.println("\n" + inputByte);
//                } else {
//                    reading = false;
//                }
//
//                if (rawBytes)
//                    System.out.println();
//                Thread.sleep(50);
//            }
//            sockInputStream.close();
//            System.out.println("\nSocket closed!");
//        }
//    }
}
