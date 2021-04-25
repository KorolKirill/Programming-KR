package Services;

import DAO.DaoFactory;
import DAO.Interfaces.DaoLot;
import DAO.Interfaces.DaoProduct;
import DAO.Interfaces.DaoUser;
import DAO.LotDao;
import Models.Lot;
import Models.Product;
import Services.Interfaces.LotService;

import java.util.ArrayList;
import java.util.Collection;

public class LotServiceIml implements LotService {
    DaoFactory daoFactory;
    DaoUser daoUser;
    DaoProduct daoProduct;
    DaoLot daoLot;

    public LotServiceIml(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        daoProduct = daoFactory.getDaoProduct();
        daoUser =daoFactory.getDaoUser();
        daoLot = daoFactory.getDaoLot();
    }

    public void deleteLot(int id, double price) {
        Lot lot = getById(id);
        daoLot.deleteById(id);
        daoProduct.addProduct(lot.getName(),lot.getInformation(),lot.getWinner());
        daoUser.addBalance(-1*price,lot.getWinner());
        daoUser.addBalance(price,lot.getOwner());

    }

    public void makeBet(int lotId,double bet, String user) {
        Lot lot = getById(lotId);
        if (daoLot.changeWinner(lotId,bet,user)) {
            System.out.println("here");
            if (!lot.getWinner().equals(lot.getOwner())) {
                daoUser.addBalance(lot.getCurrentPrice(),lot.getWinner());
            }
            daoUser.addBalance(-1*bet,user);
        }
    }


    public Collection<LotDao.BetHistory> getBetHistory(int id) {
        return daoLot.getBetHistory(id);
    }

    public int createLot (Product product, double initialPrice, double minStep) {
        int lotId = daoLot.addLot(product,initialPrice,minStep);
        if (lotId!=-1) {
            daoProduct.remove(product);
        }
        return lotId;
    }
    public Lot getById(int id) {
        return  daoLot.findById(id);
    }
    public ArrayList<Lot>  getAllUserLots (String user) {
        return daoLot.getAllUserLots(user);
    }
    public ArrayList<Lot>  getAllLots () {
        return daoLot.getAllLots();
    }

}
