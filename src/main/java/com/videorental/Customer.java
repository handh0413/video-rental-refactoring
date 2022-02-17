package com.videorental;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        Iterator<Rental> iterator = rentals.iterator();
        String result = "Rental Record for " + getName() + "\n";

        while (iterator.hasNext()) {
            Rental each = (Rental) iterator.next();
            // show figures
            result += "\t" + String.valueOf(each.getCharge()) + "(" + each.getMovie().getTitle() + ")" + "\n";
        }

        result += "Amount owed is " + getTotalAmount() + "\n";
        result += "You earned " + getFrequentRentalPoints() + " frequent rental points";
        return result;
    }

    // ctrl + alt + m > extract method
    private double getTotalAmount() {
        double totalAmount = 0;
        for (Rental rental : rentals) {
            totalAmount += rental.getCharge();
        }
        return totalAmount;
    }

    // ctrl + alt + m > extract method
    private int getFrequentRentalPoints() {
        int frequentRentalPoints = 0;
        for (Rental rental : rentals) {
            frequentRentalPoints = getFrequentRentalPointsFor(rental);
        }
        return frequentRentalPoints;
    }

    private int getFrequentRentalPointsFor(Rental rental) {
        int frequentRentalPoints = 0;
        frequentRentalPoints++;
        // add bonus for a two day new release rental
        if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1) {
            frequentRentalPoints++;
        }
        return frequentRentalPoints;
    }

}
