package ui.controller.handlers;

import ui.controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetQuotePrefHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("QuotePref", request.getParameter("choice"));
        response.addCookie(cookie);

        if (request.getParameter("choice").equals("yes")) request.setAttribute("wantsQuote", "yes");
        else request.setAttribute("wantsQuote", "no");

        RequestHandler handler = new HomeHandler();
        handler.setService(getService());
        return handler.handleRequest(request, response);
    }
}
