package ui.controller.handlers;

import domain.model.Product;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class GetShoppingCartHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<ArrayList<Object>> cart;

        if (request.getSession().getAttribute("message") != null) {
            request.setAttribute("message", request.getSession().getAttribute("message"));
            request.getSession().setAttribute("message", null);
        }

        if (request.getSession().getAttribute("shoppingCart") == null) cart = new ArrayList<>();
        else cart = (ArrayList<ArrayList<Object>>) request.getSession().getAttribute("shoppingCart");

        double total = 0;

        if (cart.size() > 0) {
            for (ArrayList<Object> item : cart) {
                total += ((Product) item.get(0)).getPrice() * Integer.parseInt(item.get(1).toString());
            }
        }

        request.setAttribute("productDB", cart);
        request.setAttribute("total", total);

        //System.out.println(cart);

        request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
        //return "shoppingCart.jsp";
    }
}
