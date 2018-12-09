package ui.controller.handlers;

import domain.model.Product;
import domain.model.Role;
import ui.controller.Controller;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductFormHandler extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role[] roles = {Role.ADMIN};
        Controller.checkRole(request, roles);

        Product product = this.getService().getProduct(Integer.parseInt(request.getParameter("id")));

        request.setAttribute("product", product);

        //response.sendRedirect("deleteProduct.jsp");
        request.getRequestDispatcher("deleteProduct.jsp").forward(request, response);

        //return "deleteProduct.jsp";
    }
}
