package DAO;

import DAO.Interfaces.DaoUser;
import Models.Lot;
import Models.Product;
import Models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDao implements DaoUser {
    DataBase db;

    public UserDao(DataBase db) {
        this.db = db;
    }

    public boolean addBalance(Double income,String login) {
        String query = "UPDATE lotsdatabase.users set balance = balance + ? where login = ?";
        try (PreparedStatement pr = db.getConnection().prepareStatement(query)){
            pr.setDouble(1,income);
            pr.setString(2,login);
            pr.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean registrateNewUser(String login,String password ) {
        try  {
            String query =  "Insert INTO lotsdatabase.users (login,password,balance) " +
                    " VALUES(?,?,0) ";
            try (PreparedStatement pr = db.getConnection().prepareStatement(query)){
                pr.setString(1,login);
                pr.setString(2,password);
                pr.executeUpdate();
            }
            return true;
        } catch (SQLException throwables) {
            System.out.println("cannot create new user");
            throwables.printStackTrace();
            return false;
        }
    }

    public  User auth(String login, String password) {
        String query =  String.format("select * from lotsdatabase.users where login ='%s' and password = '%s'",login,password);
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                double balance = resultSet.getDouble("balance");

                ArrayList<Product> products = null;
                ArrayList<Lot> lots = null;

                Boolean isAdmin = resultSet.getBoolean("isAdmin");
                resultSet.close();
                return new User(login,password,products,lots,balance,isAdmin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public  boolean isLoginReserved(String login) {
        try (Statement statement = db.getConnection().createStatement()) {
            if (statement.executeQuery(String.format("select * from lotsdatabase.users where login ='%s'",login)).next()){
                System.out.println("founded");
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
