package ui.controller;


import domain.model.Person;
import domain.model.Product;
import domain.model.ShopService;
import sun.misc.Request;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShopService shopService;
    private HandlerFactory factory = new HandlerFactory();

    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext context = this.getServletContext();
        Properties properties = new Properties();
        Enumeration<String> parameterNames = context.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String propertyName = parameterNames.nextElement();
            properties.setProperty(propertyName, context.getInitParameter(propertyName));
        }

        shopService = new ShopService(properties);
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String destination = "index.jsp";
        if (action != null) {
            RequestHandler handler = null;
            handler = factory.getController(action, shopService);
            destination = handler.handleRequest(request, response);
        } else {
            destination = factory.getController("Home", shopService).handleRequest(request, response);
        }
        request.setAttribute("loggedIn", this.checkLoggedIn(request));
        RequestDispatcher view=request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    private String checkLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") != null) {
            for (Person p : shopService.getPersons()) {
                if (p.getUserid().equals(session.getAttribute("userId"))) return p.getFirstName();
            }
            return "-1";
        }
        else return "-1";
    }
}
