package hust.soict.dsai.aims.screen;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AbstractAddItemScreen {
    public AddCompactDiscToStoreScreen(Store store, StoreScreen storeScreen) {
        super("Add Compact Disc", store, storeScreen);

        JPanel formPanel = createFormPanel(5);
        JTextField titleField = addField(formPanel, "Title");
        JTextField categoryField = addField(formPanel, "Category");
        JTextField artistField = addField(formPanel, "Artist");
        JTextField costField = addField(formPanel, "Cost");
        JTextField tracksField = addField(formPanel, "Tracks");

        JButton submitButton = createSubmitButton(() -> {
            try {
                String[] tracks = tracksField.getText().trim().isEmpty()
                        ? new String[0]
                        : tracksField.getText().split("\\s*,\\s*");
                CompactDisc cd = new CompactDisc(
                        titleField.getText().trim(),
                        categoryField.getText().trim(),
                        artistField.getText().trim(),
                        parseFloat(costField.getText(), "Cost"),
                        tracks);
                store.addMedia(cd);
                showSuccessMessage(cd.getTitle());
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        add(formPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}
