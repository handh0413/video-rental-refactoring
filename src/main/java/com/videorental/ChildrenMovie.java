package com.videorental;

public class ChildrenMovie extends Movie {
    public ChildrenMovie(String title, int priceCode) {
        super(title, priceCode);
    }

    @Override
    double getChargeFor(int daysRented, Rental rental) {
        double thisAmount = 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }
}
