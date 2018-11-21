package ui.controller.handlers;

import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPasswordHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = this.getService().getPerson(request.getParameter("userid"));

        if (person.isPasswordCorrect(request.getParameter("password"))) {
            request.setAttribute("result", "This password is correct!");
        } else {
            request.setAttribute("result", "This password is NOT correct!");
        }

        request.setAttribute("person", person);

        return "checkPassword.jsp";
    }
}
