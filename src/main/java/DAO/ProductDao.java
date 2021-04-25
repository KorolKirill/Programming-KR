package DAO;

import DAO.Interfaces.DaoProduct;
import Models.Product;
import Models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDao implements DaoProduct {
    DataBase db;

    public ProductDao(DataBase db) {
        this.db = db;
    }

    public ArrayList<Product> getAllUserProduct(String user) {
        ArrayList<Product> products = new ArrayList<>();
        try (Statement statement = db.getConnection().createStatement()) {
            String query = String.format("select * from lotsdatabase.products where owner ='%s'", user);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                String information = resultSet.getString("information");
                Product product = new Product(productName, user, information, id);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public Product findById(int id) {
        String query = String.format("select * from lotsdatabase.products where id ='%d'", id);
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String owner = resultSet.getString("owner");
                String info = resultSet.getString("information");
                resultSet.close();
                return new Product(name, owner, info, id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean remove(Product product) {
        String queryDelete = "DELETE  from lotsdatabase.products where id ='"+product.getId()+"'";
        try (Statement statement = db.getConnection().createStatement()) {
            statement.execute(queryDelete);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public int addProduct(String name, String info, String owner) {
        String query = "Insert INTO lotsdatabase.products (name,owner,information) " +
                " VALUES(?,?,?) ";
        try (PreparedStatement pr = db.getConnection().prepareStatement(query)) {
            pr.setString(1, name);
            pr.setString(2, owner);
            pr.setString(3, info);
            pr.executeUpdate();
            return lastId();
        } catch (SQLException throwables) {
            System.out.println("cannot add new product");
            throwables.printStackTrace();
        }
        return -1;
    }

    // int because we need to know what id of this product we have in db
    public int addProduct(String name, String info, User owner) {
        return addProduct(name,info,owner.getLogin());
    }

    private int lastId() {
        String query = "select * from lotsdatabase.products order by id DESC Limit 1";
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}