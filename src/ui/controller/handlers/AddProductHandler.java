package ui.controller.handlers;

import domain.model.Person;
import domain.model.Product;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AddProductHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Product product = new Product();
        ArrayList<String> errors = new ArrayList<String>();

        setName(request, product, errors);
        setDescription(request, product, errors);
        setPrice(request, product, errors);

        if (errors.size() < 1) {
            this.getService().addProduct(product);
            RequestHandler handler = new OverviewProdsHandler();
            handler.setService(getService());
            return handler.handleRequest(request, response);
        } else {
            request.setAttribute("errors", errors);
            return "addProduct.jsp";
        }
    }

    private void setPrice(HttpServletRequest request, Product product, ArrayList<String> errors) {
        try {
            product.setPrice(request.getParameter("price"));
            request.setAttribute("pricePrev", request.getParameter("price"));
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setDescription(HttpServletRequest request, Product product, ArrayList<String> errors) {
        try {
            product.setDescription(request.getParameter("description"));
            request.setAttribute("descriptionPrev", request.getParameter("description"));
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setName(HttpServletRequest request, Product product, ArrayList<String> errors) {
        try {
            product.setName(request.getParameter("name"));
            request.setAttribute("namePrev", request.getParameter("name"));
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}
