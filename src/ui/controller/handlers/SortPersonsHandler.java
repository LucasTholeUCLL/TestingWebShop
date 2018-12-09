package ui.controller.handlers;

import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SortPersonsHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie cookie = new Cookie("personSort", request.getParameter("choice"));
        response.addCookie(cookie);

        switch(request.getParameter("choice")) {
            case "lname":
                request.getSession().setAttribute("chosenSort", "lname");
                break;
            case "email":
                request.getSession().setAttribute("chosenSort", "email");
                break;
            default:
                request.getSession().setAttribute("chosenSort", "fname");
        }

        //System.out.println(request.getAttribute("chosenSort") + " - sortPersonsHandler");

        RequestHandler handler = new OverviewHandler();
        handler.setService(getService());
        handler.handleRequest(request, response);
    }
}
