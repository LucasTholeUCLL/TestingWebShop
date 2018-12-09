package ui.controller.handlers;

import domain.model.Product;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ConfirmOrderHandler extends RequestHandler {
        @Override
        public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            ArrayList<ArrayList<Object>> cart = (ArrayList<ArrayList<Object>>) request.getSession().getAttribute("shoppingCart");

            double total = 0;

            if (cart == null) {
                response.sendRedirect("Controller");
            } else {

                if (cart.size() > 0) {
                    for (ArrayList<Object> item : cart) {
                        total += ((Product) item.get(0)).getPrice() * Double.parseDouble(item.get(1) + "");
                    }
                }

                request.setAttribute("productDB", cart);
                request.setAttribute("total", total);

                request.setAttribute("adr", request.getParameter("adr"));
                request.setAttribute("zip", request.getParameter("zip"));
                request.setAttribute("state", request.getParameter("state"));
                request.setAttribute("city", request.getParameter("city"));

                request.getSession().setAttribute("shoppingCart", null);

                request.getRequestDispatcher("orderComplete.jsp").forward(request, response);
                //response.sendRedirect("orderComplete.jsp");
                //return "orderComplete.jsp";
            }
        }
}
