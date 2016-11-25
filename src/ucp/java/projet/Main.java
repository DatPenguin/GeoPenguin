package ucp.java.projet;

import ucp.java.projet.gui.PenguinWindow;
import ucp.java.projet.utilities.CountryList;
import ucp.java.projet.utilities.Importer;

public class Main {

    public static final String COUNTRY_CODES = new String("country_codes_iso.csv");
    public static final String COUNTRY_POP = new String("POP.csv");
    private static PenguinWindow p;

    public static CountryList countryList = new CountryList();
    private static Importer importer;

    public static void main(String[] args) {
        importer = new Importer();

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
