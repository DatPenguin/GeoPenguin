package ucp.java.projet.utilities;

import javax.swing.*;

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Objet contenant les informations des pays
 */
public class Country {
    private String englishName;
    private String frenchName;
    private String ISO2;
    private String ISO3;
    private String numeric;
    private ImageIcon flag;
    private ImageIcon map;
    private int popDensity;
    private String pop;
    private String FIPS;
    private String FIPSName;

    public String getFIPS() {
        return FIPS;
    }

    public void setFIPS(String FIPS) {
        this.FIPS = FIPS;
    }

    public String getFIPSName() {
        return FIPSName;
    }

    public void setFIPSName(String FIPSName) {
        this.FIPSName = FIPSName;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getFrenchName() {
        return frenchName;
    }

    public void setFrenchName(String frenchName) {
        this.frenchName = frenchName;
    }

    public String getISO3() {
        return ISO3;
    }

    public void setISO3(String ISO3) {
        this.ISO3 = ISO3;
    }

    public String getNumeric() {
        return numeric;
    }

    public void setNumeric(String numeric) {
        this.numeric = numeric;
    }

    public Country(String englishName, String frenchName, String ISO2, String ISO3, String numeric, ImageIcon flag, ImageIcon map, int popDensity) {

        this.englishName = englishName;
        this.frenchName = frenchName;
        this.ISO2 = ISO2;
        this.ISO3 = ISO3;
        this.numeric = numeric;
        this.flag = flag;
        this.map = map;
        this.popDensity = popDensity;
    }

    public Country(String name) {
        this.englishName = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getISO2() {
        return ISO2;
    }

    public void setISO2(String ISO2) {
        this.ISO2 = ISO2;
    }

    public ImageIcon getFlag() {
        return flag;
    }

    public void setFlag(ImageIcon flag) {
        this.flag = flag;
    }

    public ImageIcon getMap() {
        return map;
    }

    public void setMap(ImageIcon map) {
        this.map = map;
    }

    public int getPopDensity() {
        return popDensity;
    }

    public void setPopDensity(int popDensity) {
        this.popDensity = popDensity;
    }

    @Override
    public String toString() {
        return "Nom EN ='" + englishName + '\'' +
                "\nNom FR ='" + frenchName + '\'' +
                "\nISO2 ='" + ISO2 + '\'' +
                "\nISO3 ='" + ISO3 + '\'' +
                "\nCode Numerique ='" + numeric + '\'' +
                "\nDensite de Population =" + popDensity +
                "\nPopulation ='" + pop + '\'' +
                "\nFIPS ='" + FIPS + '\'' +
                "\nNom FIPS ='" + FIPSName + '\'';
    }
}
