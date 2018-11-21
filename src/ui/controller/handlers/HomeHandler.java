package ui.controller.handlers;

import ui.controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
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
        return "index.jsp";
    }
}
