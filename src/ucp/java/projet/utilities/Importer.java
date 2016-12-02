package ucp.java.projet.utilities;

/**
 * Created by penguin on 07/11/16.
 */

import ucp.java.projet.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Permet d'importer les donnees des pays depuis des fichiers
 */

public class Importer {

    private FileReader fileReader;
    private BufferedReader reader;
    private String buffer = new String("");
    private Country c;

    public Importer() {
        try {
            readBasics();
            readPop();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur de lecture des fichiers");
        }
    }

    private void readBasics() throws IOException {
        fileReader = new FileReader(Main.COUNTRY_CODES);
        reader = new BufferedReader(fileReader);

        buffer = reader.readLine();
        buffer = reader.readLine();
        while(buffer != null) {
            c = new Country(buffer);
            csvParser(buffer);
            Main.countryList.put(c.getFrenchName(), c);
            buffer = reader.readLine();
        }
    }

    private void readPop() throws IOException {
        fileReader = new FileReader(Main.COUNTRY_POP);
        reader = new BufferedReader(fileReader);

        buffer = reader.readLine();
        while (!buffer.startsWith("CHN"))
            buffer = reader.readLine();
        while(buffer != null) {
            popParser(buffer);
            if (!buffer.startsWith("TUV"))
                buffer = reader.readLine();
            else
                break;
        }
    }

    private void csvParser(String s) {
        String[] buffer = s.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
        c.setEnglishName(buffer[0].replaceAll("\"", ""));
        c.setFrenchName(buffer[1].replaceAll("\"", ""));
        c.setISO2(buffer[2].replaceAll("\"", ""));
        c.setISO3(buffer[3].replaceAll("\"", ""));
        c.setNumeric(buffer[4].replaceAll("\"", ""));
    }

    private void popParser(String s) {
        String[] buffer = s.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
        if (IndianaJones.getByISO3(buffer[0]) != null)
            IndianaJones.getByISO3(buffer[0].replaceAll("\"", "")).setPop(buffer[4].replaceAll("\"", "").replaceAll(",", " ") + " 000");
    }

    private void xmlParser(String s) {
        // TODO Faire un parser a la main pour le fichier sourceXML.xml
    }
}
