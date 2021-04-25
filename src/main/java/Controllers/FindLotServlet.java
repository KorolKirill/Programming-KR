package Controllers;

import Models.Lot;
import Services.Interfaces.LotService;
import Services.Interfaces.ProductService;
import Services.Interfaces.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

@WebServlet(name = "FindLotServlet", value = "/FindLotServlet")
public class FindLotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("searchCategory");
        String param = request.getParameter("param");
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        ProductService productService = (ProductService) getServletContext().getAttribute("productService");
        LotService lotService = (LotService) getServletContext().getAttribute("lotService");
        try {
            ArrayList<Lot> lots =  lotService.getAllLots();

            System.out.println(param);
            if (category!=null && param!=null && !"".equals(param)) {
                Predicate<Lot> filter = getFilter(category,param);
                ArrayList<Lot> founded = new ArrayList<>();
                lots.
                        stream().
                        filter(filter).
                        forEach(founded::add);
                System.out.println(founded.toString());
                request.setAttribute("founded",founded);
            }
            request.getRequestDispatcher("WEB-INF/jsp/find-page.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private Predicate<Lot> getFilter(String category,String param) {
        Predicate<Lot> filter = null;
        switch (category) {
            case "byOwner":
                filter = x -> x.getOwner().equals(param);
                break;
            case "byId":
                filter = x -> x.getId() == Integer.parseInt(param);
                break;
            case "byLowerPrice":
                filter = x -> x.getCurrentPrice() <= Integer.parseInt(param);
                break;
            case "byGreaterPrice":
                filter = x -> x.getCurrentPrice() >= Integer.parseInt(param);
                break;
            case "byWinner":
                filter = x -> x.getWinner().equals(param);
                break;
            case "byName":
                filter = x -> x.getName().equals(param);
                break;
            case "byInfo":
                filter = x -> x.getInformation().contains(param);
                break;
        }
        return filter;
    }
}
