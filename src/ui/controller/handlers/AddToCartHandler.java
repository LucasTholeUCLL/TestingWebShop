package ui.controller.handlers;

import domain.model.Product;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class AddToCartHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        ArrayList<Product> products;

        if (session.getAttribute("shoppingCart") != null) products = (ArrayList<Product>) session.getAttribute("shoppingCart");
        else products = new ArrayList<>();

        for (Product p : getService().getProducts()) {
            if (p.getProductId() == Integer.parseInt(request.getParameter("id"))) products.add(p);
        }

        session.setAttribute("shoppingCart", products);

        RequestHandler handler = new OverviewProdsHandler();
        handler.setService(getService());
        return handler.handleRequest(request, response);
    }
}
