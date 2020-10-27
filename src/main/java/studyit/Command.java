package studyit;

import academic.Grade;
import academic.GradeBook;
import academic.Person;
import academic.PersonBook;
import academic.AcademicCommandType;

import academic.AcademicStorage;
import academic.AcademicCommandParser;
import bookmark.BookmarkParser;
import bookmark.BookmarkUi;

import exceptions.InvalidCommandException;
import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;
import flashcard.FlashcardRun;
import timetable.TimeTableRun;
import userinterface.ErrorMessage;
import userinterface.HelpMessage;
import userinterface.Ui;

import java.io.IOException;

import java.util.ArrayList;
import bookmark.BookmarkRun;


public class Command {

    public static void executeCommand(String command, CommandType commandType,
                                      BookmarkRun bookmarkRun, FlashcardRun flashcardRun,
                                      TimeTableRun timeTableRun, ArrayList<academic.Grade> currentGrades,
                                      ArrayList<academic.Person> listOfPerson) {
        if (commandType == CommandType.EXIT_PROGRAM) {
            Ui.printExit();
        } else if (commandType == CommandType.EXIT_MODE) {
            Ui.exitMode();
        } else if (commandType == CommandType.LOCATION) {
            Ui.printLocation();
        } else if (commandType == CommandType.CHANGE_MODE) {
            Ui.changeModeCommand(command);
        } else if (commandType == CommandType.HELP) {
            HelpMessage.printHelpMessage();
        } else if (StudyIt.getCurrentMode() != Mode.MENU) {
            // Run the mode specific commands if the input is none of the general command
            handleNonGeneralCommand(command, commandType, bookmarkRun, flashcardRun, timeTableRun,
                    currentGrades, listOfPerson);
        } else {
            assert commandType == CommandType.UNIDENTIFIABLE : "This command should be unidentifiable";
            ErrorMessage.printUnidentifiableCommand();
            StudyItLog.logger.info("Cannot understand command input.");
        }
    }

    public static void handleNonGeneralCommand(String command, CommandType commandType,
                                               BookmarkRun bookmarkRun,
                                               FlashcardRun flashcardRun, TimeTableRun timeTableRun,
                                               ArrayList<academic.Grade> currentGrades,
                                               ArrayList<academic.Person> listOfPerson) {
        Mode currentMode = StudyIt.getCurrentMode();
        if (currentMode == Mode.BOOKMARK) {
            executeBookmarkModeCommand(command, bookmarkRun);
        } else if (currentMode == Mode.TIMETABLE) {
            executeTimetableModeCommand(command, timeTableRun);
        } else if (currentMode == Mode.ACADEMIC) {
            executeAcademicModeCommand(command, currentGrades, listOfPerson);
        } else if (currentMode == Mode.FLASHCARD) {
            executeFlashcardCommand(command, flashcardRun);
        } else {
            assert currentMode == Mode.MENU : "The current mode should be at menu";
            StudyItLog.logger.severe("Mode is not handled properly.");
        }
    }

    public static void executeBookmarkModeCommand(String command, BookmarkRun bookmarkRun) {
        StudyItLog.logger.info("Processing bookmark mode.");
        bookmarkRun.run(command);
    }

    public static void executeTimetableModeCommand(String command, TimeTableRun timeTableRun) {
        StudyItLog.logger.info("Processing timetable mode.");
        timeTableRun.run(command);
    }

    public static void executeAcademicModeCommand(String command, ArrayList<academic.Grade> currentGrades,
                                                  ArrayList<academic.Person> listOfPerson) {
        StudyItLog.logger.info("Processing academic mode.");


        try {
            AcademicCommandType commandType = AcademicCommandParser.getAcademicCommandType(command);

            if (commandType == AcademicCommandType.ADD_CONTACT) {
                Ui.printLine("Adding Contact");
                PersonBook.addPerson(AcademicCommandParser.getContact(command), listOfPerson);

            } else if (commandType == AcademicCommandType.CHECK_CONTACT) {
                Ui.printLine(PersonBook.printPersonBook(listOfPerson));

            } else if (commandType == AcademicCommandType.ADD_GRADE) {
                Ui.printLine("Adding Grade");
                GradeBook.addGrade(AcademicCommandParser.getGrade(command), currentGrades);

            } else if (commandType == AcademicCommandType.CHECK_GRADE) {
                Ui.printLine(GradeBook.printCap(currentGrades));

            } else if (commandType == AcademicCommandType.LIST_GRADE) {
                Ui.printLine(GradeBook.printListOfGrades(currentGrades));

            } else if (commandType == AcademicCommandType.DELETE_PERSON) {
                Ui.printLine("Deleting contact");
                PersonBook.deletePerson(AcademicCommandParser.parseDeletePerson(command),listOfPerson);

            } else if (commandType == AcademicCommandType.DELETE_GRADE) {
                Ui.printLine("Deleting grade");
                GradeBook.deleteGrade(AcademicCommandParser.parseDeleteGrade(command),currentGrades);

            } else {
                StudyItLog.logger.severe("Invalid command type, check studyit.Command Parser");
            }
        } catch (InvalidCommandException e) {
            ErrorMessage.printUnidentifiableCommand();
            StudyItLog.logger.info("Invalid academic command.");
        } catch (StringIndexOutOfBoundsException e) {
            ErrorMessage.printUnidentifiableInput();
            StudyItLog.logger.info("Invalid academic command. Sting Index out of bounds.");
        } catch (NumberFormatException e) {
            ErrorMessage.printInvalidNumber();
            StudyItLog.logger.info("Invalid Number.");
        } catch (InvalidGradeException e) {
            ErrorMessage.printInvalidGrade();
            StudyItLog.logger.info("Invalid Grade.");
        } catch (InvalidMcException e) {
            ErrorMessage.printInvalidMc();
            StudyItLog.logger.info("Invalid MC.");
        }

        try {
            AcademicStorage.writeFile(listOfPerson, currentGrades);
        } catch (IOException e) {
            System.out.println("placeholder error message");
        }

    }

    public static void executeFlashcardCommand(String command, FlashcardRun flashcardRun) {
        StudyItLog.logger.info("Processing flashcard mode.");
        flashcardRun.run(command);
    }
}