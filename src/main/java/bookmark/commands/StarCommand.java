package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import exceptions.EmptyBookmarkException;
import exceptions.InvalidBookmarkException;

import java.util.ArrayList;

public class StarCommand extends BookmarkCommand{
    public static final int STAR_LENGTH = 4;
    private int chosenCategory;
    private String line;
    private int starLinkNumber;

    public StarCommand(String line, int chosenCategory){
        this.line = line.trim();
        this.chosenCategory = chosenCategory;
    }

    public void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories, BookmarkStorage storage) {
        try {
            if (chosenCategory == 0) {
                ui.printChooseCategoryMessage();
            } else if (line.length() <= STAR_LENGTH) {
                throw new EmptyBookmarkException();
            } else {
                assert chosenCategory > 0 : "Category number is not chosen";
                evaluateStarNumber(categories);
                categories.get(chosenCategory - 1).markLinkAsStar(starLinkNumber-1);
                ui.showBookmarkLinkList(categories.get(chosenCategory - 1).getLinks());
                ui.showStarBookmarks(categories);
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

    private void evaluateStarNumber(ArrayList<BookmarkCategory> categories)
            throws NumberFormatException, InvalidBookmarkException {
        line = line.substring(STAR_LENGTH).trim();
        int catNum = Integer.parseInt(line);
        if (catNum == 0 || catNum > categories.get(chosenCategory - 1).getLinks().size()) {
            throw new InvalidBookmarkException();
        }
        starLinkNumber = catNum;
    }

    public int getCategoryNumber(){
        return chosenCategory;
    }
}
