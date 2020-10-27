package bookmark;

import java.util.ArrayList;

public class BookmarkUi {

    public BookmarkUi() {

    }

    public static void printWelcomeBookmarkMessage() {
        System.out.println("Welcome to B00KMARK!");
        System.out.println("Choose your category by typing \"bm <category index>!\"");
        //showBookmarkCategoryList();
    }

    public void showBookmarkCategoryList(ArrayList<BookmarkCategory> bookmarkCategories) {
        System.out.println("Here are the categories available:");
        int i = 1;
        for (BookmarkCategory category: bookmarkCategories) {
            System.out.println(i + "." + category.getName());
            i++;
        }
    }

    public void showBookmarkLinkList(ArrayList<BookmarkList> links) {
        System.out.println("Bookmarks:");
        if (links.size() == 0) {
            System.out.println("<empty>");
        } else {
            int i = 1;
            for (BookmarkList link: links) {
                System.out.println(i + "." + link);
                i++;
            }
        }
    }

    public void printGoodbyeMessage() {
        System.out.println("Use \"exit\" to exit the mode or enter another category\n"
                + "using \"bm <category index>\"");
    }

    public void showBookmarkList(ArrayList<BookmarkCategory> categories) {
        System.out.println("Here is the list");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("Category: " + categories.get(i).getName());
            showBookmarkLinkList(categories.get(i).getLinks());
        }
    }

    public void showInvalidBookmarkCommand() {
        System.out.println("Invalid Bookmark commands");
    }

    public void printChooseCategoryMessage() {
        System.out.println("Please choose a category.");
    }

    public void showEmptyError(String item) {
        System.out.println("Empty " + item + ", please enter a valid link.");
    }

    public void showInvalidError(String item) {
        System.out.println("Not a valid " + item + ", please enter a valid link.");
    }

    public void showInvalidNumberError() {
        System.out.println("Enter a number");
    }

    public void showModeChangeMessage(ArrayList<BookmarkCategory> categories, int categoryNumberInList) {
        System.out.println("You are now in " + categories.get(categoryNumberInList).getName() + " category");
        System.out.println("The following are your current bookmarks in this category");
        showBookmarkLinkList(categories.get(categoryNumberInList).getLinks());
        System.out.println("Add new bookmarks by using \"add <link>\"");
    }

    public void showAlreadyInModeMessage() {
        System.out.println("Already in chosen Category");
    }

    public void showStarBookmarks(ArrayList<BookmarkCategory> categories) {
        int i = 0;
        System.out.println("Starred bookmarks: ");
        for(BookmarkCategory category : categories) {
            for (BookmarkList link : category.getLinks()){
                if (link.getStar()){
                    i++;
                    System.out.println(i + "." + link);
                }
            }
        }

    }
}
