package bookmark;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class BookmarkStorage {
    private final File bookmarkFile;
    private final String filePath;

    public BookmarkStorage(String filePath) {
        String dirPath = "data";
        File fileDir = new File(dirPath);

        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        this.filePath = filePath;
        bookmarkFile = new File(filePath);
    }

    public ArrayList<BookmarkCategory> loadFile() {
        try {
            Scanner s = new Scanner(bookmarkFile);
            ArrayList<BookmarkCategory> bookmarkCategories = new ArrayList<>();
            while (s.hasNext()) {
                String[] categories = s.nextLine().split(" , ");
                int i = 0;
                for (String category : categories) {
                    int x = 0;
                    String[] parseCategory = category.split(" = ");
                    String categoryName = parseCategory[0];
                    bookmarkCategories.add(new BookmarkCategory(categoryName));
                    if (parseCategory.length < 2) {
                        i++;
                        continue;
                    }
                    String[] links = parseCategory[1].split(" ");
                    for (String link : links) {
                        assert i >= 0 : "Problem reading file";
                        if (link.contains("|STAR|")) {
                            bookmarkCategories.get(i).addLink(link.substring(6));
                            bookmarkCategories.get(i).markLinkAsStar(x);
                        } else {
                            bookmarkCategories.get(i).addLink(link);
                        }
                        x++;
                    }
                    i++;
                }
            }
            return bookmarkCategories;
        } catch (FileNotFoundException e) {
            System.out.println("This file is not found, creating a new file now!");
            ArrayList<BookmarkCategory> newBookmarkCategories = new ArrayList<>();
            newBookmarkCategories.add(new BookmarkCategory("NUS"));
            newBookmarkCategories.add(new BookmarkCategory("Zoom"));
            newBookmarkCategories.add(new BookmarkCategory("Internship"));
            newBookmarkCategories.add(new BookmarkCategory("Hackathon"));
            newBookmarkCategories.add(new BookmarkCategory("Career Talk"));
            return newBookmarkCategories;
        }
    }

    public void saveLinksToFile(ArrayList<BookmarkCategory> categories) {
        try {
            FileWriter fw = new FileWriter(filePath, false); //true append, false overwrite
            for (BookmarkCategory category : categories) {
                fw.write(category.getName() + " = " + getCategoryLinks(category) + " , ");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

    private String getCategoryLinks(BookmarkCategory category) {
        String listOfLinks = "";
        int i = 1;
        for (BookmarkList link : category.getLinks()) {
            if (link.getStar()) {
                listOfLinks += "|STAR|";
            }
            listOfLinks += link.getLink() + " ";
        }
        return listOfLinks;
    }

}
