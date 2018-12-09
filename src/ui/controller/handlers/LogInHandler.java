package ui.controller.handlers;

import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class LogInHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userId = request.getParameter("userid");
        String password = request.getParameter("password");
        ArrayList<String> errors = new ArrayList<>();

        boolean loggedIn = false;

        if (!userId.trim().isEmpty() && !password.trim().isEmpty()) {
            for (Person p : getService().getPersons()) {
                if (p.getUserid().equals(userId)) {
                    if (p.isPasswordCorrect(password)) {
                        loggedIn = true;
                        HttpSession session = request.getSession();
                        session.setAttribute("userId", userId);



                        response.sendRedirect("Controller");
                        request.getSession().setAttribute("message", "You are now logged in as '" + p.getFirstName() + " " + p.getLastName() + "'!");
                    }
                }
            }
        }
        if (!loggedIn) {
            if (userId.trim().isEmpty()) errors.add("Enter a userid!");
            if (password.trim().isEmpty()) errors.add("Enter a password!");
            if (!userId.trim().isEmpty() && !password.trim().isEmpty()) errors.add("Userid or Password not correct!");

            request.setAttribute("errors", errors);

            RequestHandler handler = new LogInFormHandler();
            handler.setService(getService());
            handler.handleRequest(request, response);
        }
    }
}
