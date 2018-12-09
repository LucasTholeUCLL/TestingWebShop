package ui.controller;


import domain.model.Person;
import domain.model.Role;
import domain.model.ShopService;


import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
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
        request.setAttribute("loggedIn", this.checkLoggedIn(request, shopService));
        request.setAttribute("cartSize", getCartSize(request));
        if (action != null) {
            try {
                RequestHandler handler = null;
                handler = factory.getController(action, shopService);
                handler.handleRequest(request, response);
            } catch (NotAuthorizedException e) {
                request.setAttribute("error", "You have insufficient rights to look at the requested page!");
                factory.getController("Home", shopService).handleRequest(request, response);
            }
        } else {
            factory.getController("Home", shopService).handleRequest(request, response);
        }
        //System.out.println(request.getAttribute("loggedIn"));
        //request.getRequestDispatcher(destination).forward(request, response);
        //response.sendRedirect(destination);
    }

    public static Person checkLoggedIn(HttpServletRequest request, ShopService shopService) {
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") != null) {
            for (Person p : shopService.getPersons()) {
                if (p.getUserid().equals(session.getAttribute("userId"))) return p;
            }
            return null;
        }
        else return null;
    }

    public static void checkRole(HttpServletRequest request, Role[] roles) {
        boolean found = false;
        Person person = (Person) request.getAttribute("loggedIn");
        if (person != null) {
            for (Role role : roles) {
                if (person.getRole().equals(role)) found = true;
            }
        }
        if (!found) {
            throw new NotAuthorizedException();
        }

    }

    private int getCartSize(HttpServletRequest request) {
        if (request.getSession().getAttribute("shoppingCart") == null) return 0;
        else {
            int amount = ((List<Object>) request.getSession().getAttribute("shoppingCart")).size();

            return amount;
        }
    }
}
