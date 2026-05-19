package hust.soict.dsai.aims.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

        setJMenuBar(createMenuBar());
        add(createHeader(), BorderLayout.NORTH);
        add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewCart = new JMenuItem("View Cart");
        viewCart.addActionListener(event -> new CartScreen(cart, this));

        menu.add(viewCart);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        return menuBar;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel title = new JLabel("AIMS Store");
        title.setFont(title.getFont().deriveFont(28.0f));
        title.setForeground(new Color(32, 82, 149));

        JButton cartButton = new JButton("View Cart");
        cartButton.addActionListener(event -> new CartScreen(cart, this));

        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cartButton);
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
