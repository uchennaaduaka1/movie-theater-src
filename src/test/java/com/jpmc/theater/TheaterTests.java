package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {
    @Test
    void testTotalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 40);
    }

    @Test
    void testDiscountFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer lbj = new Customer("LeBron James", "id-34567");
        Reservation reservation = theater.reserve(lbj, 1, 10);
        assertEquals(reservation.totalFee(), 80);
    }

    @Test
    void testIllegalStateExceptionMessage() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer mj = new Customer("Michael Jordan", "id-12345");
        Throwable exception = assertThrows(IllegalStateException.class, () -> theater.reserve(mj, 10, 4));
        assertEquals("not able to find any showing for given sequence 10", exception.getMessage());
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
        System.out.println(" ");
        theater.printScheduleJson();
    }
}
