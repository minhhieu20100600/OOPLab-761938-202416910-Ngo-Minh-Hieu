package hust.soict.dsai.aims.screen;

import javax.swing.SwingUtilities;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.store.Store;

public class AimsScreen {
    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();

        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f));
        store.addMedia(new Book("1984", "Fiction", "George Orwell", 15.00f));
        store.addMedia(new CompactDisc("Abbey Road", "Rock", "The Beatles", 12.99f,
                new String[] {"Come Together", "Something"}));

        SwingUtilities.invokeLater(() -> new StoreScreen(store, cart));
    }
}
