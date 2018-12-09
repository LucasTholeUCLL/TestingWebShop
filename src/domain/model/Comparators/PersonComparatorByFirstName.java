package domain.model.Comparators;

import domain.model.Person;

import java.util.Comparator;

public class PersonComparatorByFirstName implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        String p1Name = p1.getFirstName().toUpperCase();
        String p2Name = p2.getFirstName().toUpperCase();
        if (p1Name.equals(p2Name)) {
            return (p1.getLastName().toUpperCase().compareTo(p2.getLastName().toUpperCase()));
        }
        return (p1Name.compareTo(p2Name));
    }
}
