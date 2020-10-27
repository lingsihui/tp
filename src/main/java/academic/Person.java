package academic;

/**
 * Represents a person in study-it.
 */
public class Person {
    protected String nameOfPerson;
    protected String contactNumberOfPerson;
    protected String emailOfPerson;

    public Person(String name, String number, String email) {
        this.nameOfPerson = name;
        this.contactNumberOfPerson = number;
        this.emailOfPerson = email;
    }

    public static String printIndividualPerson(Person person) {
        return "[P] | " + person.nameOfPerson + " | " + person.contactNumberOfPerson + " | " + person.emailOfPerson;
    }


}
