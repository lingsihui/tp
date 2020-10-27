package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import exceptions.EmptyBookmarkException;

import java.util.ArrayList;

public class AddCategoryCommand extends BookmarkCommand {
    public static final int CAT_LENGTH = 3;
    private int categoryNumber;
    private String  categoryToAdd;
    private String categoryName;

    public AddCategoryCommand(String command,int categoryNumber) {
        this.categoryNumber = categoryNumber;
        this.categoryToAdd = command.trim();
        assert command.toLowerCase().startsWith("cat") : "Add category command is "
                + "called when line does not start with cat";
        assert categoryNumber >= 0 : "Missing category number";
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage storage) {
        try {
            evaluateCategory();
            categories.add(new BookmarkCategory(categoryName));
            System.out.println("Adding " + categoryName + " to bookmark category...");
            ui.showBookmarkCategoryList(categories);
            storage.saveLinksToFile(categories);
        } catch (EmptyBookmarkException e) {
            ui.showEmptyError();
        }
    }

    private void evaluateCategory() throws EmptyBookmarkException {
        if (categoryToAdd.length() <= CAT_LENGTH) {
            throw new EmptyBookmarkException();
        }
        assert categoryToAdd.length() > 0 : "Link should not be empty";
        categoryName = categoryToAdd.substring(CAT_LENGTH).trim();
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }
}
