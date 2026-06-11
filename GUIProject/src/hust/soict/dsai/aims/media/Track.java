package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.playable.Playable;

public class Track implements Playable, Comparable<Track> {
    private final String title;
    private final int length;

    public Track(String title, int length) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Track title must not be empty.");
        }
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() throws PlayerException {
        if (length <= 0) {
            throw new PlayerException("ERROR: Track " + title + " has invalid length: " + length);
        }
        System.out.println("Playing track: " + title);
        System.out.println("Track length: " + length);
    }

    @Override
    public int compareTo(Track other) {
        if (other == null) {
            return 1;
        }
        int cmp = title.compareTo(other.title);
        if (cmp != 0) {
            return cmp;
        }
        return Integer.compare(length, other.length);
    }
}
