package Controllers;

import Models.User;
import Services.Interfaces.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/Register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("RegistrationServlet");
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            UserService userService = (UserService) getServletContext().getAttribute("userService");
            User currentUser = userService.registrateNewUSer(login,password);

            if (currentUser!=null) {
                request.getSession().setAttribute("User",currentUser);
                request.getSession().setAttribute("isAdmin",false);
            }
            else {
                String erorMessage = "Аккаунт с таким логином уже существует";
                request.setAttribute("erorMessage",erorMessage);
            }
             request.getRequestDispatcher("./index.jsp").forward(request,response);
    }
}
