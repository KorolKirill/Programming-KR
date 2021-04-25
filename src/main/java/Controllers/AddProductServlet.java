package Controllers;

import Models.User;
import Services.Interfaces.ProductService;
import Services.Interfaces.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddProductServlet", value = "/AddProductServlet")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String productName = request.getParameter("name");
            String productInfo = request.getParameter("information");
            User user = (User) request.getSession().getAttribute("User");
            System.out.println(user);
            ProductService productService = (ProductService) getServletContext().getAttribute("productService");
            int id =  productService.addProduct(productName,productInfo,user);
            user.getProducts().add(productService.getProductById(id));
            user.getProducts().stream().forEach(System.out::println);
            response.sendRedirect("Account");
        } catch (Exception e) {
            response.sendError(400);
            e.printStackTrace();
        }

    }
}
