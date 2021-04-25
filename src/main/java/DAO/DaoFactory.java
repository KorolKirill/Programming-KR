package DAO;

import DAO.Interfaces.DaoLot;
import DAO.Interfaces.DaoProduct;
import DAO.Interfaces.DaoUser;

public class DaoFactory {
    DaoUser daoUser;
    DataBase db;
    DaoProduct daoProduct;
    DaoLot daoLot;
    public DaoFactory(DataBase db) {
        this.db = db;
        daoUser = new UserDao(db);
        daoProduct = new ProductDao(db);
        daoLot = new LotDao(db);
    }

    public DaoLot getDaoLot() {
        return daoLot;
    }

    public DaoProduct getDaoProduct() {
        return daoProduct;
    }

    public DaoUser getDaoUser() {
        return daoUser;
    }
}
