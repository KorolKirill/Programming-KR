package Models;

import java.sql.Date;

public class Lot extends Product {

    private double currentPrice;
    private double betStep;
    private Date dateCreation;
    private String winner;

    @Override
    public String toString() {
        return "Lot{" +
                "currentPrice=" + currentPrice +
                ", betStep=" + betStep +
                ", dateCreation=" + dateCreation +
                ", winner='" + winner + '\'' +
                '}';
    }

    public Lot(Product product, double currentPrice, double betStep, Date dateCreation, String winner) {
        super(product.getName(), product.getOwner(), product.getInformation(), product.getId());
        this.currentPrice = currentPrice;
        this.betStep = betStep;
        this.dateCreation = dateCreation;
        this.winner = winner;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getBetStep() {
        return betStep;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public String getWinner() {
        return winner;
    }
}
