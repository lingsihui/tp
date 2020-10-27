package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import exceptions.InvalidBookmarkException;
import exceptions.EmptyBookmarkException;

import java.util.ArrayList;

public class AddLinkCommand extends BookmarkCommand {
    public static final int ADD_LENGTH = 3;
    private String line;
    private int categoryNumber;
    private String link;

    public AddLinkCommand(String line, int categoryNumber) {
        this.categoryNumber = categoryNumber;
        this.line = line.trim();
        assert line.toLowerCase().startsWith("add") : "Add link command starts with add.";
        assert categoryNumber >= 0 : "Category number not negative.";
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage storage) {
        try {
            if (categoryNumber == 0) {
                ui.printChooseCategoryMessage();
            } else {
                assert categoryNumber > 0 : "Category number not 0";
                evaluateLink();
                categories.get(categoryNumber - 1).addLink(link);
                ui.showBookmarkLinkList(categories.get(categoryNumber - 1).getLinks());
                storage.saveLinksToFile(categories);
            }
        } catch (EmptyBookmarkException e) {
            ui.showEmptyError("link");
        } catch (InvalidBookmarkException e) {
            ui.showInvalidError("link");
        }
    }

    private void evaluateLink() throws EmptyBookmarkException, InvalidBookmarkException {
        if (line.length() <= ADD_LENGTH) {
            throw new EmptyBookmarkException();
        }
        assert line.length() > 0 : "Link should not be empty";
        link = line.substring(ADD_LENGTH).trim();
        if (!link.contains("https://") || !link.contains(".") || link.contains(" ")) {
            throw new InvalidBookmarkException();
        }
        assert link.contains("https://") && link.contains(".") && !link.contains(" ") : "Invalid link";
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }
}
