package Controllers;

import Models.User;
import Services.Interfaces.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddBalanceServlet", value = "/AddBalanceServlet")
public class AddBalanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String income = request.getParameter("income");
//        String toWhom = request.getParameter("toWhom");
        User curentUser = (User) request.getSession().getAttribute("User");
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        if (income!=null){
            try {
                double incomeD = Double.parseDouble(income);
                userService.addBalance(incomeD,curentUser);
            } catch (NumberFormatException e) {
                response.sendError(400); // Здесь если не получилось перевести  в дабл, то ошибка
                e.printStackTrace();
            }
        }
        response.sendRedirect("Account");
    }
}
