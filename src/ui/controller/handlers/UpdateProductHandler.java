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

public class UpdateProductHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role[] roles = {Role.ADMIN};
        Controller.checkRole(request, roles);

        Product product = new Product();
        ArrayList<String> errors = new ArrayList<String>();

        setName(request, product, errors);
        setDescription(request, product, errors);
        setPrice(request, product, errors);
        product.setProductId(Integer.parseInt(request.getParameter("productId")));

        if (errors.size() < 1) {
            this.getService().updateProducts(product);
            response.sendRedirect("Controller?action=OverviewProds");
        } else {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
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
