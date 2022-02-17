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
        return movie.getChargeFor(daysRented, this);
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
