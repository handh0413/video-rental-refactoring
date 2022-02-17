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

    @Override
    int getFrequentRentalPointsFor(int daysRented) {
        if ((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
