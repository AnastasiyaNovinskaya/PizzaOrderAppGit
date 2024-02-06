package Servlets;
import Models.PizzaType;
import Repository.Repository;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PlaceOrderServlet", value = "/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Repository repository = new Repository();
        ArrayList<PizzaType> pizzaTypeArrayList = repository.getPizza();
        HttpSession session = request.getSession(true);
        session.setAttribute("pizzas", pizzaTypeArrayList);
        request.setAttribute("pizzas", pizzaTypeArrayList);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/placeOrderPlease.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] selectedPizzas = request.getParameterValues("selectedPizzas");
        Repository repository = new Repository();
        if (selectedPizzas != null) {
            for (String pizzaId : selectedPizzas) {
                String pizzaQuantities = request.getParameter("pizzaQuantity" + pizzaId);
                int quantity = Integer.parseInt(pizzaQuantities);
                PizzaType pizza = repository.getPizzaById(Integer.parseInt(pizzaId));
                repository.addOrder(pizza.getName(), quantity);
            }
        }
        response.sendRedirect(request.getContextPath() + "/welcome.jsp");
    }
}
