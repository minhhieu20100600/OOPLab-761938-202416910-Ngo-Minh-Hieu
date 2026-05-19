package hust.soict.dsai.aims.screen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.playable.Playable;

public class CartScreen extends JFrame {
    private final Cart cart;
    private final StoreScreen storeScreen;
    private final DefaultTableModel tableModel;
    private final JLabel totalLabel;
    private final JTable table;

    public CartScreen(Cart cart, StoreScreen storeScreen) {
        this.cart = cart;
        this.storeScreen = storeScreen;
        this.tableModel = new DefaultTableModel(new Object[] {"ID", "Title", "Category", "Cost"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(tableModel);
        this.totalLabel = new JLabel();

        setTitle("Cart");
        setSize(800, 500);
        setLocationRelativeTo(storeScreen);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);

        refreshCart();
        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("Back to store");
        backButton.addActionListener(event -> dispose());

        JButton playButton = new JButton("Play");
        playButton.addActionListener(event -> playSelectedMedia());

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(event -> removeSelectedMedia());

        panel.add(new JLabel("Current cart"));
        panel.add(backButton);
        panel.add(playButton);
        panel.add(removeButton);
        return panel;
    }

    private JPanel createFooter() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton placeOrderButton = new JButton("Place order");
        placeOrderButton.addActionListener(event -> {
            JOptionPane.showMessageDialog(this,
                    String.format("Order placed. Total cost: %.2f $", cart.totalCost()));
            cart.clear();
            refreshCart();
        });

        panel.add(totalLabel);
        panel.add(placeOrderButton);
        return panel;
    }

    private void refreshCart() {
        tableModel.setRowCount(0);
        for (Media media : cart.getItems()) {
            tableModel.addRow(new Object[] {media.getId(), media.getTitle(), media.getCategory(), media.getCost()});
        }
        totalLabel.setText(String.format("Total: %.2f $", cart.totalCost()));
        if (storeScreen != null) {
            storeScreen.refreshStoreItems();
        }
    }

    private Media getSelectedMedia() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an item first.");
            return null;
        }

        int mediaId = (Integer) tableModel.getValueAt(selectedRow, 0);
        return cart.findById(mediaId);
    }

    private void removeSelectedMedia() {
        Media media = getSelectedMedia();
        if (media == null) {
            return;
        }
        cart.removeMedia(media);
        refreshCart();
    }

    private void playSelectedMedia() {
        Media media = getSelectedMedia();
        if (media == null) {
            return;
        }
        if (media instanceof Playable) {
            ((Playable) media).play();
            JOptionPane.showMessageDialog(this, "Playing: " + media.getTitle());
            return;
        }
        JOptionPane.showMessageDialog(this, "This media cannot be played.");
    }
}
