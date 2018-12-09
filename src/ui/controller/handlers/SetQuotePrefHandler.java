package ui.controller.handlers;

import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetQuotePrefHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie cookie = new Cookie("QuotePref", request.getParameter("choice"));
        response.addCookie(cookie);

        if (request.getParameter("choice").equals("yes")) request.setAttribute("wantsQuote", "yes");
        else request.setAttribute("wantsQuote", "no");

        response.sendRedirect("Controller");
    }
}
