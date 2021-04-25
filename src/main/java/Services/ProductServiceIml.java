package Services;

import DAO.DaoFactory;
import DAO.Interfaces.DaoProduct;
import DAO.Interfaces.DaoUser;
import Models.Product;
import Models.User;
import Services.Interfaces.ProductService;

import java.util.ArrayList;

public class ProductServiceIml implements ProductService {
    DaoFactory dao;
    DaoUser daoUser;
    DaoProduct daoProduct;

    public ProductServiceIml(DaoFactory dao) {
        this.dao = dao;
        daoUser = dao.getDaoUser();
        daoProduct = dao.getDaoProduct();
    }
    public int addProduct(String name, String info, User owner) {
        int id = daoProduct.addProduct(name,info,owner);
        return id;
    }

    public ArrayList<Product> getAllUSerProduct(String user) {
        return daoProduct.getAllUserProduct(user);
    }

    public Product getProductById (int id) {
        return  daoProduct.findById(id);
    }


}
