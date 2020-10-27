package userinterface;

import studyit.Mode;
import studyit.StudyIt;

public class HelpMessage extends Ui {
    private static final String generalCommands = "Here are the general commands available:\n"
            + "help                 - prints out help message\n"
            + "location             - tells you your current mode\n"
            + "cd <mode index/name> - changes the program to the corresponding mode\n"
            + "exit                 - exit the program/mode you are currently at\n";

    private static final String currentModes = "These are the modes you can go to:\n"
            + "1 menu       - main menu\n"
            + "2 bookmark   - bookmark internet links\n"
            + "3 timetable  - plan your study schedule\n"
            + "4 academic   - track your academic details\n"
            + "5 flashcard  - flashcards to revise your study materials";

    private static final String academicCommands = "Here are the academic commands available:\n"
            + "add contact c/CONTACT  m/MOBILE  e/EMAIL  - adds a contact\n"
            + "check contact                             - prints the list of contact currently stored\n"
            + "delete contact                            - deletes contact at specified index\n"
            + "add grade n/MODULE NAME  m/MC  g/GRADE    - adds a grade\n"
            + "check grade                               - prints the current CAP based on grade stored\n"
            + "list grade                                - prints the list of grades currently stored\n"
            + "delete grade                              - deletes grade at specified index\n";


    private static final String bookmarkCommands = "Here are the bookmark commands available:\n"
            + "bm <category index>  - changes mode from bookmark main into a category \n"
            + "add <link>           - add bookmark link into a specific category\n"
            + "rm <index number>    - remove a bookmark link into a specific category\n"
            + "list                 - prints the list of categories and respective list of links\n"
            + "back                 - go back to bookmark main\n";

    private static final String timetableCommand =  "Here are the timetable commands available:\n"
            + "add class /MODULE CODE /ONLINE or OFFLINE\n"
            + "/ZOOM LINK or VENUE /DAYS at TIME /NUMBER OF WEEKS                - add a class \n"
            + "add activity /ONLINE or OFFLINE /ZOOM LINK or VENUE /DATE at TIME - add an activity\n"
            + "show schedule                                                     - display schedule";

    private static final String flashcardCommands = "Here are the flashcard commands available:\n"
            + "add     - adds a question and answer to the flashcard deck\n"
            + "list    - shows the flashcards that have been added\n"
            + "test    - user can attempt to answer a random question from the flashcard deck\n"
            + "back    - exit test mode and go back to flashcard main\n";

    public static void printHelpMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println(generalCommands);
        System.lineSeparator();

        Mode currentMode = StudyIt.getCurrentMode();
        if (currentMode == Mode.BOOKMARK) {
            printBookmarkHelp();
        } else if (currentMode == Mode.TIMETABLE) {
            printTimetableHelp();
        } else if (currentMode == Mode.ACADEMIC) {
            printAcademicHelp();
        } else if (currentMode == Mode.FLASHCARD) {
            printFlashcardHelp();
        }

        System.lineSeparator();
        System.out.println(currentModes);
        System.out.println(LINE_DIVIDER);
    }

    public static void printBookmarkHelp() {
        System.out.println(bookmarkCommands);
    }

    public static void printTimetableHelp() {
        System.out.println(timetableCommand);
    }

    public static void printAcademicHelp() {
        System.out.println(academicCommands);
    }

    public static void printFlashcardHelp() {
        System.out.println(flashcardCommands);
    }
}
