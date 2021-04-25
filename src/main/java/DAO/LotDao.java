package DAO;

import DAO.Interfaces.DaoLot;
import Models.Lot;
import Models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.Stack;

public class LotDao implements DaoLot {
    DataBase db;

    public LotDao(DataBase dataBase) {
        this.db = dataBase;
    }

    public ArrayList<Lot> getAllLots() {
        ArrayList<Lot> lots = new ArrayList<>();
        try (Statement statement = db.getConnection().createStatement()) {
            String query = "select * from lotsdatabase.lots;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String owner = resultSet.getString("owner");
                String info = resultSet.getString("information");
                double currentPrice = resultSet.getDouble("currentPrice");
                double betStep = resultSet.getDouble("betStep");
                Date dateCreation = resultSet.getDate("dateCreation");
                String winner = resultSet.getString("winner");
                int id = resultSet.getInt("id");
                Product product = new Product(name, owner, info, id);
                Lot lot = new Lot(product,currentPrice,betStep,dateCreation,winner);
                lots.add(lot);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lots;
    }
    public class BetHistory {
        public String getLogin() {
            return login;
        }

        public double getBet() {
            return bet;
        }

        public String getDate() {
            return date;
        }

        public BetHistory(String login, double bet, String date) {
            this.login = login;
            this.bet = bet;
            this.date = date;
        }
        String login;
        double bet;
        String date;
    }

    public Collection<BetHistory> getBetHistory(int id) {
        Stack<BetHistory> betHistories = new Stack<>();
        try (Statement statement = db.getConnection().createStatement()) {
            String tableName = "lotsdatabase.lot_"+id+"_history";
            String query = "select * from "+tableName+" ;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("login");
                String date = resultSet.getString("betDate");
                double bet = resultSet.getDouble("bet");
                BetHistory history = new BetHistory(name,bet,date);
                betHistories.push(history);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return betHistories;
    }
    private boolean deleteHistoryTable(int id) {
        try (Statement statement = db.getConnection().createStatement()) {
            String tableName =" lotsdatabase.lot_"+id+"_history ";
            String query = "DROP TABLE "+tableName;
            statement.execute(query);
            System.out.println("dropped");
            return true;
        } catch (SQLException throwables) {
            System.out.println("problems with deleting hitory table ");
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteById (int id) {
        try (Statement statement = db.getConnection().createStatement()) {
            String tableName =" lotsdatabase.lot_"+id+"_history ";
            String query = "DELETE from lotsdatabase.lots where id ="+id;
            statement.execute(query);
            deleteHistoryTable(id);
            return true;
        } catch (SQLException throwables) {
            System.out.println("problems with lot deleting ");
            throwables.printStackTrace();
        }
        return false;
    }

    private boolean addBetHistory(double bet, String user,int lotId) {
        try (Statement statement = db.getConnection().createStatement()) {
            java.util.Date date = new java.util.Date();
            String tableName =" lotsdatabase.lot_"+lotId+"_history ";
            String query = String.format(
                    "Insert INTO  "+tableName+
                            "(login,bet,betDate) " +
                            " VALUES('%s',%f,'%s') ",user,bet,date.toString());
            statement.execute(query);
            return true;
        } catch (SQLException throwables) {
            System.out.println("problems with lot history changing");
            throwables.printStackTrace();
        }
        return false;
    }


    public boolean changeWinner(int lotId,double bet, String user) {
        String query = "UPDATE lotsdatabase.lots set winner = ? , currentPrice = ? where id = ?";
        try (PreparedStatement pr = db.getConnection().prepareStatement(query)){

            pr.setString(1,user);
            pr.setDouble(2,bet);
            pr.setInt(3,lotId);
            pr.executeUpdate();
            addBetHistory(bet,user,lotId);
            System.out.println("changeWi");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }


    public ArrayList<Lot> getAllUserLots(String user) {
        ArrayList<Lot> lots = new ArrayList<>();
        try (Statement statement = db.getConnection().createStatement()) {
            String query = "select * from lotsdatabase.lots where owner='"+user+"' ;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String owner = resultSet.getString("owner");
                String info = resultSet.getString("information");
                double currentPrice = resultSet.getDouble("currentPrice");
                double betStep = resultSet.getDouble("betStep");
                Date dateCreation = resultSet.getDate("dateCreation");
                String winner = resultSet.getString("winner");
                int id = resultSet.getInt("id");
                Product product = new Product(name, owner, info, id);
                Lot lot = new Lot(product,currentPrice,betStep,dateCreation,winner);
                lots.add(lot);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lots;
    }


    public Lot findById(int id) {
        String query = String.format("select * from lotsdatabase.lots where id ='%d'", id);
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String owner = resultSet.getString("owner");
                String info = resultSet.getString("information");
                double currentPrice = resultSet.getDouble("currentPrice");
                double betStep = resultSet.getDouble("betStep");
                Date dateCreation = resultSet.getDate("dateCreation");
                String winner = resultSet.getString("winner");
                resultSet.close();
                Product product = new Product(name, owner, info, id);
                return new Lot(product,currentPrice,betStep,dateCreation,winner);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int addLot(Product product, double initialPrice, double minStep) {
        String query = "Insert INTO lotsdatabase.lots " +
                "(name,owner,information,currentPrice,betStep,dateCreation,winner) " +
                " VALUES(?,?,?,?,?,?,?) ";
        try (PreparedStatement pr = db.getConnection().prepareStatement(query)) {
            pr.setString(1, product.getName());
            pr.setString(2, product.getOwner());
            pr.setString(3, product.getInformation());
            pr.setDouble(4, initialPrice);
            pr.setDouble(5, minStep);
            java.util.Date date = new java.util.Date();
            Date dateSql = new Date(date.getTime());
            pr.setDate(6, dateSql);
            pr.setString(7, product.getOwner());
            pr.executeUpdate();
            int lotId = lastId();
            createLotHistoryTable(lotId);
            return lotId;
        } catch (SQLException throwables) {
            System.out.println("cannot create a lot");
            throwables.printStackTrace();
        }
        return -1;
    }

    private void createLotHistoryTable(int id) {
        try (Statement statement = db.getConnection().createStatement()) {
            String tableName = "lot_" + id + "_history ";
            String queryTableCreate = "Create table if not exists lotsdatabase." + tableName +
                    "(" +
                    "id INT key NOT NULL AUTO_INCREMENT," +
                    "login VARCHAR(50) not null," +
                    "bet float," +
                    "betDate varchar(200)" +
                    ")";

            statement.execute(queryTableCreate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


        private int lastId() {
        String query = "select * from lotsdatabase.lots order by id DESC Limit 1";
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
