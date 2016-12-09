package ucp.java.projet.utilities;

import ucp.java.projet.Main;

/**
 * Created by penguin on 25/11/16.
 * Cette classe s'appelle Indiana Jones car elle fouille les TreeMaps pour trouver des tresors
 */
public class IndianaJones {
    public static Country getByISO2 (String ISO2) {
        Country c;
        for (String s : Main.countryList.keySet()) {
            c = Main.countryList.get(s);
            if (ISO2.equalsIgnoreCase(c.getISO2()))
                return c;
        }
        return null;
    }

    public static Country getByISO3 (String ISO3) {
        Country c;
        for (String s : Main.countryList.keySet()) {
            c = Main.countryList.get(s);
            if (ISO3.equalsIgnoreCase(c.getISO3()))
                return c;
        }
        return null;
    }

    public static Country getByEngName(String engName) {
        Country c;
        for (String s : Main.countryList.keySet()) {
            c = Main.countryList.get(s);
            if (engName.equalsIgnoreCase(c.getEnglishName()))
                return c;
        }
        return null;
    }

    public static Country getByFIPSName(String FIPSName) {
        Country c;
        for (String s : Main.countryList.keySet()) {
            c = Main.countryList.get(s);
            if (FIPSName.startsWith(c.getEnglishName()) || c.getEnglishName().startsWith(FIPSName))
                return c;
        }
        return null;
    }

    public static Country searchByFrenchName(String frName) {
        Country c;
        for (String s : Main.countryList.keySet()) {
            c = Main.countryList.get(s);
            if (c.getFrenchName().contains(frName))
                return c;
        }
        return null;
    }
}
