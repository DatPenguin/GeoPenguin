package ucp.java.projet.gui;

import ucp.java.projet.Main;
import ucp.java.projet.utilities.Country;
import ucp.java.projet.utilities.IndianaJones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Matteo Staiano, Morgane Guisy
 * @description Fenetre de recherche avancee
 */
public class SearchWindow extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();
    private String[] critereList = {"Nom FR", "Nom EN", "ISO2", "ISO3"};
    private JComboBox critere = new JComboBox(critereList);
    private JTextField pays = new JTextField();
    private JButton searchButton = new JButton();
    private JTextArea generated = new JTextArea();
    private JLabel background = new JLabel();

    public SearchWindow() {
        this.setLayout(null);
        this.setTitle("Recherche avancée");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(panel);
        this.setSize(500, 400);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/penguin.png"));
        panel.setLayout(null);

        critere.setBounds(50, 100, 100, 30);
        critere.setEditable(false);
        critere.setVisible(true);
        panel.add(critere);

        pays.setBounds(100, 150, 100, 30);
        pays.setEditable(true);
        pays.setVisible(true);
        panel.add(pays);

        background.setBounds(0, 0, 500, 400);
        background.setIcon(new ImageIcon(Main.class.getResource("/searchBackground.jpg")));
        background.setVisible(true);
        panel.add(background);

        searchButton.setBounds(100, 190, 125, 30);
        searchButton.setText("Rechercher");
        searchButton.setVisible(true);
        searchButton.addActionListener(this);
        panel.add(searchButton);

        generated.setBounds(150, 100, 300, 200);
        generated.setVisible(true);
        generated.setEditable(false);
        panel.add(generated);

        this.getRootPane().setDefaultButton(searchButton);

        panel.setVisible(true);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String critereString = (String) critere.getSelectedItem();
            String enteredCountry = pays.getText();
            Country c;
            switch (critereString) {
                case "ISO2":
                    c = IndianaJones.getByISO2(enteredCountry);
                    break;
                case "Nom FR":
                    c = IndianaJones.searchByFrenchName(enteredCountry);
                    break;
                case "Nom EN":
                    c = IndianaJones.getByEngName(enteredCountry);
                    break;
                case "ISO3":
                    c = IndianaJones.getByISO3(enteredCountry);
                    break;
                default:
                    c = null;
                    System.err.println("Le code ne correspond a aucun pays");
                    break;
            }
            if (c == null) {
                System.err.println("Aucun pays n'a pu etre trouve");
                JOptionPane.showMessageDialog(panel, "Aucun pays n'a été trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            generated.setText("English Name : " + c.getEnglishName() + "\nFrench Name : " + c.getFrenchName() +
                    "\nISO2 : " + c.getISO2() + "\nISO3 : " + c.getISO3() + "\nNumeric Value : " + c.getNumeric() + "\nPopulation : " + c.getPop() + "\nFIPS Name : "
                    + c.getFIPSName() + "\nFIPS : " + c.getFIPS());
        }
    }
}
