package hust.soict.dsai.aims.customer;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.customer.controller.ViewStoreController;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AimsCustomerApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Store store = createStore();
        Cart cart = new Cart();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Store.fxml"));
        Scene scene = new Scene(loader.load());
        ViewStoreController controller = loader.getController();
        controller.setStoreAndCart(store, cart);

        stage.setTitle("AIMS");
        stage.setScene(scene);
        stage.show();
    }

    private Store createStore() {
        Store store = new Store();
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f));
        store.addMedia(new Book("1984", "Fiction", "George Orwell", 15.00f));
        store.addMedia(new CompactDisc("Abbey Road", "Rock", "The Beatles", 12.99f,
                new Track[] {
                    new Track("Come Together", 259),
                    new Track("Something", 182)
                }));
        return store;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
