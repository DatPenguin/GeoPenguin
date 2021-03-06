package ucp.java.projet.utilities;

import ucp.java.projet.Main;

import java.io.*;

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Permet de serialiser les parametres pour les conserver entre deux utilisations du programme
 */
public class SerializeSettings {
    /**
     * Methode lancant la serialisation en mode GUI
     */
    public static void serialize() {
        ObjectOutputStream oos;
        String str = Main.getWindow().getCurrentCountry().getFrenchName();
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("country"))));
            oos.writeObject(str);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        str = Main.BASE_FOLDER;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("settings"))));
            oos.writeObject(str);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode lancant la serialisation en mode console
     */
    public static void serializeConsole() {
        ObjectOutputStream oos;
        String str = Main.BASE_FOLDER;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("settings"))));
            oos.writeObject(str);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode deserialisant les fichiers precedemment crees
     *
     * @param file Fichier a deserialiser
     * @return Donnees correspondantes au fichier envoye en parametre
     */
    public static String deserialize(String file) {
        ObjectInputStream ois;
        String out = null;

        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(file))));
            out = ois.readObject().toString();
            ois.close();
        } catch (IOException e) {
            System.err.println("Fichier de parametres \"" + file + "\" introuvable. Il sera cree a la fin de l'execution.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return out;
    }

}
