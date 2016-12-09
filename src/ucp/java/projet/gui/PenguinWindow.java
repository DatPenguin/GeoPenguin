package ucp.java.projet.gui;

/**
 * @author Ethan
 * @date 04/11/2016.
 */

import ucp.java.projet.Main;
import ucp.java.projet.utilities.Country;
import ucp.java.projet.utilities.Importer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class PenguinWindow extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();
    private JTextArea generated = new JTextArea();
    private JLabel genLabel = new JLabel();
    private JButton valider = new JButton();
    private JButton flagButton = new JButton();
    private JButton mapButton = new JButton();
    private JLabel banner = new JLabel();
    private JLabel background = new JLabel();
    private JComboBox<String> comboBox = new JComboBox(Main.countryList.keySet().toArray());

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

        comboBox.setBounds(200, 250, 225, 30);
        comboBox.setVisible(true);
        if (Importer.deserializedName != null)
            comboBox.setSelectedItem(Importer.deserializedName);
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

        genLabel.setBounds(125, 100, 300, 15);
        genLabel.setText("Bienvenue dans GeoPenguin");
        genLabel.setForeground(Color.white);
        genLabel.setVisible(true);
        panel.add(genLabel);

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == valider) {
            Country c = Main.countryList.get(comboBox.getSelectedItem());
            generated.setText("English Name : " + c.getEnglishName() + "\nFrench Name : " + c.getFrenchName() +
                    "\nISO2 : " + c.getISO2() + "\nISO3 : " + c.getISO3() + "\nNumeric Value : " + c.getNumeric() + "\nPopulation : " + c.getPop() + "\nFIPS Name : "
                    + c.getFIPSName() + "\nFIPS : " + c.getFIPS());
        }
        else if (e.getSource() == flagButton) {
            new FlagWindow();
        }
        else if (e.getSource() == mapButton) {
            new MapWindow();
        }
    }

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

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public static String whereAreTheFiles() {
        return JOptionPane.showInputDialog("Veuillez entrer l'emplacement des fichiers de donnees :");
    }
}
