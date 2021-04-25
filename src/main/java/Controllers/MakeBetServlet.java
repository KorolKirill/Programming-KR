package Controllers;

import Models.Lot;
import Models.User;
import Services.Interfaces.LotService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MakeBetServlet", value = "/MakeBetServlet")
public class MakeBetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("lot_id");
        String betParam = request.getParameter("bet_amount");
        User currentUser = (User) request.getSession().getAttribute("User");
        LotService lotService = (LotService) getServletContext().getAttribute("lotService");

        try {
            int id = Integer.parseInt(idParam);
            double bet = Double.parseDouble(betParam);
            Lot currentLot = lotService.getById(id);
            if (currentUser.getBalance()<bet) {
                String warning = "You don`t have enough money";
                request.setAttribute("error_message",warning);
                request.getRequestDispatcher("eror-page.jsp").forward(request,response);
            }
            else if (bet<currentLot.getCurrentPrice()+currentLot.getBetStep()) {
                String warning = "Your bet is smaller than (highest bet + bet step)";
                request.setAttribute("error_message",warning);
                request.getRequestDispatcher("eror-page.jsp").forward(request,response);
            }
           else {
               lotService.makeBet(id,bet,currentUser.getLogin());
               currentUser.setBalance(currentUser.getBalance()-bet);
               response.sendRedirect("LotServlet?lot_id="+id);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }
}
