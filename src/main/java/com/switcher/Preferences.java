package com.switcher;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Preferences {

    public boolean encryptEnabled;
    public boolean closeBeforeLaunch;
    public String theme;

    private String path = "preferences.txt";

    public void readFromFile() {
        try {

            Scanner in = new Scanner(new FileReader(path));

            String thisLine = in.nextLine();
            encryptEnabled = Boolean.parseBoolean(thisLine.substring(thisLine.indexOf("=") + 1));
            thisLine = in.nextLine();
            closeBeforeLaunch = Boolean.parseBoolean(thisLine.substring(thisLine.indexOf("=") + 1));
            thisLine = in.nextLine();
            theme = thisLine.substring(thisLine.indexOf("=") + 1);

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public void writeToFile() {

        try {
            FileWriter writer = new FileWriter(path);

            writer.write("");
            writer.close();

            BufferedWriter out = new BufferedWriter(new FileWriter(path, true));
            out.write("encryptEnabled=" + encryptEnabled + "\n");
            out.write("closeBeforeLaunch=" + closeBeforeLaunch + "\n");
            out.write("theme=" + theme + "\n");
            out.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }

    }

}
