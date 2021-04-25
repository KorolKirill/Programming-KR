package Controllers;

import Models.Product;
import Services.Interfaces.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("product_id");
        try {
            ProductService productService = (ProductService) getServletContext().getAttribute("productService");
            int id = Integer.parseInt(idParam);
            Product product = productService.getProductById(id);
            if (product==null) {
                request.getRequestDispatcher("WEB-INF/jsp/product-not-found.jsp").forward(request,response);
            } else {
                request.setAttribute("Product",product);
                request.getRequestDispatcher("WEB-INF/jsp/product-page.jsp").forward(request,response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
