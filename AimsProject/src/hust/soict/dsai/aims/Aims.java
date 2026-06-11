package hust.soict.dsai.aims;

import java.util.Scanner;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.playable.Playable;
import hust.soict.dsai.aims.store.Store;

public class Aims {
    private static final Store store = new Store();
    private static final Cart cart = new Cart();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        seedStore();

        int choice;
        do {
            showMenu();
            choice = readInt();
            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    seeCurrentCart();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void seedStore() {
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f));
        store.addMedia(new Book("1984", "Fiction", "George Orwell", 15.0f));
        store.addMedia(new CompactDisc("Abbey Road", "Rock", "The Beatles", 12.99f,
                new Track[] {
                        new Track("Come Together", 259),
                        new Track("Something", 182)
                }));
    }

    public static void showMenu() {
        System.out.println("\nAIMS");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void viewStore() {
        int choice;
        do {
            store.printStore();
            System.out.println("\nOptions:");
            System.out.println("--------------------------------");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4: ");

            choice = readInt();
            switch (choice) {
                case 1:
                    showMediaDetails();
                    break;
                case 2:
                    addMediaToCartFromStore();
                    break;
                case 3:
                    playMediaFromStore();
                    break;
                case 4:
                    System.out.println(cart);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    public static void seeCurrentCart() {
        int choice;
        do {
            System.out.println(cart);
            System.out.println("\nOptions:");
            System.out.println("--------------------------------");
            System.out.println("1. Filter media in cart");
            System.out.println("2. Sort media in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4-5: ");

            choice = readInt();
            switch (choice) {
                case 1:
                    filterCart();
                    break;
                case 2:
                    sortCart();
                    break;
                case 3:
                    removeMediaFromCart();
                    break;
                case 4:
                    playMediaFromCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    public static void updateStore() {
        System.out.println("\nUpdate Store");
        System.out.println("--------------------------------");
        System.out.println("1. Add sample DVD");
        System.out.println("2. Remove media by ID");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");

        int choice = readInt();
        switch (choice) {
            case 1:
                store.addMedia(new DigitalVideoDisc("Aladdin", "Animation", "John Musker", 90, 18.99f));
                break;
            case 2:
                System.out.print("Enter ID to remove: ");
                Media media = store.getItem(readInt());
                if (media != null) {
                    store.removeMedia(media);
                } else {
                    System.out.println("Item not found!");
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void showMediaDetails() {
        System.out.print("Enter ID: ");
        Media media = store.getItem(readInt());
        if (media == null) {
            System.out.println("Item not found!");
            return;
        }

        System.out.println(media);
        mediaDetailsMenu(media);
    }

    private static void mediaDetailsMenu(Media media) {
        System.out.println("\nOptions:");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");

        int choice = readInt();
        switch (choice) {
            case 1:
                cart.addMedia(media);
                break;
            case 2:
                playMedia(media);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void addMediaToCartFromStore() {
        System.out.print("Enter ID to add: ");
        Media media = store.getItem(readInt());
        if (media != null) {
            cart.addMedia(media);
        } else {
            System.out.println("Item not found!");
        }
    }

    private static void playMediaFromStore() {
        System.out.print("Enter ID to play: ");
        Media media = store.getItem(readInt());
        if (media == null) {
            System.out.println("Item not found!");
            return;
        }
        playMedia(media);
    }

    private static void filterCart() {
        System.out.println("Filter options:");
        System.out.println("1. By ID");
        System.out.println("2. By title");
        System.out.print("Please choose a number: 1-2: ");

        int choice = readInt();
        switch (choice) {
            case 1:
                System.out.print("Enter ID: ");
                cart.searchId(readInt());
                break;
            case 2:
                System.out.print("Enter title keyword: ");
                cart.searchTitle(SCANNER.nextLine());
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void sortCart() {
        System.out.println("Sort options:");
        System.out.println("1. By title then cost");
        System.out.println("2. By cost then title");
        System.out.print("Please choose a number: 1-2: ");

        int choice = readInt();
        switch (choice) {
            case 1:
                cart.sortByTitleCost();
                break;
            case 2:
                cart.sortByCostTitle();
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        System.out.println(cart);
    }

    private static void removeMediaFromCart() {
        System.out.print("Enter ID to remove: ");
        Media media = cart.findById(readInt());
        if (media != null) {
            cart.removeMedia(media);
        } else {
            System.out.println("Media not found in cart!");
        }
    }

    private static void playMediaFromCart() {
        System.out.print("Enter ID to play: ");
        Media media = cart.findById(readInt());
        if (media == null) {
            System.out.println("Media not found in cart!");
            return;
        }
        playMedia(media);
    }

    private static void placeOrder() {
        System.out.printf("Order placed. Total cost: %.2f $%n", cart.totalCost());
        cart.clear();
    }

    private static void playMedia(Media media) {
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException exception) {
                System.err.println(exception.getMessage());
            }
        } else {
            System.out.println("This media cannot be played.");
        }
    }

    private static int readInt() {
        while (!SCANNER.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            SCANNER.nextLine();
        }
        int value = SCANNER.nextInt();
        SCANNER.nextLine();
        return value;
    }
}
