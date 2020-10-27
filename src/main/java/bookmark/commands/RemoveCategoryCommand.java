package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import exceptions.EmptyBookmarkException;
import exceptions.InvalidBookmarkException;

import java.util.ArrayList;

public class RemoveCategoryCommand extends BookmarkCommand {
    public static final int DELETE_LENGTH = 6;
    private int catNumber;
    private int categoryNumber;
    private String line;

    public RemoveCategoryCommand(String line, int categoryNumber) {
        this.categoryNumber = categoryNumber;
        this.line = line.trim();
        assert line.toLowerCase().startsWith("delete") : "Delete category command is called wrongly";
        assert categoryNumber >= 0 : "Missing category number";
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage storage) {
        try {
            if (categoryNumber != 0) {
                System.out.println("Please go back to main bookmark menu to delete a category");
                assert categoryNumber > 0 : "Choose Category message is called when category number is chosen";
            } else if (line.length() <= DELETE_LENGTH) {
                throw new EmptyBookmarkException();
            } else {
                line = line.substring(DELETE_LENGTH);
                assert line.length() > 0 : "Link should not be empty";
                catNumber = evaluateLinkNumber(categories);
                System.out.println("Deleting Category: " + categories.get(catNumber - 1).getName());
                categories.remove(catNumber - 1);
                ui.showBookmarkCategoryList(categories);
                storage.saveLinksToFile(categories);
            }
        } catch (EmptyBookmarkException e) {
            ui.showEmptyError();
        } catch (InvalidBookmarkException e) {
            ui.showInvalidError();
        } catch (NumberFormatException e) {
            ui.showInvalidNumberError();
        }
    }

    private int evaluateLinkNumber(ArrayList<BookmarkCategory> categories)
            throws NumberFormatException, InvalidBookmarkException {
        line = line.trim();
        int catNum = Integer.parseInt(line);
        if (catNum == 0 || catNum > categories.size()) {
            throw new InvalidBookmarkException();
        }
        return catNum;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }
}





