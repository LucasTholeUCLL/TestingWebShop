package ui.controller.handlers;

import ui.controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortPersonsHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("personSort", request.getParameter("choice"));
        response.addCookie(cookie);

        switch(request.getParameter("choice")) {
            case "lname":
                request.setAttribute("chosenSort", "lname");
                break;
            case "email":
                request.setAttribute("chosenSort", "email");
                break;
            default:
                request.setAttribute("chosenSort", "fname");
        }

        //System.out.println(request.getAttribute("chosenSort") + " - sortPersonsHandler");

        RequestHandler handler = new OverviewHandler();
        handler.setService(getService());
        return handler.handleRequest(request, response);
    }
}
