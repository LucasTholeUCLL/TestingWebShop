package ui.controller.handlers;

import domain.model.Person;
import domain.model.Product;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePersonFormHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = this.getService().getPerson(request.getParameter("id"));

        request.setAttribute("person", person);

        return "deletePerson.jsp";
    }
}
