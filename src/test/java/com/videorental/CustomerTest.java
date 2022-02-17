package com.videorental;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    // ctrl + alt + shift + t > introduce constant
    public static final String NAME = "NAME_NOT_IMPORTANT";
    public static final String TITLE = "TITLE_NOT_IMPORTANT";
    // ctrl + alt + shift + t > introduce field
    private Customer customer = new Customer(NAME);


    @Test
    public void returnNewCustomer() {
        assertThat(customer, is(notNullValue()));
    }

    @Test
    public void statementForNoRental() {
        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "Amount owed is 0.0\n"
                + "You earned 0 frequent rental points"));
    }

    @Test
    public void statementForRegularMovieRentalForLessThan3Days() {
        // arrange
        int daysRented = 2;
        int priceCode = Movie.REGULAR;
        Rental rental = createRentalFor(daysRented, priceCode);
        customer.addRental(rental);

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 2.0\n"
                + "You earned 1 frequent rental points"));
    }

    @Test
    public void statementForRegularMovieRentalForMoreThan2Days() {
        // arrange
        int daysRented = 3;
        int priceCode = Movie.REGULAR;
        Rental rental = createRentalFor(daysRented, priceCode);
        customer.addRental(rental);

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.5\n"
                + "You earned 1 frequent rental points"));
    }

    @Test
    public void statementForNewReleaseMovie() {
        // arrange
        int daysRented = 1;
        int priceCode = Movie.NEW_RELEASE;
        Rental rental = createRentalFor(daysRented, priceCode);
        customer.addRental(rental);

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent rental points"));
    }

    @Test
    public void statementForChildrenMovieRentalMoreThan3Days() {
        // arrange
        int daysRented = 4;
        int priceCode = Movie.CHILDREN;
        Rental rental = createRentalFor(daysRented, priceCode);
        customer.addRental(rental);

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent rental points"));
    }

    @Test
    public void statementForChildrenMovieRentalLessThan4Days() {
        // arrange
        int daysRented = 3;
        int priceCode = Movie.CHILDREN;
        Rental rental = createRentalFor(daysRented, priceCode);
        customer.addRental(rental);

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t1.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 1.5\n"
                + "You earned 1 frequent rental points"));
    }

    @Test
    public void statementForNewReleaseMovieRentalMoreThan1Day() {
        // arrange
        int daysRented = 2;
        int priceCode = Movie.NEW_RELEASE;
        Rental rental = createRentalFor(daysRented, priceCode);
        customer.addRental(rental);

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t6.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 6.0\n"
                + "You earned 2 frequent rental points"));
    }

    // ctrl + alt + m > extract method
    private Rental createRentalFor(int daysRented, int priceCode) {
        Movie movie = new Movie(TITLE, priceCode);
        return new Rental(movie, daysRented);
    }
}
