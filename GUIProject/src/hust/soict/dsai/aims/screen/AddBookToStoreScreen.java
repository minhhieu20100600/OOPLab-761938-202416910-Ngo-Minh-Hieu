package hust.soict.dsai.aims.screen;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;

public class AddBookToStoreScreen extends AbstractAddItemScreen {
    public AddBookToStoreScreen(Store store, StoreScreen storeScreen) {
        super("Add Book", store, storeScreen);

        JPanel formPanel = createFormPanel(4);
        JTextField titleField = addField(formPanel, "Title");
        JTextField categoryField = addField(formPanel, "Category");
        JTextField authorField = addField(formPanel, "Author");
        JTextField costField = addField(formPanel, "Cost");

        JButton submitButton = createSubmitButton(() -> {
            try {
                Book book = new Book(
                        titleField.getText().trim(),
                        categoryField.getText().trim(),
                        authorField.getText().trim(),
                        parseFloat(costField.getText(), "Cost"));
                store.addMedia(book);
                showSuccessMessage(book.getTitle());
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });

        add(formPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}
