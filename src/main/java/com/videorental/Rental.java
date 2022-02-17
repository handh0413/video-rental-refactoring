package com.videorental;

public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    // ctrl + alt + shift + t > move instance method
    // shift + f6 > method 명 수정
    public double getCharge() {
        return getChargeFor(daysRented);
    }

    private double getChargeFor(int daysRented) {
        double thisAmount = 0;
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;
                break;

            case Movie.NEW_RELEASE:
                thisAmount += daysRented * 3;
                break;

            case Movie.CHILDREN:
                thisAmount += 1.5;
                if (daysRented > 3)
                    thisAmount += (daysRented - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    int getFrequentRentalPointsFor() {
        // add bonus for a two day new release rental
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
