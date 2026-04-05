package Lab2.section14;

import Lab2.section7_10.DigitalVideoDisc;

public class Aims {
    public static void main(String[] args) {
        Cart anOrder = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", "John Musker", 90, 18.99f);

        anOrder.addDigitalVideoDisc(dvd1, dvd2);

        DigitalVideoDisc[] list = {dvd3};
        anOrder.addDigitalVideoDisc(list);

        anOrder.printCart();
    }
}