package ui.controller.handlers;

import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OverviewProdsHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getSession().getAttribute("message") != null) {
            request.setAttribute("message", request.getSession().getAttribute("message"));
            request.getSession().setAttribute("message", null);
        }

        request.setAttribute("productDB", this.getService().getProducts());
        request.getRequestDispatcher("productoverview.jsp").forward(request, response);
        //return "productoverview.jsp";
    }
}
