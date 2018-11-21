package ui.controller.handlers;

import domain.model.Person;
import domain.model.Product;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductFormHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Product product = this.getService().getProduct(Integer.parseInt(request.getParameter("id")));

        request.setAttribute("product", product);

        return "deleteProduct.jsp";
    }
}
