package hust.soict.dsai.aims.media;

import java.util.Objects;
import java.util.Comparator;

abstract public class Media implements Comparable<Media> {
    private static int nbMedias = 0;
    private int id;
    private String title;
    private String category;
    private float cost;

    public Media(String title) {
        this(title, null, 0f);
    }

    public Media(String title, String category, float cost) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost must be non-negative.");
        }
        this.title = title;
        this.category = category;
        this.cost = cost;
        nbMedias++;
        id = nbMedias;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }
    public static int getNbMedias() { return nbMedias; }

    protected void setTitle(String title) {
        this.title = title;
    }

    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return Float.compare(media.cost, cost) == 0 && Objects.equals(title, media.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cost);
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) {
            return 1;
        }
        int cmp = title.compareTo(other.title);
        if (cmp != 0) {
            return cmp;
        }
        return Float.compare(cost, other.cost);
    }
    //

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new Comparator<Media>() {
        public int compare(Media m1, Media m2) {
            int cmp = m1.getTitle().compareTo(m2.getTitle());
            if (cmp != 0) return cmp;
            return Float.compare(m1.getCost(), m2.getCost());
        }
    };
    //

    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new Comparator<Media>() {
        public int compare(Media m1, Media m2) {
            int cmp = Float.compare(m1.getCost(), m2.getCost());
            if (cmp != 0) return cmp;
            return m1.getTitle().compareTo(m2.getTitle());
        }
    };
    //
}
