package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void reservationBookedWithSpecialCodeDiscount() {
        Customer customer = new Customer("John Doe", "12345");
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 30)));
        Reservation reservation1 = new Reservation(customer, showing, 3);
        assertTrue(reservation1.totalFee() == 19.125);
        assertTrue(reservation1.getCustomer().getCustomerName().equals("John Doe"));
        assertTrue(reservation1.getCustomer().getCustomerId().equals("12345"));
    }

    @Test
    void reservationBookedWithoutDiscounts() {
        Customer customer = new Customer("LeBron James", "23064");
        Movie movie = new Movie("Space Jam", Duration.ofMinutes(120), 15, 0);
        Showing showing = new Showing(movie, 4, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 30)));
        Reservation reservation = new Reservation(customer, showing, 6);
        assertTrue(reservation.totalFee() == 90);
        assertTrue(reservation.getCustomer().getCustomerId().equals("23064"));
        assertTrue(reservation.getCustomer().getCustomerName().equals("LeBron James"));
    }

    @Test
    void reservationBookedWithSequenceDiscounts() {
        Customer customer = new Customer("James Blake", "20256");
        Movie movie = new Movie("Transformers 3: Dark of the Moon", Duration.ofMinutes(105), 10.5, 0);
        Showing showing = new Showing(movie, 1, LocalDateTime.now());
        Reservation reservation = new Reservation(customer, showing, 4);
        assertTrue(reservation.totalFee() == 30);
        assertTrue(reservation.getCustomer().getCustomerName().equals("James Blake"));
        assertTrue(reservation.getCustomer().getCustomerId().equals("20256"));
    }

}
