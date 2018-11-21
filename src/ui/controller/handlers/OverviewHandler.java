package ui.controller.handlers;

import domain.model.Comparators.PersonComparatorByEmail;
import domain.model.Comparators.PersonComparatorByFirstName;
import domain.model.Comparators.PersonComparatorByLastName;
import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;

public class OverviewHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Comparator<Person> comparator = new PersonComparatorByFirstName();
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("personSort") && (request.getAttribute("chosenSort") == null)) {

                    switch(c.getValue()) {
                        case "email":
                            request.setAttribute("chosenSort", "email");
                            break;
                        case "lname":
                            request.setAttribute("chosenSort", "lname");
                            break;
                        default:
                            request.setAttribute("chosenSort", "fname");
                    }
                }
            }
        }
        if (request.getAttribute("chosenSort") != null) {
            switch (request.getAttribute("chosenSort").toString()) {
                case "lname":
                    comparator = new PersonComparatorByLastName();
                    break;
                case "email":
                    comparator = new PersonComparatorByEmail();
                    break;
                default:
            }
        }
        //System.out.println(request.getAttribute("chosenSort") + " - overViewHandler");
        //System.out.println(comparator.toString() + " - overViewHandler");


        List<Person> persons = getService().getPersons();
        persons.sort(comparator);
        request.setAttribute("personDB", persons);
        return "personoverview.jsp";
    }
}
