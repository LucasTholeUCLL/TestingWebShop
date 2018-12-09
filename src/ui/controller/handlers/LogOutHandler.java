package ui.controller.handlers;

import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("userId", null);

        request.setAttribute("message", "You are no longer logged in!");

        response.sendRedirect("Controller");

        /*RequestHandler handler = new HomeHandler();
        handler.setService(getService());
        handler.handleRequest(request, response);*/
    }
}
