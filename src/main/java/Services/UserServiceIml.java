package Services;

import DAO.DaoFactory;
import DAO.Interfaces.DaoLot;
import DAO.Interfaces.DaoProduct;
import DAO.Interfaces.DaoUser;
import Models.Product;
import Models.User;
import Services.Interfaces.UserService;

public class UserServiceIml implements UserService {
    DaoFactory dao;
    DaoUser daoUser;
    DaoProduct daoProduct;
    DaoLot daoLot;


    public UserServiceIml(DaoFactory dao) {
        this.dao = dao;
        daoUser = dao.getDaoUser();
        daoProduct = dao.getDaoProduct();
        daoLot = dao.getDaoLot();
    }


    public User registrateNewUSer(String login, String password) {
        if (!daoUser.isLoginReserved(login)) {
            if (daoUser.registrateNewUser(login,password))
                return new User(login, password);
        }
        return null;
    }

    public User auth(String login, String password ) {
        User user = daoUser.auth(login,password);
        user.setProducts(daoProduct.getAllUserProduct(login));
        user.setLots(daoLot.getAllUserLots(login));
        return user ;
    }

    public void addBalance(double income,User user) {
        if (daoUser.addBalance(income,user.getLogin())) {
            changeUserBalance(user,income);
        }
    }

    private void changeUserBalance(User user,double income) {
        user.setBalance( user.getBalance() + income  );
    }

}
