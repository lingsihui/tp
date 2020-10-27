package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackCommandTest {
    private BookmarkUi ui = new BookmarkUi();
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();
    private BookmarkStorage storage = new BookmarkStorage("data/bookmark.txt");


    @Test
    void executeCommand_backCommandInMain_showByeMessage() {
        int categoryNumber = 0;
        BackCommand command = new BackCommand(categoryNumber);
        command.executeCommand(ui,categories,storage);
        assertEquals(0,command.getCategoryNumber());
    }

    @Test
    void executeCommand_backCommandInCategory_returnToBookmarkMain() {
        int categoryNumber = 1;
        BackCommand command = new BackCommand(categoryNumber);
        command.executeCommand(ui,categories,storage);
        assertEquals(0,command.getCategoryNumber());
    }
}