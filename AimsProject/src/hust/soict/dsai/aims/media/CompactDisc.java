package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.playable.Playable;

public class CompactDisc extends Media implements Playable {
    private String artist;
    private final List<Track> tracks;

    public CompactDisc(String title, String category, String artist, float cost, String[] tracks) {
        this(title, category, artist, cost, toTrackList(tracks));
    }

    public CompactDisc(String title, String category, String artist, float cost, Track[] tracks) {
        super(title, category, cost);
        this.artist = artist;
        this.tracks = new ArrayList<>();
        if (tracks != null) {
            Collections.addAll(this.tracks, tracks);
        }
    }

    public String getArtist() { return artist; }
    public List<Track> getTracks() { return Collections.unmodifiableList(tracks); }

    public int getLength() {
        int total = 0;
        for (Track track : tracks) {
            total += track.getLength();
        }
        return total;
    }

    private static Track[] toTrackArray(String[] trackTitles) {
        if (trackTitles == null) {
            return new Track[0];
        }
        Track[] converted = new Track[trackTitles.length];
        for (int i = 0; i < trackTitles.length; i++) {
            converted[i] = new Track(trackTitles[i], 0);
        }
        return converted;
    }

    private static Track[] toTrackList(String[] trackTitles) {
        return toTrackArray(trackTitles);
    }

    @Override
    public String toString() {
        return String.format("CD - %s - %s by %s: %.2f $", getTitle(), getCategory(), artist, getCost());
    }
    //

    @Override
    public void play() throws PlayerException {
        if (getLength() <= 0) {
            System.err.println("ERROR: CD length is invalid: " + getLength());
            throw new PlayerException("ERROR: CD " + getTitle() + " cannot be played.");
        }
        System.out.println("Playing CD by " + artist);
        System.out.println("CD title: " + getTitle());
        for (Track track : tracks) {
            try {
                track.play();
            } catch (PlayerException exception) {
                System.err.println(exception.getMessage());
                throw exception;
            }
        }
    }
    //
}
