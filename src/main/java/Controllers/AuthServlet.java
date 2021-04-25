package Controllers;

import Models.User;
import Services.Interfaces.LotService;
import Services.Interfaces.ProductService;
import Services.Interfaces.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/Auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            UserService userService = (UserService) getServletContext().getAttribute("userService");
            ProductService productService = (ProductService) getServletContext().getAttribute("productService");
            LotService lotService = (LotService) getServletContext().getAttribute("lotService");
            User currentUser = userService.auth(login,password);

            if (currentUser!=null) {
                currentUser.setLots(lotService.getAllUserLots(currentUser.getLogin()));
                currentUser.setProducts(productService.getAllUSerProduct(currentUser.getLogin()));
                 request.getSession().setAttribute("User",currentUser);

            }
            else {
                String erorMessage = "Вы ввели не правильный пароль или логин";
                request.setAttribute("erorMessage",erorMessage);
            }
            request.getSession().setMaxInactiveInterval(7200);
            request.getRequestDispatcher("./index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
