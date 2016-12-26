package ucp.java.projet.utilities;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Liste de pays heritant de TreeMap
 */
public class CountryList extends TreeMap<String, Country> {

    /**
     * Methode toString
     *
     * @return Chaine de caracteres permettant d'afficher la liste
     */
    @Override
    public String toString() {
        String s = new String();
        for(Map.Entry<String, Country> country : this.entrySet())
            s += country.getValue().getEnglishName() + " " + country.getValue().getISO2() + " ";
        return s;
    }
}
