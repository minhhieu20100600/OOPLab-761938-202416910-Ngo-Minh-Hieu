package hust.soict.dsai.aims.customer.controller;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.customer.AimsCustomerApplication;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewStoreController {
    @FXML
    private GridPane gridPane;

    @FXML
    private Button btnViewCart;

    private Store store;
    private Cart cart;

    public void setStoreAndCart(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        refreshStoreItems();
    }

    public void refreshStoreItems() {
        if (gridPane == null || store == null || cart == null) {
            return;
        }
        gridPane.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Media media : store.getItemsInStore()) {
            try {
                FXMLLoader loader = new FXMLLoader(AimsCustomerApplication.class.getResource("view/Item.fxml"));
                Parent itemRoot = loader.load();
                ItemController controller = loader.getController();
                controller.setData(media, cart, this::refreshStoreItems);
                gridPane.add(itemRoot, column, row);
                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            } catch (Exception exception) {
                throw new IllegalStateException("Cannot load item view", exception);
            }
        }
    }

    @FXML
    private void btnViewCartPressed() {
        switchToCart();
    }

    private void switchToCart() {
        try {
            FXMLLoader loader = new FXMLLoader(AimsCustomerApplication.class.getResource("view/Cart.fxml"));
            Parent root = loader.load();
            CartController controller = loader.getController();
            controller.setStoreAndCart(store, cart);

            Stage stage = (Stage) btnViewCart.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception exception) {
            throw new IllegalStateException("Cannot open cart screen", exception);
        }
    }
}
