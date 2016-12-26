package ucp.java.projet;

import ucp.java.projet.utilities.Importer;
import ucp.java.projet.utilities.IndianaJones;
import ucp.java.projet.utilities.SerializeSettings;

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Main ne concernant que l'executable console
 */
public class MainConsole {
    public static void main(String[] args) {
        Main.CONSOLE_MODE = true;
        Main.initStrings();
        new Importer();
        if (args.length == 0)
            Main.goConsole();
        else {
            try {
                System.out.println(IndianaJones.getByFrenchName(args[0]).toString());
            } catch (NullPointerException e) {
                System.err.println("Pays introuvable");
            }
        }
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {    // Execute le code suivant a la fermeture du programme
            public void run() {
                SerializeSettings.serializeConsole();
            }
        }));
    }
}
