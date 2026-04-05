package Lab2.section16;

public class Main {
    public static void main(String[] args) {


        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King");
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars");
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin");


        System.out.println("DVD 1: " + dvd1.getTitle() + " - ID: " + dvd1.getId());
        System.out.println("DVD 2: " + dvd2.getTitle() + " - ID: " + dvd2.getId());
        System.out.println("DVD 3: " + dvd3.getTitle() + " - ID: " + dvd3.getId());


        System.out.println("Tổng số DVD hiện có: " + DigitalVideoDisc.getNbDigitalVideoDiscs());
    }
}