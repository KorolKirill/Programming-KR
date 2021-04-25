package DAO.Interfaces;

import Models.Product;
import Models.User;

import java.util.ArrayList;

public interface DaoProduct {
    // int because we need to know what id of this product we have in db
    public int addProduct(String name, String info, User owner);
    public ArrayList<Product> getAllUserProduct(String user);
    public Product findById (int id);
    public boolean remove(Product product);
    public int addProduct(String name, String info, String owner);
}
