package hust.soict.dsai.aims.customer.controller;

import java.util.stream.Collectors;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.customer.AimsCustomerApplication;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.playable.Playable;
import hust.soict.dsai.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {
    @FXML
    private Button btnViewStore;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label costLabel;

    private Store store;
    private Cart cart;
    private final ObservableList<Media> tableItems = FXCollections.observableArrayList();
    private FilteredList<Media> filteredItems;

    @FXML
    private void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        filteredItems = new FilteredList<>(tableItems, media -> true);
        tblMedia.setItems(filteredItems);

        ChangeListener<Media> selectionListener = (observable, oldValue, newValue) -> updateButtonBar(newValue);
        tblMedia.getSelectionModel().selectedItemProperty().addListener(selectionListener);

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> showFilteredMedia());
    }

    public void setStoreAndCart(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        refreshTable();
    }

    private void refreshTable() {
        tableItems.setAll(cart.getItems());
        costLabel.setText(String.format("%.2f $", cart.totalCost()));
        showFilteredMedia();
    }

    private void updateButtonBar(Media selectedMedia) {
        boolean hasSelection = selectedMedia != null;
        btnRemove.setVisible(hasSelection);
        btnPlay.setVisible(hasSelection && selectedMedia instanceof Playable);
    }

    private void showFilteredMedia() {
        String keyword = tfFilter.getText() == null ? "" : tfFilter.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            filteredItems.setPredicate(media -> true);
            return;
        }

        if (radioBtnFilterId.isSelected()) {
            filteredItems.setPredicate(media -> String.valueOf(media.getId()).contains(keyword));
            return;
        }

        filteredItems.setPredicate(media -> media.getTitle().toLowerCase().contains(keyword));
    }

    @FXML
    private void btnViewStorePressed() {
        switchToStore();
    }

    @FXML
    private void btnPlayPressed() {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (!(selectedMedia instanceof Playable)) {
            return;
        }
        try {
            ((Playable) selectedMedia).play();
            new Alert(AlertType.INFORMATION, "Playing: " + selectedMedia.getTitle()).showAndWait();
        } catch (PlayerException exception) {
            new Alert(AlertType.ERROR, exception.getMessage()).showAndWait();
        }
    }

    @FXML
    private void btnRemovePressed() {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia == null) {
            return;
        }
        cart.removeMedia(selectedMedia);
        refreshTable();
    }

    @FXML
    private void btnPlaceOrderPressed() {
        new Alert(AlertType.INFORMATION, String.format("Order placed. Total cost: %.2f $", cart.totalCost())).showAndWait();
        cart.clear();
        refreshTable();
    }

    private void switchToStore() {
        try {
            FXMLLoader loader = new FXMLLoader(AimsCustomerApplication.class.getResource("view/Store.fxml"));
            Parent root = loader.load();
            ViewStoreController controller = loader.getController();
            controller.setStoreAndCart(store, cart);

            Stage stage = (Stage) btnViewStore.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception exception) {
            throw new IllegalStateException("Cannot open store screen", exception);
        }
    }
}
