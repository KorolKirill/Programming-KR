package Controllers;

import Models.User;
import Services.Interfaces.LotService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StopLotServlet", value = "/StopLotServlet")
public class StopLotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("lot_id");
        String currentPrice = request.getParameter("currentPrice");
        String winner = request.getParameter("winner");
        LotService lotService = (LotService) getServletConfig().getServletContext().getAttribute("lotService");
        User user = (User) request.getSession().getAttribute("User");
        try {
            int id = Integer.parseInt(idParam);
            double price = Double.parseDouble(currentPrice);
            lotService.deleteLot(id,price);
            user.setBalance(user.getBalance()+price);
            user.getLots().removeIf(x->x.getId()==id);
            response.sendRedirect("index.jsp");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
