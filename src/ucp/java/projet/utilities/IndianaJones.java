package ucp.java.projet.utilities;

import ucp.java.projet.Main;

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Cette classe s'appelle Indiana Jones car elle fouille les TreeMaps pour trouver des tresors
 */
public class IndianaJones {

    /**
     * Methode permettant de trouver un pays a partir de son Code ISO2
     *
     * @param ISO2 Le Code ISO2 de recherche
     * @return Le pays correspondant au Code ISO2
     */
    public static Country getByISO2 (String ISO2) {
        Country c;
        for (String s : Main.countryList.keySet()) {
            c = Main.countryList.get(s);
            if (ISO2.equalsIgnoreCase(c.getISO2()))
                return c;
        }
        return null;
    }

    /**
     * Methode permettant de trouver un pays a partir de son Code ISO3
     * @param ISO3 Le Code ISO3 de recherche
     * @return Le pays correspondant au Code ISO3
     */
    public static Country getByISO3 (String ISO3) {
        Country c;
        for (String s : Main.countryList.keySet()) {
            c = Main.countryList.get(s);
            if (ISO3.equalsIgnoreCase(c.getISO3()))
                return c;
        }
        return null;
    }

    /**
     * Methode permettant de trouver un pays a partir de son nom anglais
     * @param engName Nom anglais de recherche
     * @return Le pays correspondant au nom anglais
     */
    public static Country getByEngName(String engName) {
        Country c;
        for (String s : Main.countryList.keySet()) {
            c = Main.countryList.get(s);
            if (engName.equalsIgnoreCase(c.getEnglishName()))
                return c;
        }
        return null;
    }

    /**
     * Methode permettant de trouver un pays a partir de son nom FIPS
     * @param FIPSName Nom FIPS de recherche
     * @return Le pays correspondant au nom FIPS
     */
    public static Country getByFIPSName(String FIPSName) {
        Country c;
        for (String s : Main.countryList.keySet()) {
            c = Main.countryList.get(s);
            if (FIPSName.startsWith(c.getEnglishName()) || c.getEnglishName().startsWith(FIPSName))
                return c;
        }
        return null;
    }

    /**
     * Methode permettant de trouver un pays a partir de son nom francais
     * @param frName Nom francais de recherche
     * @return Le pays correspondant au nom francais
     */
    public static Country getByFrenchName(String frName) {
        Country c;
        for (String s : Main.countryList.keySet()) {
            c = Main.countryList.get(s);
            if (c.getFrenchName().toLowerCase().contains(frName.toLowerCase()))
                return c;
        }
        return null;
    }
}
