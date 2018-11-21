package domain.model.Comparators;

import domain.model.Person;

import java.util.Comparator;

public class PersonComparatorByLastName implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        if (p1.getLastName().equals(p2.getLastName())) {
            return (p1.getFirstName().compareTo(p2.getFirstName()));
        }
        return (p1.getLastName().compareTo(p2.getLastName()));
    }
}
