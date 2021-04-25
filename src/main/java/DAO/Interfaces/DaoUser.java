package DAO.Interfaces;

import Models.User;

public interface DaoUser {
    public User auth(String login, String password);
    public boolean registrateNewUser(String login,String password);
    public  boolean isLoginReserved(String login);
    public boolean addBalance(Double income,String login);
}
