package bookmark;

import bookmark.commands.BookmarkCommand;
import exceptions.InvalidCommandException;
import studyit.StudyItLog;


import java.util.ArrayList;

public class BookmarkRun {
    private ArrayList<BookmarkCategory> bookmarkCategories;
    private BookmarkUi bookmarkUi;
    private BookmarkParser bookmarkParser;
    private BookmarkStorage bookmarkStorage;
    private int mode = 0;

    public BookmarkRun() {
        bookmarkUi = new BookmarkUi();
        bookmarkParser = new BookmarkParser();
        bookmarkStorage = new BookmarkStorage("data/bookmark.txt");
        bookmarkCategories = bookmarkStorage.loadFile();
    }

    public void run(String command) {
        try {
            BookmarkCommand c = bookmarkParser.evaluateInput(command,mode);
            c.executeCommand(bookmarkUi,bookmarkCategories,bookmarkStorage);
            mode = c.getCategoryNumber();
        } catch (InvalidCommandException e) {
            bookmarkUi.showInvalidBookmarkCommand();
            StudyItLog.logger.info("Cannot understand bookmark command");
        }
    }
}
