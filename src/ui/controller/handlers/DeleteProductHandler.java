package ui.controller.handlers;

import domain.model.Person;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        this.getService().deleteProduct(Integer.parseInt(request.getParameter("productId")));

        return new OverviewProdsHandler().handleRequest(request, response);
    }
}
