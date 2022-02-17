package com.videorental;

public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title, int priceCode) {
        super(title, priceCode);
    }

    @Override
    double getChargeFor(int daysRented, Rental rental) {
        double thisAmount = 0;
        thisAmount += daysRented * 3;
        return thisAmount;
    }
}
