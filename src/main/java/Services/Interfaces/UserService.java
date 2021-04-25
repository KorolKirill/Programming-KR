package Services.Interfaces;

import DAO.DaoFactory;
import Models.Product;
import Models.User;

public interface UserService {
    public User auth(String login, String password );
    public User registrateNewUSer(String login, String password);
    public void addBalance(double income,User user);
}