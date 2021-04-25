package Controllers;

import DAO.LotDao;
import Models.Lot;
import Services.Interfaces.LotService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Stack;

@WebServlet(name = "LotServlet", value = "/LotServlet")
public class LotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id  = (Integer) request.getAttribute("lot_id");
        String idParam = request.getParameter("lot_id");
        try {
         if (id == null) {
             id = Integer.parseInt(idParam);
         }
            LotService lotService = (LotService) getServletContext().getAttribute("lotService");
            Lot lot = lotService.getById(id);
            Stack<LotDao.BetHistory> betHistory = (Stack<LotDao.BetHistory>) lotService.getBetHistory(id);
            if (lot==null) {
                response.sendRedirect("WEB-INF/jsp/lot-not-found.jsp");
            } else {
                request.setAttribute("Lot",lot);
                request.setAttribute("history",betHistory);
                request.getRequestDispatcher("WEB-INF/jsp/lot-page.jsp").forward(request,response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.getRequestDispatcher("WEB-INF/jsp/lot-page.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
