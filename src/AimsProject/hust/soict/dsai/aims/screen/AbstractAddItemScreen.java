package hust.soict.dsai.aims.screen;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.store.Store;

public abstract class AbstractAddItemScreen extends JFrame {
    protected final Store store;
    protected final StoreScreen storeScreen;

    protected AbstractAddItemScreen(String title, Store store, StoreScreen storeScreen) {
        this.store = store;
        this.storeScreen = storeScreen;

        setTitle(title);
        setSize(450, 300);
        setLocationRelativeTo(storeScreen);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    protected JPanel createFormPanel(int rows) {
        JPanel panel = new JPanel(new GridLayout(rows, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        return panel;
    }

    protected JTextField addField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        JTextField field = new JTextField();
        panel.add(label);
        panel.add(field);
        return field;
    }

    protected JButton createSubmitButton(Runnable action) {
        JButton button = new JButton("Add");
        button.addActionListener(event -> action.run());
        return button;
    }

    protected float parseFloat(String value, String fieldName) {
        try {
            return Float.parseFloat(value.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(fieldName + " must be a number.");
        }
    }

    protected int parseInt(String value, String fieldName) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(fieldName + " must be an integer.");
        }
    }

    protected void showSuccessMessage(String mediaTitle) {
        if (storeScreen != null) {
            storeScreen.refreshStoreItems();
        }
        JOptionPane.showMessageDialog(this, "Added to store: " + mediaTitle);
        dispose();
    }
}
