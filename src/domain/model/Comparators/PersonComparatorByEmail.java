package domain.model.Comparators;

import domain.model.Person;

import java.util.Comparator;

public class PersonComparatorByEmail implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        return (p1.getEmail().replace("@", "").replace(".","").toUpperCase().compareTo(p2.getEmail().replace("@", "").replace(".","").toUpperCase()));
    }
}
