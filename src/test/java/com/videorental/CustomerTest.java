package com.videorental;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
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
        customer.addRental(createRentalFor(2, Movie.REGULAR));

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 2.0\n"
                + "You earned 1 frequent rental points"));
    }

    @Test
    public void statementForRegularMovieRentalForMoreThan2Days() {
        // arrange
        customer.addRental(createRentalFor(3, Movie.REGULAR));

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.5\n"
                + "You earned 1 frequent rental points"));
    }

    @Test
    public void statementForNewReleaseMovie() {
        // arrange
        customer.addRental(createRentalFor(1, Movie.NEW_RELEASE));

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent rental points"));
    }

    @Test
    public void statementForChildrenMovieRentalMoreThan3Days() {
        // arrange
        customer.addRental(createRentalFor(4, Movie.CHILDREN));

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent rental points"));
    }

    @Test
    public void statementForChildrenMovieRentalLessThan4Days() {
        // arrange
        customer.addRental(createRentalFor(3, Movie.CHILDREN));

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t1.5(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 1.5\n"
                + "You earned 1 frequent rental points"));
    }

    @Test
    public void statementForNewReleaseMovieRentalMoreThan1Day() {
        // arrange
        customer.addRental(createRentalFor(2, Movie.NEW_RELEASE));

        // assert
        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t6.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 6.0\n"
                + "You earned 2 frequent rental points"));
    }

    @Test
    public void statementForNewMovieRental() {
        Movie regularMovie = new RegularMovie(TITLE, Movie.REGULAR);
        Movie newReleaseMovie = new NewReleaseMovie(TITLE, Movie.NEW_RELEASE);
        Movie childrenMovie = new ChildrenMovie(TITLE, Movie.CHILDREN);

        customer.addRental(new Rental(regularMovie, 1));
        customer.addRental(new Rental(newReleaseMovie, 4));
        customer.addRental(new Rental(childrenMovie, 4));

        assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
                + "\t2.0(TITLE_NOT_IMPORTANT)\n"
                + "\t12.0(TITLE_NOT_IMPORTANT)\n"
                + "\t3.0(TITLE_NOT_IMPORTANT)\n"
                + "Amount owed is 17.0\n"
                + "You earned 4 frequent rental points"));
    }

    // ctrl + alt + m > extract method
    private Rental createRentalFor(int daysRented, int priceCode) {
        Movie movie = null;
        switch (priceCode) {
            case Movie.NEW_RELEASE:
                movie = new NewReleaseMovie(TITLE, priceCode);
                break;
            case Movie.REGULAR:
                movie = new RegularMovie(TITLE, priceCode);
                break;
            case Movie.CHILDREN:
                movie = new ChildrenMovie(TITLE, priceCode);
                break;
        }
        return new Rental(movie, daysRented);
    }
}
