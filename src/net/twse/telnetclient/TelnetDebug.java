package net.twse.telnetclient;

import java.io.*;

public class TelnetDebug {
    private PrintWriter out;

    TelnetDebug(String pathToLogFile) {
        try {
            File logFile = new File(pathToLogFile);
            out = new PrintWriter(new FileWriter(logFile, true));
            if (!logFile.exists()) {
                if (!logFile.createNewFile())
                    System.out.println("Cannot create log file!");
            }
            out.print("\n\nLog file updated\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void log(String s) {
            out.write(s);
    }
}