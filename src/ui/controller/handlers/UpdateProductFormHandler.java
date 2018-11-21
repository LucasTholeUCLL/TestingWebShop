package ui.controller.handlers;

import domain.model.Product;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class UpdateProductFormHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            Product product = this.getService().getProduct(Integer.parseInt(request.getParameter("id")));

            request.setAttribute("namePrev", product.getName());
            request.setAttribute("pricePrev", product.getPrice());
            request.setAttribute("descriptionPrev", product.getDescription());
            request.setAttribute("productId", request.getParameter("id"));

            return "updateProduct.jsp";

        } catch (Exception e) {
            RequestHandler handler = new OverviewProdsHandler();
            handler.setService(getService());
            return handler.handleRequest(request, response);
        }
    }
}
