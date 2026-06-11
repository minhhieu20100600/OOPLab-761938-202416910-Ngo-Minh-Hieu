package hust.soict.dsai.aims.screen;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.playable.Playable;

public class MediaStore extends JPanel {
    public MediaStore(Media media, Cart cart) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(java.awt.Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JLabel title = new JLabel(media.getTitle(), JLabel.CENTER);
        JLabel cost = new JLabel(String.format("%.2f $", media.getCost()), JLabel.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        infoPanel.add(title);
        infoPanel.add(cost);

        JButton addToCartButton = new JButton("Add to cart");
        addToCartButton.addActionListener(event -> {
            cart.addMedia(media);
            JOptionPane.showMessageDialog(this, "Added to cart: " + media.getTitle());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addToCartButton);

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(event -> {
                try {
                    ((Playable) media).play();
                    JOptionPane.showMessageDialog(this, "Playing: " + media.getTitle());
                } catch (PlayerException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage());
                }
            });
            buttonPanel.add(playButton);
        }

        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
