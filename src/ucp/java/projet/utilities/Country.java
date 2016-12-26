package ucp.java.projet.utilities;

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Objet contenant les informations des pays
 */
public class Country {

    /**
     * Nom anglais
     */
    private String englishName;

    /**
     * Nom francais
     */
    private String frenchName;

    /**
     * Code ISO2
     */
    private String ISO2;

    /**
     * Code ISO3
     */
    private String ISO3;

    /**
     * Code Numerique
     */
    private String numeric;

    /**
     * Densite de population
     */
    private int popDensity;

    /**
     * Population
     */
    private String pop;

    /**
     * Code FIPS
     */
    private String FIPS;

    /**
     * Nom FIPS
     */
    private String FIPSName;

    /**
     * Superficie
     */
    private String area;

    /**
     * Setter sur la superficie
     *
     * @param area Surface
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * Getter sur le Code FIPS
     * @return Code FIPS
     */
    public String getFIPS() {
        return FIPS;
    }

    /**
     * Setter sur le Code FIPS
     * @param FIPS Code FIPS
     */
    public void setFIPS(String FIPS) {
        this.FIPS = FIPS;
    }

    /**
     * Setter sur le Nom FIPS
     * @param FIPSName Nom FIPS
     */
    public void setFIPSName(String FIPSName) {
        this.FIPSName = FIPSName;
    }

    /**
     * Getter sur la population
     * @return Population
     */
    public String getPop() {
        return pop;
    }

    /**
     * Setter sur la population
     * @param pop Population
     */
    public void setPop(String pop) {
        this.pop = pop;
    }

    /**
     * Getter sur le nom francais
     * @return Nom Francais
     */
    public String getFrenchName() {
        return frenchName;
    }

    /**
     * Setter sur le nom francais
     * @param frenchName Nom Francais
     */
    public void setFrenchName(String frenchName) {
        this.frenchName = frenchName;
    }

    /**
     * Getter sur le Code ISO3
     * @return Code ISO3
     */
    public String getISO3() {
        return ISO3;
    }

    /**
     * Setter sur le Code ISO3
     * @param ISO3 Code ISO3
     */
    public void setISO3(String ISO3) {
        this.ISO3 = ISO3;
    }

    /**
     * Setter sur le Code Numerique
     * @param numeric Code Numerique
     */
    public void setNumeric(String numeric) {
        this.numeric = numeric;
    }

    /**
     * Constructeur
     * @param name Nom du pays
     */
    public Country(String name) {
        this.englishName = name;
    }

    /**
     * Getter sur le nom anglais
     * @return Nom anglais
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * Setter sur le nom anglais
     * @param englishName Nom anglais
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /**
     * Getter sur le Code ISO2
     * @return Code ISO2
     */
    public String getISO2() {
        return ISO2;
    }

    /**
     * Setter sur le Code ISO2
     * @param ISO2 Code ISO2
     */
    public void setISO2(String ISO2) {
        this.ISO2 = ISO2;
    }

    /**
     * Getter sur la densite de population
     * @return Densite de population
     */
    public int getPopDensity() {
        return popDensity;
    }

    /**
     * Methode toString
     * @return Chaine de caractere resumant le pays
     */
    @Override
    public String toString() {
        return "Nom EN ='" + englishName + '\'' +
                "\nNom FR ='" + frenchName + '\'' +
                "\nISO2 ='" + ISO2 + '\'' +
                "\nISO3 ='" + ISO3 + '\'' +
                "\nCode Numerique ='" + numeric + '\'' +
                "\nDensite de Population ='" + popDensity +
                "'\nPopulation ='" + pop + '\'' +
                "\nFIPS ='" + FIPS + '\'' +
                "\nNom FIPS ='" + FIPSName + '\'';
    }

    /**
     * Methode lancant le calcul de la densite de population pour le pays
     */
    public void calcDensity() {
        this.popDensity = (Integer.parseInt(pop.replaceAll(" ", ""))) / (Integer.parseInt(area.replaceAll(" ", "")));
    }
}
