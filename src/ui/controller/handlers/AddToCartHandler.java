package ui.controller.handlers;

import domain.model.Product;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddToCartHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
        if (request.getParameter("amount") != null && !request.getParameter("amount").trim().isEmpty()
                && Integer.parseInt(request.getParameter("amount")) > 0 && Integer.parseInt(request.getParameter("amount")) < 10001) {
            HttpSession session = request.getSession();

            ArrayList<ArrayList<Object>> products;

            if (session.getAttribute("shoppingCart") != null)
                products = (ArrayList<ArrayList<Object>>) session.getAttribute("shoppingCart");
            else products = new ArrayList<ArrayList<Object>>();

            boolean inCart = false;

            for (Product p : getService().getProducts()) {
                ArrayList<Object> product = new ArrayList<Object>();
                if (p.getProductId() == Integer.parseInt(request.getParameter("id"))) {
                    for (List<Object> pc : products) {
                        if (((Product)pc.get(0)).getProductId() == p.getProductId()) {
                            inCart = true;
                            int Amt = Integer.parseInt(pc.get(1).toString());
                            Amt+=Integer.parseInt(request.getParameter("amount"));
                            request.getSession().setAttribute("message", "Added " + request.getParameter("amount") + "x " + p.getName() + " to your shopping cart!");
                            pc.set(1, Amt);
                        }
                    }
                    if (!inCart) {
                        product.add(p);
                        product.add(request.getParameter("amount"));
                        request.getSession().setAttribute("message", "Added " + request.getParameter("amount") + "x " + p.getName() + " to your shopping cart!");
                        products.add(product);
                    }
                }
            }

            session.setAttribute("shoppingCart", products);
            response.sendRedirect("Controller?action=OverviewProds");

        } else {
            request.setAttribute("error", "Amount must be a number between 1 and 10000!");
            RequestHandler handler = new OverviewProdsHandler();
            handler.setService(getService());
            handler.handleRequest(request, response);
        }} catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Amount must be a number between 1 and 10000!");
            RequestHandler handler = new OverviewProdsHandler();
            handler.setService(getService());
            handler.handleRequest(request, response);
        }
    }
}
