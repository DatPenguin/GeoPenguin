package ucp.java.projet;

import ucp.java.projet.gui.PenguinWindow;
import ucp.java.projet.utilities.CountryList;
import ucp.java.projet.utilities.Importer;

import java.io.IOException;

public class Main {

    public static final String COUNTRY_CODES = new String("country_codes_iso.csv");
    private static PenguinWindow p;
    //public static final String COUNTRY_CODES = new String("countries.geo");

    public static CountryList countryList = new CountryList();
    private static Importer importer;

    public static void main(String[] args) {
        try {
            importer = new Importer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (argsContain(args, "console")) {
            System.err.println("Console mode");
            System.out.println(countryList.toString());
        }
        else {
            System.err.println("GUI mode");
            p = new PenguinWindow();
        }
    }

    public static PenguinWindow getWindow() {
        return p;
    }

    private static boolean argsContain(String[] args, String parsed) {
        for(String s : args)
            if (s.endsWith(parsed))
                return true;
        return false;
    }
}
