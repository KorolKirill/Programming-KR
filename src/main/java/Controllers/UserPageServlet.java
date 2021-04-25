package Controllers;

import Models.User;
import Services.Interfaces.LotService;
import Services.Interfaces.ProductService;
import Services.Interfaces.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserPageServlet", value = "/Account")
public class UserPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("User");
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        ProductService productService = (ProductService) getServletContext().getAttribute("productService");
        LotService lotService = (LotService) getServletContext().getAttribute("lotService");

        if (user == null) {
            request.getRequestDispatcher("not-authorized.jsp").forward(request,response);
        }
        else {
            user.setLots(lotService.getAllUserLots(user.getLogin()));
            user.setProducts(productService.getAllUSerProduct(user.getLogin()));
            request.getRequestDispatcher("user-page.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
