package Services.Interfaces;

import DAO.LotDao;
import Models.Lot;
import Models.Product;

import java.util.ArrayList;
import java.util.Collection;

public interface LotService {
    public int createLot (Product product, double initialPrice, double minStep);
    public Lot getById(int id);
    public ArrayList<Lot> getAllLots ();
    public void makeBet(int lotId,double bet, String user);
    public Collection<LotDao.BetHistory> getBetHistory(int id);
    public void deleteLot(int id, double price);
    public ArrayList<Lot>  getAllUserLots (String user);
}
