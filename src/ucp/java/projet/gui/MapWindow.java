package ucp.java.projet.gui;

import ucp.java.projet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Fenetre affichant les cartes du pays choisi et de la region du monde correspondante.
 */
public class MapWindow extends JFrame {

    /**
     * Panneau contenant tous les elements
     */
    private JPanel panel = new JPanel();

    /**
     * Label affichant la carte du pays
     */
    private JLabel countryMap = new JLabel();

    /**
     * Label affichant la carte de la region
     */
    private JLabel regionMap = new JLabel();

    /**
     * Label en arriere-plan, visible uniquement si aucune carte n'a pu etre trouvee
     */
    private JLabel errorLabel = new JLabel();

    /**
     * Variable stockant temporairement le chemin d'acces des cartes trouvees
     */
    private String foundMap = new String();

    /**
     * Unique constructeur, initialise toutes les donnees necessaires
     */
    public MapWindow() {
        panel.setLayout(null);

        this.setTitle("Carte");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/penguin.png"));
        this.setSize(1000, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(panel);

        countryMap.setBounds(0, -14, 500, 400);
        regionMap.setBounds(500, -14, 500, 400);

        BufferedImage i = null;
        BufferedImage j = null;

        try {
            i = PenguinWindow.toBufferedImage(new ImageIcon("maps/"
                    + Main.countryList.get(Main.getWindow().getComboBox().getSelectedItem()).getFIPS().toLowerCase()
                    + "-map.gif").getImage());
            mapFinder(new File("locator"), Main.countryList.get(Main.getWindow().getComboBox().getSelectedItem()).getFIPS().toLowerCase() + "_large_locator.gif");
            j = PenguinWindow.toBufferedImage(new ImageIcon(foundMap).getImage());
        } catch (Exception e) {
            System.err.println("Map not found");
        }

        // Affichage des cartes en tant qu'icones de label
        if (i != null) {
            countryMap.setIcon(new ImageIcon(i.getScaledInstance(500, 400, Image.SCALE_SMOOTH)));
            countryMap.setVisible(true);
            panel.add(countryMap);
        }
        if (j != null) {
            regionMap.setIcon(new ImageIcon(j.getScaledInstance(500, 400, Image.SCALE_SMOOTH)));
            regionMap.setVisible(true);
            panel.add(regionMap);
        }

        // Message s'affichant si aucune carte n'a pu etre trouvee
        errorLabel.setBounds(160, 120, 1000, 15);
        errorLabel.setText("Aucune carte trouvable");
        errorLabel.setForeground(Color.BLACK);
        errorLabel.setFont(errorLabel.getFont().deriveFont(Font.PLAIN).deriveFont(20f));
        errorLabel.setVisible(true);
        panel.add(errorLabel);

        panel.setVisible(true);
        this.setVisible(true);
    }

    /**
     * Fonction recherchant une carte de facon recursive
     *
     * @param folder  Dossier de travail actuel
     * @param mapName Nom de la carte recherchee
     */
    public void mapFinder(File folder, String mapName) {
        String[] files = folder.list();
        if (Arrays.asList(files).contains(mapName)) {
            try {
                foundMap = folder.getCanonicalPath() + "/" + mapName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            for (File f : folder.listFiles())
                if (f.isDirectory())
                    mapFinder(f, mapName);
        }
    }
}
