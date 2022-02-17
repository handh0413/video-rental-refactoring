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
        String result = getStatementHeader();
        result += getRentalLineReports();
        result = getStatementFooter(result);
        return result;
    }

    // ctrl + alt + m > extract method
    private String getStatementHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    // ctrl + alt + m > extract method
    private String getRentalLineReports() {
        String result = "";
        for (Rental rental : rentals) {
            result += "\t" + rental.getCharge() + "(" + rental.getMovie().getTitle() + ")" + "\n";
        }
        return result;
    }

    // ctrl + alt + m > extract method
    private String getStatementFooter(String result) {
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
            frequentRentalPoints += rental.getFrequentRentalPointsFor();
        }
        return frequentRentalPoints;
    }

}
