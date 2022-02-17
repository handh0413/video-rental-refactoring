package com.videorental;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    public static final String NAME = "NAME_NOT_IMPORTANT";
    public static final String TITLE = "TITLE_NOT_IMPORTANT";

    // ctrl + alt + shift + t > introduce constant
    @Test
    public void returnNewCustomer() {
        Customer customer = new Customer(NAME);
        assertThat(customer, is(notNullValue()));
    }

    @Test
    public void statementForNoRental() {
        // arrange
        Customer customer = new Customer(NAME);

        // act
        String statement = customer.statement();

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "Amount owed is 0.0\n"
                + "You earned 0 frequent rental points"));
    }

    @Test
    public void statementForRegularMovieRentalForLessThan3Days() {
        // arrange
        Customer customer = new Customer(NAME);
        Movie movie = new Movie(TITLE, Movie.REGULAR);
        int daysRented = 2;
        Rental rental = new Rental(movie, daysRented);
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
        Customer customer = new Customer(NAME);
        Movie movie = new Movie(TITLE, Movie.REGULAR);
        int daysRented = 3;
        Rental rental = new Rental(movie, daysRented);
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
        Customer customer = new Customer(NAME);
        Movie movie = new Movie(TITLE, Movie.NEW_RELEASE);
        int daysRented = 1;
        Rental rental = new Rental(movie, daysRented);
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
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie(TITLE, Movie.CHILDREN);
        int daysRented = 4;
        Rental rental = new Rental(movie, daysRented);
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
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie(TITLE, Movie.CHILDREN);
        int daysRented = 3;
        Rental rental = new Rental(movie, daysRented);
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
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie(TITLE, Movie.NEW_RELEASE);
        int daysRented = 2;
        Rental rental = new Rental(movie, daysRented);
        customer.addRental(rental);

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t6.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 6.0\n"
                + "You earned 2 frequent rental points"));
    }
}
