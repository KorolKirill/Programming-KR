package DAO.Interfaces;

import DAO.LotDao;
import Models.Lot;
import Models.Product;

import java.util.ArrayList;
import java.util.Collection;

public interface DaoLot {
    public int addLot(Product product, double initialPrice, double minStep);
    public Lot findById(int id);
    public ArrayList<Lot> getAllUserLots(String user);
    public ArrayList<Lot> getAllLots();
    public boolean changeWinner(int lotId,double bet, String user);
    public Collection<LotDao.BetHistory> getBetHistory(int id);
    public boolean deleteById (int id);
}
