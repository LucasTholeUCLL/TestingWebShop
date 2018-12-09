package ui.controller.handlers;

import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckPasswordHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person person = this.getService().getPerson(request.getParameter("userid"));

        if (person.isPasswordCorrect(request.getParameter("password"))) {
            request.setAttribute("result", "This password is correct!");
        } else {
            request.setAttribute("result", "This password is NOT correct!");
        }

        request.setAttribute("person", person);

        request.getRequestDispatcher("checkPassword").forward(request, response);
        //return "checkPassword.jsp";
    }
}
