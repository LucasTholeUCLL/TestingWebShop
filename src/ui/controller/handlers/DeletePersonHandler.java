package ui.controller.handlers;

import domain.model.Role;
import ui.controller.Controller;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePersonHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role[] roles = {Role.ADMIN};
        Controller.checkRole(request, roles);

        this.getService().deletePerson(request.getParameter("userid"));

        response.sendRedirect("Controller?action=Overview");
        //new OverviewHandler().handleRequest(request, response);
    }
}
