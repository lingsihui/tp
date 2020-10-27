package bookmark;

import java.util.ArrayList;

public class BookmarkCategory {
    private String name;
    private ArrayList<BookmarkList> links = new ArrayList<>();

    public BookmarkCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<BookmarkList> getLinks() {
        return links;
    }

    public void addLink(String link) {
        links.add(new BookmarkList(link));
    }

    public void removeLink(int number) {
        links.remove(number - 1);
    }

    public void markLinkAsStar(int number) {
        links.get(number).markLinkAsStar();
    }
}
