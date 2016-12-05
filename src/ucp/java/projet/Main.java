package ucp.java.projet;

import ucp.java.projet.gui.PenguinWindow;
import ucp.java.projet.utilities.CountryList;
import ucp.java.projet.utilities.Importer;

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Classe principale servant de point d'entree
 */

public class Main {

    /**
     * Emplacement des codes pays
     */
    public static final String COUNTRY_CODES = new String("country_codes_iso.csv");

    /**
     * Emplacement des references FIPS
     */
    public static final String FIPS_CODES = new String("sourceXML.xml");

    /**
     * Emplacement des fichiers concernant la population des pays
     */
    public static final String COUNTRY_POP = new String("POP.csv");

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
        new Importer();                      // Un simple appel de l'Importer le lance

        if (argsContain(args, "console")) {             // Verifie si on veut lancer le programme en mode console
            System.err.println("Console mode");         // Confirme le lancement en console
            System.out.println(countryList.toString());
        } else {
            System.err.println("GUI mode");             // Confirme le lancement en GUI
            p = new PenguinWindow();                    // Lance le GUI
        }
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
