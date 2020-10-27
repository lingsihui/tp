package bookmark.commands;

import bookmark.BookmarkCategory;
import bookmark.BookmarkStorage;
import bookmark.BookmarkUi;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveLinkCommandTest {
    private BookmarkUi ui = new BookmarkUi();
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();
    private BookmarkStorage storage = new BookmarkStorage("data/bookmark.txt");


    @Test
    void executeCommand_removeValidLinkCommand_removeLinkCorrectly() {
        setUpBookmark();
        String inputString = "rm 1";
        int categoryNumber = 2;
        RemoveLinkCommand removeCommand = new RemoveLinkCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories,storage);
        assertEquals(0,categories.get(categoryNumber - 1).getLinks().size());
    }

    @Test
    void executeCommand_removeInValidCategoryCommand_doseNotRemoveLink() {
        setUpBookmark();
        String inputString = "rm 10000"; //rm 0
        int categoryNumber = 2;
        RemoveLinkCommand removeCommand = new RemoveLinkCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories,storage);
        assertEquals(1,categories.get(categoryNumber - 1).getLinks().size());
    }

    @Test
    void executeCommand_removeEmptyCategoryCommand_doesNotRemoveLink() {
        setUpBookmark();
        String inputString = "rm ";
        int categoryNumber = 2;
        RemoveLinkCommand removeCommand = new RemoveLinkCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories,storage);
        assertEquals(1,categories.get(categoryNumber - 1).getLinks().size());
    }

    @Test
    void executeCommand_removeNotANumber_doesNotRemoveLink() {
        setUpBookmark();
        String inputString = "rm abcdef";
        int categoryNumber = 2;
        RemoveLinkCommand removeCommand = new RemoveLinkCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories,storage);
        assertEquals(1,categories.get(categoryNumber - 1).getLinks().size());
    }

    @Test
    public void executeCommand_categoryNotChosen_doesNotRemoveLink() {
        setUpBookmark();
        String inputString = "rm 1";
        int categoryNumber = 0;
        RemoveLinkCommand removeCommand = new RemoveLinkCommand(inputString,categoryNumber);
        removeCommand.executeCommand(ui,categories,storage);
        assertEquals(0,categories.get(categoryNumber).getLinks().size());
        assertEquals(1,categories.get(categoryNumber + 1).getLinks().size());
    }

    private void setUpBookmark() {
        categories.add(new BookmarkCategory("NUS"));
        categories.add(new BookmarkCategory("Zoom"));
        String addLink = "add https://huhuhu.com";
        int categoryNumber = 2;
        AddLinkCommand command = new AddLinkCommand(addLink,categoryNumber);
        command.executeCommand(ui,categories,storage);
    }

}