package ucp.java.projet.gui;

import ucp.java.projet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Fenetre affichant le drapeau du pays choisi
 */

public class FlagWindow extends JFrame {

    /**
     * Panneau contenant tous les composants
     */
    private JPanel panel = new JPanel();

    /**
     * Label permettant d'afficher le drapeau
     */
    private JLabel flag = new JLabel();

    /**
     * Label en arriere-plan, ne s'affichant que si aucun drapeau n'est trouvable
     */
    private JLabel errorLabel = new JLabel();

    /**
     * Unique constructeur, initialise toutes les donnees necessaires
     */
    public FlagWindow() {
        panel.setLayout(null);  // Layout null pour choisir avec precision la position des elements

        // Parametrage de la fenetre
        this.setTitle("Drapeau");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/penguin.png"));
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(panel);

        flag.setBounds(0, -14, 500, 300);

        BufferedImage i = null;

        // On cherche l'image
        try {
            i = PenguinWindow.toBufferedImage(new ImageIcon("flags/" +
                    Main.countryList.get(Main.getWindow().getComboBox().getSelectedItem()).getFIPS().toLowerCase() + "-lgflag.gif").getImage());
        } catch (Exception e) {
            System.err.println("Flag not found");
        }

        // Si on a reussi a trouver et a bufferiser l'image
        if (i != null)
            flag.setIcon(new ImageIcon(i.getScaledInstance(500, 273, Image.SCALE_SMOOTH))); // Parametrage du fond de la fenetre
        flag.setVisible(true);
        panel.add(flag);

        // Texte s'affichant si aucun drapeau n'a pu etre trouve
        errorLabel.setBounds(160, 120, 1000, 15);
        errorLabel.setText("Aucun drapeau trouvable");
        errorLabel.setForeground(Color.BLACK);
        errorLabel.setFont(errorLabel.getFont().deriveFont(Font.PLAIN).deriveFont(20f));
        errorLabel.setVisible(true);
        panel.add(errorLabel);

        panel.setVisible(true);
        this.setVisible(true);
    }

}
