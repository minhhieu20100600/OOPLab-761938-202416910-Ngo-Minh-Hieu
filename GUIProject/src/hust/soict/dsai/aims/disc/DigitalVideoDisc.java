package hust.soict.dsai.aims.disc;

import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.playable.Playable;

public class DigitalVideoDisc extends Media implements Playable {
    private String director;
    private int length;

    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        if (length < 0) {
            throw new IllegalArgumentException("Length must be non-negative.");
        }
        this.director = director;
        this.length = length;
    }

    public String getDirector() { return director; }
    public int getLength() { return length; }

    @Override
    public String toString() {
        return String.format("DVD - %s - %s - %s - %d: %.2f $", getTitle(), getCategory(), director, length, getCost());
    }

    @Override
    public void play() throws PlayerException {
        if (length <= 0) {
            System.err.println("ERROR: DVD length is invalid: " + length);
            throw new PlayerException("ERROR: DVD " + getTitle() + " cannot be played.");
        }
        System.out.println("Playing DVD: " + getTitle());
        System.out.println("DVD length: " + length);
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }
}
