package ucp.java.projet.utilities;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by penguin on 08/11/16.
 */
public class CountryList extends TreeMap<String, Country> {
    @Override
    public String toString() {
        String s = new String();
        for(Map.Entry<String, Country> country : this.entrySet())
            s += country.getValue().getEnglishName() + " " + country.getValue().getISO2() + " ";
        return s;
    }
}
