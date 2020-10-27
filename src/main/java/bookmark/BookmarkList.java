package bookmark;

public class BookmarkList {
    private String link;
    private Boolean star;

    public BookmarkList(String link) {
        this.link = link;
        this.star = false;
    }

    public String getLink() {
        return link;
    }

    public void markLinkAsStar() {
        if (!this.star) {
            this.star = true;
        } else if (star) {
            this.star = false;
        }
    }

    public String getStarIcon() {
        return (star ? "[\u2713]" : ""); //return tick symbols
    }

    public String toString() {
        return getStarIcon() + getLink();
    }

    public boolean getStar() {
        return star;
    }
}
