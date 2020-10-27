package academic;

import academic.Grade;
import exceptions.InvalidCommandException;
import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;
import studyit.CommandParser;

import java.util.Arrays;
import java.util.List;

public class AcademicCommandParser extends CommandParser {

    public static AcademicCommandType getAcademicCommandType(String command)
            throws InvalidCommandException {
        String commandModified = CommandParser.standardizeCommand(command);

        if (commandModified.startsWith("add contact")) {
            return AcademicCommandType.ADD_CONTACT;
        } else if (commandModified.startsWith("check contact")) {
            return AcademicCommandType.CHECK_CONTACT;
        } else if (commandModified.startsWith("add grade")) {
            return AcademicCommandType.ADD_GRADE;
        } else if (commandModified.startsWith("check grade")) {
            return AcademicCommandType.CHECK_GRADE;
        } else if (commandModified.equalsIgnoreCase("list grade")) {
            return AcademicCommandType.LIST_GRADE;
        } else if (commandModified.startsWith("delete contact")) {
            return AcademicCommandType.DELETE_PERSON;
        } else if (commandModified.startsWith("delete grade")) {
            return AcademicCommandType.DELETE_GRADE;
        } else {
            throw new InvalidCommandException();
        }
    }

    public static String[] getContact(String command) throws NumberFormatException {
        //String commandModified = standardizeCommand(command);
        String name = command.substring(command.indexOf("c/") + 2,
                command.indexOf("m/")).trim();
        String number = command.substring(command.indexOf("m/") + 2,
                command.indexOf("e/")).trim();
        String email = command.substring(command.indexOf("e/") + 2);
        int numberFormatTest = Integer.parseInt(number);
        return new String[]{name, number, email};
    }

    public static String[] getGrade(String command) throws InvalidGradeException, InvalidMcException {
        //String commandModified = standardizeCommand(command);
        String name = command.substring(command.indexOf("n/") + 2,
                command.indexOf("m/")).trim();
        String mc = command.substring(command.indexOf("m/") + 2,
                command.indexOf("g/")).trim();
        String grade = command.substring(command.indexOf("g/") + 2);

        List<String> list = Arrays.asList(Grade.listOfGrades);

        if (!list.contains(grade.toLowerCase())) {
            throw new InvalidGradeException();
        } else if (mc.equals("0")) {
            throw new InvalidMcException();
        }
        return new String[]{name, mc, grade};
    }

    public static String[] parseImportedStatement(String importedStatement) {
        int positionOfFirstDivider = importedStatement.indexOf("|");
        int positionOfSecondDivider = importedStatement.indexOf("|",positionOfFirstDivider + 1);
        int positionOfThirdDivider = importedStatement.lastIndexOf("|");

        String var1 = importedStatement.substring(positionOfFirstDivider + 1,positionOfSecondDivider).trim();
        String var2 = importedStatement.substring(positionOfSecondDivider + 1,positionOfThirdDivider).trim();
        String var3 = importedStatement.substring(positionOfThirdDivider + 1).trim();

        return new String[]{var1, var2, var3};

    }

    public static Integer parseDeletePerson(String command) {
        return Integer.parseInt(command.substring(14).trim());
    }

    public static Integer parseDeleteGrade(String command) {
        return Integer.parseInt(command.substring(12).trim());
    }

}