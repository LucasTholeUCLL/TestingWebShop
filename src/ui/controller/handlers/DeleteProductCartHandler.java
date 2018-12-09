package ui.controller.handlers;

import domain.model.Product;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteProductCartHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        ArrayList<ArrayList<Object>> products;

        if (session.getAttribute("shoppingCart") != null) products = (ArrayList<ArrayList<Object>>) session.getAttribute("shoppingCart");
        else products = new ArrayList<ArrayList<Object>>();

        ArrayList<ArrayList<Object>> newCart = new ArrayList<>();

        Product removed = null;
        int amount = 0;

        for (ArrayList<Object> product : products) {
            Product productt = (Product)product.get(0);
            if (productt.getProductId() != Integer.parseInt(request.getParameter("id"))) {
                newCart.add(product);
            } else {
                amount=Integer.parseInt(product.get(1).toString());
                removed = productt;
            }
        }

        if (removed != null) request.getSession().setAttribute("message", "Removed " + amount + "x " + removed.getName() + " from your shopping cart!");
        //else request.setAttribute("error", "There was an error removing this item from your shopping cart. If this error does not resolve itself try reopening this website in a new window");


        session.setAttribute("shoppingCart", newCart);

        response.sendRedirect("Controller?action=GetShoppingCart");
    }
}
