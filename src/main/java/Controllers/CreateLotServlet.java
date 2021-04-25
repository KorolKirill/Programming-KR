package Controllers;

import Models.Product;
import Models.User;
import Services.Interfaces.LotService;
import Services.Interfaces.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateLotServlet", value = "/CreateLotServlet")
public class CreateLotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("product_id");
        String initialPrice = request.getParameter("initialPrice");
        String betStep = request.getParameter("betStep");
        ProductService productService = (ProductService) getServletContext().getAttribute("productService");
        LotService lotService = (LotService) getServletContext().getAttribute("lotService");

        try {
            int id = Integer.parseInt(productId);
            double initialPriceD = Double.parseDouble(initialPrice);
            double betStepD = Double.parseDouble(betStep);
            Product product = productService.getProductById(id);
            Integer lotId =  lotService.createLot(product,initialPriceD,betStepD);
            request.setAttribute("lot_id",lotId);

            User user = (User) request.getSession().getAttribute("User");

            user.getProducts().removeIf(x->x.getId()==id);
            user.getLots().add(lotService.getById(lotId));

            response.sendRedirect("LotServlet?lot_id="+id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }
}
