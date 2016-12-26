package ucp.java.projet;

import ucp.java.projet.gui.PenguinWindow;
import ucp.java.projet.utilities.*;

import java.util.Scanner;

/*
    TODO : Verif compatibilite 1.7, Javadoc
 */

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Classe principale servant de point d'entree
 */

public class Main {

    public static boolean CONSOLE_MODE = false;

    /**
     * Dossier contenant tous les fichiers de donnees
     */
    public static String BASE_FOLDER;

    /**
     * Emplacement des codes pays
     */
    public static String COUNTRY_CODES;

    public static String AREA;

    /**
     * Emplacement des references FIPS
     */
    public static String FIPS_CODES;

    /**
     * Emplacement des fichiers concernant la population des pays
     */
    public static String COUNTRY_POP;

    /**
     * Objet contenant notre fenetre
     */
    private static PenguinWindow p;

    /**
     * Liste des pays, utile pour la ComboBox
     */
    public static CountryList countryList = new CountryList();

    /**
     * Permet d'initialiser tous les chemins d'acces en tenant compte du BASE_FOLDER
     */
    public static void initStrings() {
        BASE_FOLDER = SerializeSettings.deserialize("settings");
        if (BASE_FOLDER == null && !CONSOLE_MODE)
            BASE_FOLDER = PenguinWindow.whereAreTheFiles();
        else if (BASE_FOLDER == null && CONSOLE_MODE)
            consoleAskBaseFolder();
        COUNTRY_POP = BASE_FOLDER + "POP.csv";
        COUNTRY_CODES = BASE_FOLDER + "country_codes_iso.csv";
        FIPS_CODES = BASE_FOLDER + "sourceXML.xml";
        AREA = BASE_FOLDER + "rawdata_2147.txt";
    }

    /**
     * @param args Arguments de lancement
     */
    public static void main(String[] args) {
        if (argsContain(args, "console")) {             // Verifie si on veut lancer le programme en mode console
            System.err.println("Console mode");         // Confirme le lancement en console
            CONSOLE_MODE = true;
        } else {
            System.err.println("GUI mode");             // Confirme le lancement en GUI
            CONSOLE_MODE = false;                    // Lance le GUI
        }

        initStrings();
        new Importer();                      // Un simple appel de l'Importer le lance

        if (!CONSOLE_MODE)
            p = new PenguinWindow();
        else
            goConsole();

        if (!CONSOLE_MODE)
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {    // Execute le code suivant a la fermeture du programme
                public void run() {
                    SerializeSettings.serialize();
                }
            }));
        else
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {    // Execute le code suivant a la fermeture du programme
                public void run() {
                    SerializeSettings.serializeConsole();
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

    public static void consoleAskBaseFolder() {
        Scanner s = new Scanner(System.in);
        System.out.println("Veuillez entrer le chemin du dossier contenant les fichiers de donnees...");
        BASE_FOLDER = s.nextLine();
        s.close();
    }

    public static void goConsole() {
        Country c;
        Scanner s = new Scanner(System.in);
        System.out.println("Selon quel critere souhaitez-vous rechercher un pays ?\n1. Nom FR\n2. Nom EN\n3. ISO2");
        String str = s.nextLine();
        switch (str) {
            case "1":
                System.out.println("Entrez le nom francais du pays :");
                c = IndianaJones.getByFrenchName(s.nextLine());
                break;
            case "2":
                System.out.println("Entrez le nom anglais du pays :");
                c = IndianaJones.getByEngName(s.nextLine());
                break;
            case "3":
                System.out.println("Entrez le code ISO2 du pays :");
                c = IndianaJones.getByISO2(s.nextLine());
                break;
            default:
                System.err.println("Choix invalide");
                return;
        }
        System.out.print(c.toString());
        s.close();
    }
}
