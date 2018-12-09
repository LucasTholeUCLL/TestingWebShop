package ui.controller.handlers;

import ui.controller.Controller;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("loggedIn", Controller.checkLoggedIn(request, getService()));

        if (request.getSession().getAttribute("message") != null) {
            request.setAttribute("message", request.getSession().getAttribute("message"));
            request.getSession().setAttribute("message", null);
        }

        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("QuotePref")) {
                    if (c.getValue().equals("yes")) {
                        if (request.getAttribute("wantsQuote") == null) request.setAttribute("wantsQuote", "yes");
                    } else {
                        if (request.getAttribute("wantsQuote") == null) request.setAttribute("wantsQuote", "no");
                    }
                }
            }
        }
        //response.sendRedirect("index.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);
        //return "index.jsp";
    }
}
