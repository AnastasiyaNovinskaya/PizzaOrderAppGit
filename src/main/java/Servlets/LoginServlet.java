package Servlets;

import Repository.Repository;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Repository repository = new Repository();

        if (repository.validateUser(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            response.sendRedirect("welcome.jsp");
        } else {
            response.sendRedirect("login-failed.jsp");
        }
    }
}

// I suggest to make connection field private, to keep internal workings of a class encapsulated.
// We could extract the Repository into a field of the LoginServlet class to have Repository as a class field rather than a local variable.