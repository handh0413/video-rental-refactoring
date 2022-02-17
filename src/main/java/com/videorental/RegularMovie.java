package com.videorental;

public class RegularMovie extends Movie {
    public RegularMovie(String title, int priceCode) {
        super(title, priceCode);
    }

    @Override
    double getChargeFor(int daysRented, Rental rental) {
        double thisAmount = 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;
        return thisAmount;
    }
}
