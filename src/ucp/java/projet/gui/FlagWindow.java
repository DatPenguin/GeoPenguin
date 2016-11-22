package ucp.java.projet.gui;

import ucp.java.projet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by penguin on 15/11/16.
 */
public class FlagWindow extends JFrame {

    private JPanel panel = new JPanel();
    private JLabel background = new JLabel();
    private JLabel errorLabel = new JLabel();

    public FlagWindow() {
        panel.setLayout(null);

        this.setTitle("Drapeau");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/penguin.png"));
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(panel);

        background.setBounds(0, -14, 500, 300);

        BufferedImage i = null;

        try {
                i = toBufferedImage(new ImageIcon("flags/"
                    + Main.countryList.get(Main.getWindow().getComboBox().getSelectedItem()).getISO2().toLowerCase()
                    + "-lgflag.gif").getImage());
        } catch (Exception e) {
            System.err.println("Flag not found");
        }

        if (i != null)
            background.setIcon(new ImageIcon(i.getScaledInstance(500, 273, Image.SCALE_SMOOTH)));
        background.setVisible(true);
        panel.add(background);

        errorLabel.setBounds(160, 120, 1000, 15);
        errorLabel.setText("Aucun drapeau trouvable");
        errorLabel.setForeground(Color.BLACK);
        errorLabel.setFont(errorLabel.getFont().deriveFont(Font.PLAIN).deriveFont(20f));
        errorLabel.setVisible(true);
        panel.add(errorLabel);

        panel.setVisible(true);
        this.setVisible(true);
    }

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

}
