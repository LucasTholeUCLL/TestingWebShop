package domain.model.Comparators;

import domain.model.Person;

import java.util.Comparator;

public class PersonComparatorByLastName implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        if (p1.getLastName().toUpperCase().equals(p2.getLastName().toUpperCase())) {
            return (p1.getFirstName().toUpperCase().compareTo(p2.getFirstName().toUpperCase()));
        }
        return (p1.getLastName().toUpperCase().compareTo(p2.getLastName().toUpperCase()));
    }
}
