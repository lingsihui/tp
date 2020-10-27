package academic;

import java.util.ArrayList;

/**
 * Represents a grade book in study-it.
 */
public class PersonBook {
    public static void addPerson(String[] args, ArrayList<Person> listOfPerson) {
        listOfPerson.add(new Person(args[0],args[1],args[2]));
    }

    public static void deletePerson(Integer indexToBeDeleted, ArrayList<Person> listOfPerson) {
        listOfPerson.remove(indexToBeDeleted - 0);
        //TODO: Fix remove() not recognising index
    }

    public static String printPersonBook(ArrayList<Person> listOfPerson) {
        int listIndex = 0;
        StringBuilder listToPrint = new StringBuilder();
        for (Person person : listOfPerson) {
            if (person != null) {
                listToPrint.append(listIndex + 1);
                listToPrint.append(". [" + person.nameOfPerson + "]");
                listToPrint.append(" [" + person.contactNumberOfPerson + "]");
                listToPrint.append(" [" + person.emailOfPerson + "]");
                listIndex++;
                if (listOfPerson.size() != listIndex) {
                    listToPrint.append("\n");
                }

            }
        }

        return listToPrint.toString();
    }
}