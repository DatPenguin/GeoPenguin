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

    private JPanel panel = new JPanel();
    private JLabel background = new JLabel();
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

        background.setBounds(0, -14, 500, 300);

        BufferedImage i = null;

        try {
            i = PenguinWindow.toBufferedImage(new ImageIcon("flags/" +
                    Main.countryList.get(Main.getWindow().getComboBox().getSelectedItem()).getFIPS().toLowerCase() + "-lgflag.gif").getImage());
        } catch (Exception e) {
            System.err.println("Flag not found");
        }

        if (i != null)
            background.setIcon(new ImageIcon(i.getScaledInstance(500, 273, Image.SCALE_SMOOTH))); // Parametrage du fond de la fenetre
        background.setVisible(true);
        panel.add(background);

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
