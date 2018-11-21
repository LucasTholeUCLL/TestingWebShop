package ui.controller.handlers;

import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AddPersonHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = new Person();
        ArrayList<String> errors = new ArrayList<String>();

        setUserid(request, person, errors);
        setMail(request, person, errors);
        setPassword(request, person, errors);
        setFirstname(request, person, errors);
        setLastname(request, person, errors);

        if (errors.size() < 1) {
            this.getService().addPerson(person);
            RequestHandler handler = new OverviewHandler();
            handler.setService(getService());
            return handler.handleRequest(request, response);
        } else {
            request.setAttribute("errors", errors);
            return "signUp.jsp";
        }
    }

    private void setUserid(HttpServletRequest request, Person person, ArrayList<String> errors) {
        try {
            person.setUserid(request.getParameter("userid"));
            request.setAttribute("useridPrev", request.getParameter("userid"));
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setMail(HttpServletRequest request, Person person, ArrayList<String> errors) {
        try {
            person.setEmail(request.getParameter("email"));
            request.setAttribute("emailPrev", request.getParameter("email"));
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }


    private void setPassword(HttpServletRequest request, Person person, ArrayList<String> errors) {
        try {
            person.setPasswordHashed(request.getParameter("password"));
            request.setAttribute("passwordPrev", request.getParameter("password"));
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setLastname(HttpServletRequest request, Person person, ArrayList<String> errors) {
        try {
            person.setLastName(request.getParameter("lastName"));
            request.setAttribute("lastNamePrev", request.getParameter("lastName"));
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setFirstname(HttpServletRequest request, Person person, ArrayList<String> errors) {
        try {
            person.setFirstName(request.getParameter("firstName"));
            request.setAttribute("firstNamePrev", request.getParameter("firstName"));
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}
