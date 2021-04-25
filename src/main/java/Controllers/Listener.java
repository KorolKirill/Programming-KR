package Controllers;

import DAO.DaoFactory;
import DAO.DataBase;
import Services.Interfaces.LotService;
import Services.Interfaces.ProductService;
import Services.LotServiceIml;
import Services.ProductServiceIml;
import Services.Interfaces.UserService;
import Services.UserServiceIml;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataBase db = new DataBase();
        DaoFactory daoFactory = db.getDaoFactory();
        System.out.println("Initialized");

        UserService userService = new UserServiceIml(daoFactory);
        sce.getServletContext().setAttribute("userService",userService);

        ProductService productService= new ProductServiceIml(daoFactory);
        sce.getServletContext().setAttribute("productService",productService);

        LotService lotService = new LotServiceIml(daoFactory);
        sce.getServletContext().setAttribute("lotService",lotService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
