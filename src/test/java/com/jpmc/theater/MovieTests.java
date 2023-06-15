package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void testMovieWithRegularAndAdditionalDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 30)));
        assertEquals(6.375, spiderMan.getTicketPrice(showing));

        System.out.println(Duration.ofMinutes(90));
    }

    @Test
    void testMovieWithNoDiscounts() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Showing showing = new Showing(theBatMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 30)));
        assertEquals(9, theBatMan.getTicketPrice(showing));

        System.out.println(Duration.ofMinutes(90));
    }

    @Test
    void testMovieWithSeventhDayDiscount() {
        Movie avengersEndgame = new Movie("Avengers: Endgamme", Duration.ofMinutes(270), 15, 0);
        Showing showing = new Showing(avengersEndgame, 4,
                LocalDateTime.of(LocalDate.of(2022, 6, 7), LocalTime.of(8, 15)));
        assertEquals(14, avengersEndgame.getTicketPrice(showing));

        System.out.println(Duration.ofMinutes(270));
    }

    @Test
    void testMovieWithSpecialCodeDiscountAndSeventhDayDiscount() {
        Movie transformersThree = new Movie("Transformers: Dark of the Moon", Duration.ofMinutes(120), 10, 1);
        Showing showing = new Showing(transformersThree, 7,
                LocalDateTime.of(LocalDate.of(2022, 5, 7), LocalTime.now()));
        assertEquals(5.5, transformersThree.getTicketPrice(showing));

        System.out.println(Duration.ofMinutes(120));
    }

    @Test
    void testMovieWithSpecialCodeDiscountAndStartTimeDiscount() {
        Movie littleMermaid = new Movie("The Little Mermaid", Duration.ofMinutes(90), 8, 1);
        Showing showing = new Showing(littleMermaid, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 40)));
        assertEquals(4.4, littleMermaid.getTicketPrice(showing));

        System.out.println(Duration.ofMinutes(90));
    }

    @Test
    void testBiggestDiscountIsApplied() {
        // Expect the sequence discount to win and be applied
        Movie justiceLeague = new Movie("Justice League", Duration.ofMinutes(180), 15, 1);
        Showing showing = new Showing(justiceLeague, 1, LocalDateTime.now());
        assertEquals(8.25, justiceLeague.getTicketPrice(showing));

        System.out.println(Duration.ofMinutes(180));
    }

    @Test
    void testBiggestAdditionalDiscountIsApplied() {
        // Expect the start time discount to be applied
        Movie avatarTwo = new Movie("Avatar: The Way of Wateer", Duration.ofMinutes(290), 12.5, 0);
        Showing showing = new Showing(avatarTwo, 0,
                LocalDateTime.of((LocalDate.of(2022, 07, 07)), LocalTime.of(13, 30)));
        assertEquals(9.375, avatarTwo.getTicketPrice(showing));

        System.out.println(Duration.ofMinutes(290));

    }
}
