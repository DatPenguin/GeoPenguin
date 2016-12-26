package ucp.java.projet.utilities;

import ucp.java.projet.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Classe utilitaire permettant l'importation des donnees depuis les fichiers fournis
 */
public class Importer {

    /**
     * FileReader permettant la lecture d'un fichier
     */
    private FileReader fileReader;

    /**
     * BufferedReader permettant la gestion du flux d'entree
     */
    private BufferedReader reader;

    /**
     * Buffer initialise pour eviter une valeur null
     */
    private String buffer = "";

    /**
     * Variable tampon contenant le pays en cours de traitement
     */
    private Country c;

    /**
     * Chaine de caracteres contenant le nom deserialise du dernier pays choisi
     */
    public static String deserializedName;

    /**
     * Constructeur, lance toutes les phases d'importation des donnees
     */
    public Importer() {
        try {
            deserializedName = SerializeSettings.deserialize("country");
            readBasics();
            readPop();
            readFIPS();
            readSup();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur de lecture des fichiers");
        }
    }

    /**
     * Methode lisant et stockant les informations basiques des pays
     *
     * @throws IOException Erreur si le fichier de donnees est introuvable
     */
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

    /**
     * Methode lisant et stockant les informations FIPS des pays
     * @throws IOException Erreur si le fichier de donnees est introuvable
     */
    private void readFIPS() throws IOException {
        fileReader = new FileReader(Main.FIPS_CODES);
        reader = new BufferedReader(fileReader);

        buffer = reader.readLine();
        buffer = reader.readLine();
        buffer = reader.readLine();
        while (buffer != null) {
            c = new Country(buffer);
            xmlParser(buffer);
            buffer = reader.readLine();
        }
    }

    /**
     * Methode lisant et stockant les informations de superficie des pays
     * @throws IOException Erreur si le fichier de donnees est introuvable
     */
    private void readSup() throws IOException {
        fileReader = new FileReader(Main.AREA);
        reader = new BufferedReader(fileReader);

        buffer = reader.readLine();
        while (buffer != null) {
            c = new Country(buffer);
            areaParser(buffer);
            buffer = reader.readLine();
        }
    }

    /**
     * Methode lisant et stockant les informations de population des pays
     * @throws IOException Erreur si le fichier de donnees est introuvable
     */
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

    /**
     * Methode extrayant les donnees basiques d'une chaine de caracteres et les stockant dans l'objet Country
     * @param s Chaine de donnees brute
     */
    private void csvParser(String s) {
        String[] buffer = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (int i = 0; i < 5; i++)
            if (buffer[i].startsWith(" "))
                buffer[i] = buffer[i].substring(1);
        c.setEnglishName(buffer[0].replaceAll("\"", ""));
        c.setFrenchName(buffer[1].replaceAll("\"", ""));
        c.setISO2(buffer[2].replaceAll("\"", ""));
        c.setISO3(buffer[3].replaceAll("\"", ""));
        c.setNumeric(buffer[4].replaceAll("\"", ""));
    }

    /**
     * Methode extrayant les donnees de population d'une chaine de caracteres et les stockant dans l'objet Country
     * @param s Chaine de donnees brute
     */
    private void popParser(String s) {
        String[] buffer = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (IndianaJones.getByISO3(buffer[0]) != null)
            IndianaJones.getByISO3(buffer[0].replaceAll("\"", "")).setPop(buffer[4].replaceAll("\"", "").replaceAll(",", " ") + " 000");
    }

    /**
     * Methode extrayant les donnees FIPS d'une chaine de caracteres et les stockant dans l'objet Country
     * @param s Chaine de donnees brute
     */
    private void xmlParser(String s) {
        String[] buffer = s.split("\"", 0);
        if (!buffer[0].startsWith("</") && !buffer[0].startsWith("<!"))
            if (IndianaJones.getByFIPSName(buffer[1]) != null) {
                IndianaJones.getByFIPSName(buffer[1]).setFIPSName(buffer[1]);
                IndianaJones.getByFIPSName(buffer[1]).setFIPS(buffer[3]);
            }
    }

    /**
     * Methode extrayant les donnees de superficie d'une chaine de caracteres et les stockant dans l'objet Country
     * @param s Chaine de donnees brute
     */
    private void areaParser(String s) {
        String name;
        int i = 0;
        while (i < s.length() && !Character.isLetter(s.charAt(i)))
            i++;
        s = s.substring(i);
        name = getName(s);
        try {
            IndianaJones.getByFIPSName(name).setArea(getSup(s));
            IndianaJones.getByFIPSName(name).calcDensity();
        } catch (NullPointerException e) {
        } catch (Exception f) {
            f.printStackTrace();
        }
    }

    /**
     * Methode extrayant le nom d'un pays a partir d'une chaine de caracteres de superficie
     * @param s Chaine de caracteres de superficie
     * @return Nom du pays
     */
    private String getName(String s) {
        int i = 0;
        while (Character.isLetter(s.charAt(i)) || Character.isLetter(s.charAt(i + 1)))
            i++;
        return (s.substring(0, i));
    }

    /**
     * Methode extrayant la superficie d'un pays a partir d'une chaine de caracteres de superficie
     * @param s Chaine de caracteres de superficie
     * @return Superficie du pays
     */
    private String getSup(String s) {
        int i = 0;
        while (!Character.isDigit(s.charAt(i)))
            i++;
        return s.substring(i).replaceAll(",", "");
    }

}
