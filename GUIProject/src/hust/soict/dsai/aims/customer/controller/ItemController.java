package hust.soict.dsai.aims.customer.controller;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.playable.Playable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ItemController {
    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    private Media media;
    private Cart cart;
    private Runnable refreshAction;

    public void setData(Media media, Cart cart, Runnable refreshAction) {
        this.media = media;
        this.cart = cart;
        this.refreshAction = refreshAction;

        lblTitle.setText(media.getTitle());
        lblCost.setText(String.format("%.2f $", media.getCost()));
        btnPlay.setVisible(media instanceof Playable);
    }

    @FXML
    private void btnAddToCartClicked() {
        cart.addMedia(media);
        if (refreshAction != null) {
            refreshAction.run();
        }
        new Alert(AlertType.INFORMATION, "Added to cart: " + media.getTitle()).showAndWait();
    }

    @FXML
    private void btnPlayClicked() {
        if (!(media instanceof Playable)) {
            return;
        }
        try {
            ((Playable) media).play();
            new Alert(AlertType.INFORMATION, "Playing: " + media.getTitle()).showAndWait();
        } catch (PlayerException exception) {
            new Alert(AlertType.ERROR, exception.getMessage()).showAndWait();
        }
    }
}
