package bookmark.commands;

import bookmark.BookmarkUi;
import bookmark.BookmarkCategory;



import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddLinkCommandTest {
    private BookmarkUi ui = new BookmarkUi();
    private ArrayList<BookmarkCategory> categories = new ArrayList<>();

    @Test
    public void executeCommand_addValidLinkCommand_addLinkCorrectly() {
        categories.add(new BookmarkCategory("NUS"));
        categories.add(new BookmarkCategory( "Zoom"));
        String inputString = "add https://facebook.com";
        int categoryNumber = 2;
        AddLinkCommand command = new AddLinkCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories,);
        assertEquals(1,categories.get(categoryNumber - 1).getLinks().size());
    }

    @Test
    public void executeCommand_addInValidLinkCommand_doesNotAddLink() {
        categories.add(new BookmarkCategory("NUS"));
        categories.add(new BookmarkCategory( "Zoom"));
        String inputString = "add huhuhuh";
        int categoryNumber = 2;
        AddLinkCommand command = new AddLinkCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories);
        assertEquals(0,categories.get(categoryNumber - 1).getLinks().size());
    }

    @Test
    public void executeCommand_addEmptyLinkCommand_doesNotAddLink() {
        categories.add(new BookmarkCategory("NUS"));
        categories.add(new BookmarkCategory( "Zoom"));
        String inputString = "add ";
        int categoryNumber = 2;
        AddLinkCommand command = new AddLinkCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories);
        assertEquals(0,categories.get(categoryNumber - 1).getLinks().size());
    }

    @Test
    public void executeCommand_categoryNotChosen_doesNotAddLink() {
        categories.add(new BookmarkCategory("NUS"));
        categories.add(new BookmarkCategory( "Zoom"));
        String inputString = "add ";
        int categoryNumber = 0;
        AddLinkCommand command = new AddLinkCommand(inputString,categoryNumber);
        command.executeCommand(ui,categories);
        assertEquals(0,categories.get(categoryNumber).getLinks().size());
        assertEquals(0,categories.get(categoryNumber + 1).getLinks().size());
    }


    @Test
    void getCategoryNumber() {
    }
}