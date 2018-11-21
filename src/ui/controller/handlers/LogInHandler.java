package ui.controller.handlers;

import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class LogInHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userid");
        String password = request.getParameter("password");
        ArrayList<String> errors = new ArrayList<>();

        if (!userId.trim().isEmpty() && !password.trim().isEmpty()) {
            for (Person p : getService().getPersons()) {
                if (p.getUserid().equals(userId)) {
                    if (p.isPasswordCorrect(password)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userId", userId);

                        RequestHandler handler = new HomeHandler();
                        handler.setService(getService());
                        return handler.handleRequest(request, response);
                    }
                }
            }
        }
        if (userId.trim().isEmpty()) errors.add("Enter a userid!");
        if (password.trim().isEmpty()) errors.add("Enter a password!");
        if (!userId.trim().isEmpty() && !password.trim().isEmpty()) errors.add("Userid or Password not correct!");

        request.setAttribute("errors", errors);

        RequestHandler handler = new LogInFormHandler();
        handler.setService(getService());
        return handler.handleRequest(request, response);
    }
}
