package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;


import java.util.ArrayList;

public abstract class BookmarkCommand {
    public abstract void executeCommand(BookmarkUi ui, ArrayList<BookmarkCategory> categories,
                                        BookmarkStorage bookmarkStorage);

    public abstract int getCategoryNumber();
}
