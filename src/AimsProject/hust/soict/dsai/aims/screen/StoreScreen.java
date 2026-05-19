package hust.soict.dsai.aims.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class StoreScreen extends JFrame {
    private final Store store;
    private final Cart cart;
    private JPanel centerPanel;

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;

        setTitle("AIMS Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        header.setBackground(Color.WHITE);
        header.add(new javax.swing.JLabel("AIMS Store"));
        return header;
    }

    private JScrollPane createCenter() {
        centerPanel = new JPanel(new GridLayout(0, 3, 15, 15));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        refreshStoreItems();
        return new JScrollPane(centerPanel);
    }

    public void refreshStoreItems() {
        centerPanel.removeAll();
        for (Media media : store.getItemsInStore()) {
            centerPanel.add(new MediaStore(media, cart));
        }
        centerPanel.revalidate();
        centerPanel.repaint();
    }
}
