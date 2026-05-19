package hust.soict.dsai.aims.screen;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AbstractAddItemScreen {
    public AddDigitalVideoDiscToStoreScreen(Store store, StoreScreen storeScreen) {
        super("Add Digital Video Disc", store, storeScreen);

        JPanel formPanel = createFormPanel(5);
        JTextField titleField = addField(formPanel, "Title");
        JTextField categoryField = addField(formPanel, "Category");
        JTextField directorField = addField(formPanel, "Director");
        JTextField lengthField = addField(formPanel, "Length");
        JTextField costField = addField(formPanel, "Cost");

        JButton submitButton = createSubmitButton(() -> {
            try {
                DigitalVideoDisc dvd = new DigitalVideoDisc(
                        titleField.getText().trim(),
                        categoryField.getText().trim(),
                        directorField.getText().trim(),
                        parseInt(lengthField.getText(), "Length"),
                        parseFloat(costField.getText(), "Cost"));
                store.addMedia(dvd);
                showSuccessMessage(dvd.getTitle());
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        add(formPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}
