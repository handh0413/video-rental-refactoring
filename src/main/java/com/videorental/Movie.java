package com.videorental;

import javax.swing.text.AsyncBoxView;

public abstract class Movie {
    public static final int REGULAR = 1;
    public static final int NEW_RELEASE = 2;
    public static final int CHILDREN = 3;

    private String title;
    private int priceCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    // move instance method + refactoring
    abstract double getChargeFor(int daysRented, Rental rental);

    abstract int getFrequentRentalPointsFor(int daysRented);
}
