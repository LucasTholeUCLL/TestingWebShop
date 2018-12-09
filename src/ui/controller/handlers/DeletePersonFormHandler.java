package ui.controller.handlers;

import domain.model.Person;
import domain.model.Role;
import ui.controller.Controller;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePersonFormHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role[] roles = {Role.ADMIN};
        Controller.checkRole(request, roles);

        Person person = this.getService().getPerson(request.getParameter("id"));

        request.setAttribute("person", person);

        request.getRequestDispatcher("deletePerson.jsp").forward(request, response);
        //response.sendRedirect("deletePerson.jsp");
        //return "deletePerson.jsp";
    }
}
