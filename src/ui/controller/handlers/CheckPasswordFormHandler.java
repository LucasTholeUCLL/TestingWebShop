package ui.controller.handlers;

import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckPasswordFormHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person person = this.getService().getPerson(request.getParameter("id"));
        request.setAttribute("person", person);

        request.getRequestDispatcher("checkPassword.jsp").forward(request, response);
        //return "checkPassword.jsp";
    }
}
