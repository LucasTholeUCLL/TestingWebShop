package ui.controller.handlers;

import domain.model.Product;
import domain.model.Role;
import ui.controller.Controller;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class OrderProductsHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<ArrayList<Object>> cart;

        Role[] roles = {Role.ADMIN, Role.CUSTOMER};
        Controller.checkRole(request, roles);

        if (request.getSession().getAttribute("shoppingCart") == null) {
            RequestHandler handler = new OverviewProdsHandler();
            handler.setService(getService());
            handler.handleRequest(request, response);
        }

        cart = (ArrayList<ArrayList<Object>>) request.getSession().getAttribute("shoppingCart");

        double total = 0;

        if (cart.size() > 0) {
            for (ArrayList<Object> item : cart) {
                total+=((Product)item.get(0)).getPrice() * Double.parseDouble(item.get(1)+"");
            }
        }

        request.setAttribute("productDB", cart);
        request.setAttribute("total", total);

        request.getRequestDispatcher("orderProducts.jsp").forward(request, response);

        //response.sendRedirect("orderProducts.jsp");
        //return "orderProducts.jsp";
    }
}
