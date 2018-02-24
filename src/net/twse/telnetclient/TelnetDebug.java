package net.twse.telnetclient;

import java.io.*;

public class TelnetDebug {
    private FileWriter out;

    TelnetDebug(String pathToLogFile) {
        try {
            File logFile = new File(pathToLogFile);
            out = new FileWriter(logFile, true);
            if (!logFile.exists()) {
                if (!logFile.createNewFile())
                    System.out.println("Cannot create log file!");
            }
            out.write("\n\nLog file updated\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void log(String s) {
        try {
            out.write(s);
        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        }
    }
}