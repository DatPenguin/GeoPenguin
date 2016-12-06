package ucp.java.projet;

import ucp.java.projet.gui.PenguinWindow;
import ucp.java.projet.utilities.CountryList;
import ucp.java.projet.utilities.Importer;
import ucp.java.projet.utilities.SerializeSettings;

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Classe principale servant de point d'entree
 */

public class Main {

    public static String BASE_FOLDER = "";

    /**
     * Emplacement des codes pays
     */
    public static final String COUNTRY_CODES = BASE_FOLDER + "country_codes_iso.csv";

    /**
     * Emplacement des references FIPS
     */
    public static final String FIPS_CODES = BASE_FOLDER + "sourceXML.xml";

    /**
     * Emplacement des fichiers concernant la population des pays
     */
    public static String COUNTRY_POP = BASE_FOLDER + "POP.csv";

    /**
     * Objet contenant notre fenetre
     */
    private static PenguinWindow p;

    /**
     * Liste des pays, utile pour la ComboBox
     */
    public static CountryList countryList = new CountryList();

    /**
     * @param args Arguments de lancement
     */
    public static void main(String[] args) {
        BASE_FOLDER = PenguinWindow.whereAreTheFiles();
        System.out.println(COUNTRY_POP); // TODO Ne semble pas marcher
        new Importer();                      // Un simple appel de l'Importer le lance

        if (argsContain(args, "console")) {             // Verifie si on veut lancer le programme en mode console
            System.err.println("Console mode");         // Confirme le lancement en console
            System.out.println(countryList.toString());
        } else {
            System.err.println("GUI mode");             // Confirme le lancement en GUI
            p = new PenguinWindow();                    // Lance le GUI
        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                SerializeSettings.serialize();
            }
        }));
    }

    /**
     * @return Fenetre principale du programme
     */
    public static PenguinWindow getWindow() {
        return p;
    }

    /**
     * @param args   Parametres de lancement
     * @param parsed Parametre que l'on cherche
     * @return Presence ou absence d'un parametre
     */
    private static boolean argsContain(String[] args, String parsed) {
        for(String s : args)
            if (s.contains(parsed))
                return true;
        return false;
    }
}
