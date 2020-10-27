package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import exceptions.InvalidBookmarkException;
import exceptions.EmptyBookmarkException;

import java.util.ArrayList;

public class RemoveLinkCommand extends BookmarkCommand {
    public static final int RM_LENGTH = 2;
    private int linkNumber;
    private int categoryNumber;
    private String line;

    public RemoveLinkCommand(String line, int categoryNumber) {
        this.categoryNumber = categoryNumber;
        this.line = line.trim();
        assert line.startsWith("rm") : "Remove link command is called when line does not start with rm";
        assert categoryNumber >= 0 : "Missing category number";
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage storage) {
        try {
            if (categoryNumber == 0) {
                ui.printChooseCategoryMessage();
                assert categoryNumber == 0 : "Choose Category message is called when category number is chosen";
            } else if (line.length() <= RM_LENGTH) {
                throw new EmptyBookmarkException();
            } else {
                line = line.substring(RM_LENGTH);
                assert line.length() > 0 : "Link should not be empty";
                linkNumber = evaluateLinkNumber(categories);
                System.out.println("Removing link: "
                        + categories.get(categoryNumber - 1).getLinks().get(linkNumber - 1));
                categories.get(categoryNumber - 1).removeLink(linkNumber);
                ui.showBookmarkLinkList(categories.get(categoryNumber - 1).getLinks());
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
        int linkNum = Integer.parseInt(line);
        if (linkNum == 0 || linkNum > categories.get(categoryNumber - 1).getLinks().size()) {
            throw new InvalidBookmarkException();
        }
        return linkNum;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }
}
