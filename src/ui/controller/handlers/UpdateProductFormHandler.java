package ui.controller.handlers;

import domain.model.Product;
import domain.model.Role;
import ui.controller.Controller;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateProductFormHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role[] roles = {Role.ADMIN};
        Controller.checkRole(request, roles);

        try {
            Product product = this.getService().getProduct(Integer.parseInt(request.getParameter("id")));

            request.setAttribute("namePrev", product.getName());
            request.setAttribute("pricePrev", product.getPrice());
            request.setAttribute("descriptionPrev", product.getDescription());
            request.setAttribute("productId", request.getParameter("id"));

            request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
            //return "updateProduct.jsp";

        } catch (Exception e) {
            RequestHandler handler = new OverviewProdsHandler();
            handler.setService(getService());
            handler.handleRequest(request, response);
        }
    }
}
