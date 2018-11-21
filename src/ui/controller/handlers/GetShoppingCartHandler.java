package ui.controller.handlers;

import domain.model.Product;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class GetShoppingCartHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Product> cart;

        if (request.getSession().getAttribute("shoppingCart") == null) cart = new ArrayList<Product>();
        else cart = (ArrayList<Product>) request.getSession().getAttribute("shoppingCart");

        request.setAttribute("productDB", cart);
        return "shoppingCart.jsp";
    }
}
