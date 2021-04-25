package Models;

import java.util.ArrayList;

public class User {
    private String login;
    private String password;
    private ArrayList<Product> products;
    private ArrayList<Lot> lots;
    private double balance;
    private Boolean isAdmin;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setLots(ArrayList<Lot> lots) {
        this.lots = lots;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Lot> getLots() {
        return lots;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        products = new ArrayList<>();
        lots = new ArrayList<>();
        balance = 0;
    }

    public User(String login, String password, ArrayList<Product> products, ArrayList<Lot> lots, double balance, Boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.products = products;
        this.lots = lots;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", products=" + products +
                ", lots=" + lots +
                ", balance=" + balance +
                '}';
    }
}
