package ucp.java.projet.gui;

/**
 * @author Ethan
 * @date 04/11/2016.
 */

import ucp.java.projet.Main;
import ucp.java.projet.utilities.Country;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PenguinWindow extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();
    private JTextArea generated = new JTextArea();
    private JLabel genLabel = new JLabel();
    private JButton valider = new JButton();
    private JButton moreButton = new JButton();
    private JLabel banner = new JLabel();
    private JLabel background = new JLabel();
    private JComboBox<String> comboBox = new JComboBox(/*Main.list.toArray()*/Main.countryList.keySet().toArray());

    public PenguinWindow() {

        panel.setLayout(null);

        this.setTitle("GeoPenguin");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/penguin.png"));
        this.setSize(400, 350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);

        moreButton.setBounds(220, 150, 175, 30);
        moreButton.setText("Plus d'informations");
        moreButton.setVisible(true);
        moreButton.addActionListener(this);
        panel.add(moreButton);

        comboBox.setBounds(100, 230, 225, 30);
        comboBox.setVisible(true);
        panel.add(comboBox);

        generated.setBounds(100, 120, 275, 100);
        generated.setForeground(Color.white);
        generated.setText("Choisissez un pays et cliquez sur Valider");
        generated.setVisible(true);
        generated.setLineWrap(true);
        generated.setAutoscrolls(true);
        generated.setOpaque(false);
        generated.setEditable(false);
        panel.add(generated);

        valider.setBounds(220, 15, 150, 50);
        valider.setText("Valider");
        valider.setVisible(true);
        valider.addActionListener(this);
        panel.add(valider);

        genLabel.setBounds(100, 100, 200, 15);
        genLabel.setText("Bienvenue dans GeoPenguin");
        genLabel.setForeground(Color.white);
        genLabel.setVisible(true);
        panel.add(genLabel);

        banner.setBounds(5, 5, 77, 100);
        banner.setIcon(new ImageIcon(Main.class.getResource("/penguin.png")));
        banner.setVisible(true);
        panel.add(banner);

        background.setBounds(0, 0, 400, 350);
        background.setIcon(new ImageIcon(Main.class.getResource("/background.png")));
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
            "\nISO2 : " + c.getISO2() + "\nISO3 : " + c.getISO3() + "\nNumeric Value : " + c.getNumeric());
        }
        else if (e.getSource() == moreButton) {
            new FlagWindow();
        }
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
