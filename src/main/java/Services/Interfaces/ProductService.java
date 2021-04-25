package Services.Interfaces;

import Models.Product;
import Models.User;

import java.util.ArrayList;

public interface ProductService {
    public ArrayList<Product> getAllUSerProduct(String user);
    public Product getProductById (int id);
    public int addProduct(String name, String info, User owner);
}
