package ucp.java.projet.gui;

import ucp.java.projet.Main;
import ucp.java.projet.utilities.AutoCompletion;
import ucp.java.projet.utilities.Country;
import ucp.java.projet.utilities.Importer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Fenetre principale
 */
public class PenguinWindow extends JFrame implements ActionListener {

    /**
     * Panel contenant tous les composants
     */
    private JPanel panel = new JPanel();

    /**
     * Zone de texte contenant les informations sur le pays choisi
     */
    private JTextArea generated = new JTextArea();

    /**
     * Label de bienvenue
     */
    private JLabel welcomeLabel = new JLabel();

    /**
     * Bouton de validation
     */
    private JButton valider = new JButton();

    /**
     * Bouton ouvrant la fenetre des drapeaux
     */
    private JButton flagButton = new JButton();

    /**
     * Bouton ouvrant la fenetre des cartes
     */
    private JButton mapButton = new JButton();

    /**
     * Bouton ouvrant la fenetre de recherche avancee
     */
    private JButton advancedButton = new JButton();

    /**
     * Label servant a afficher la banniere (ici, un pingouin)
     */
    private JLabel banner = new JLabel();

    /**
     * Label permettant d'afficher le fond de la fenetre
     */
    private JLabel background = new JLabel();

    /**
     * Menu deroulant contenant la liste des pays
     */
    private JComboBox<String> comboBox = new JComboBox(Main.countryList.keySet().toArray());

    /**
     * Constructeur initialisant tous les composants
     */
    public PenguinWindow() {

        panel.setLayout(null);

        this.setTitle("GeoPenguin");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/penguin.png"));
        this.setSize(500, 350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);

        flagButton.setBounds(5, 150, 100, 30);
        flagButton.setText("Drapeau");
        flagButton.setVisible(true);
        flagButton.addActionListener(this);
        panel.add(flagButton);

        mapButton.setBounds(5, 185, 100, 30);
        mapButton.setText("Cartes");
        mapButton.setVisible(true);
        mapButton.addActionListener(this);
        panel.add(mapButton);

        advancedButton.setBounds(5, 220, 150, 30);
        advancedButton.setText("Recherche");
        advancedButton.setVisible(true);
        advancedButton.addActionListener(this);
        panel.add(advancedButton);

        comboBox.setBounds(200, 250, 225, 30);
        comboBox.setVisible(true);
        if (Importer.deserializedName != null)
            comboBox.setSelectedItem(Importer.deserializedName);
        AutoCompletion.enable(comboBox);
        panel.add(comboBox);

        generated.setBounds(125, 120, 275, 200);
        generated.setForeground(Color.white);
        generated.setText("Choisissez un pays et cliquez sur Valider");
        generated.setVisible(true);
        generated.setLineWrap(true);
        generated.setAutoscrolls(true);
        generated.setOpaque(false);
        generated.setEditable(false);
        panel.add(generated);

        valider.setBounds(340, 15, 150, 50);
        valider.setText("Valider");
        valider.setVisible(true);
        valider.addActionListener(this);
        panel.add(valider);

        welcomeLabel.setBounds(125, 100, 300, 15);
        welcomeLabel.setText("Bienvenue dans GeoPenguin");
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setVisible(true);
        panel.add(welcomeLabel);

        banner.setBounds(5, 5, 77, 100);
        banner.setIcon(new ImageIcon(Main.class.getResource("/penguin.png")));
        banner.setVisible(true);
        panel.add(banner);

        background.setBounds(0, 0, 500, 350);
        background.setIcon(new ImageIcon(Main.class.getResource("/maxresdefault.png")));
        background.setVisible(true);
        panel.add(background);


        panel.setVisible(true);
        this.getRootPane().setDefaultButton(valider);
        this.setVisible(true);
    }

    /**
     * Genere le texte a afficher en fonction des informations disponibles
     *
     * @return Texte a afficher
     */
    private String outText() {
        Country c = Main.countryList.get(comboBox.getSelectedItem());
        String s = "Nom : " + c.getFrenchName();
        if (c.getPop() != null)
            s = s + ("\nPopulation : " + c.getPop());
        if (c.getPopDensity() != 0)
            s = s + ("\nDensity : " + c.getPopDensity());
        return s;
    }

    /**
     * Methode de gestion des evenements de la JFrame
     *
     * @param e Action effectuee
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == valider) {
            Country c = Main.countryList.get(comboBox.getSelectedItem());
            generated.setText(outText());
        } else if (e.getSource() == flagButton) {
            new FlagWindow();
        } else if (e.getSource() == mapButton) {
            new MapWindow();
        } else if (e.getSource() == advancedButton) {
            new SearchWindow();
        }
    }

    /**
     * Retourne le pays actuellement selectionne
     *
     * @return Pays actuellement selectionne
     */
    public Country getCurrentCountry() {
        return Main.countryList.get(comboBox.getSelectedItem());
    }

    /**
     * Methode utilitaire pour transformer une Image en BufferedImage
     *
     * @param img Image a bufferiser
     * @return BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Cree une BufferedImage avec transparence
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Dessine l'image sur la BufferedImage
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Retourne la BufferedImage ainsi creee
        return bimage;
    }

    /**
     * Getter retournant la combobox
     *
     * @return Combobox de la fenetre
     */
    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    /**
     * Methode demandant a l'utilisateur l'emplacement des fichiers de donnees et retournant celui-ci en tant que String
     *
     * @return Chemin d'acces des fichiers de donnees
     */
    public static String whereAreTheFiles() {
        return JOptionPane.showInputDialog("Veuillez entrer l'emplacement des fichiers de donnees :");
    }
}
