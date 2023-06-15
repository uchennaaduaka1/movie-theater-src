package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;
    private static LocalTime MOVIE_SPECIAL_START_TIME = LocalTime.of(11, 00);
    private static LocalTime MOVIE_SPECIAL_END_TIME = LocalTime.of(16, 00);
    private static int MOVIE_SPECIAL_DAY_OF_MONTH = 7;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    /**
     * @param title       title of the movie
     * @param runningTime running time of movie
     * @param ticketPrice ticket price of movie exlcuding disocunt
     * @param specialCode special code for movie discount
     * @return instance of a movie object with
     */
    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay()) - getAdditionalDiscount(showing.getStartTime());
    }

    private double getDiscount(int showSequence) {
        double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2; // 20% discount for special movie
        }

        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        } else {
            sequenceDiscount = 0;
        }

        // biggest discount wins
        return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
    }

    private double getAdditionalDiscount(LocalDateTime startTime) {
        double hourDiscount = 0;
        if ((MOVIE_SPECIAL_START_TIME.isBefore(startTime.toLocalTime()))) {
            if (MOVIE_SPECIAL_END_TIME.isAfter(startTime.toLocalTime())) {
                hourDiscount = ticketPrice * 0.25; // 25% discount for movies starting between 11AM - 4PM
            }
        }

        double dayDiscount = 0;
        int seventhDay = startTime.getDayOfMonth();
        if (MOVIE_SPECIAL_DAY_OF_MONTH == seventhDay) {
            dayDiscount = 1; // $1 discount for movie on 7th day of month;
        }

        // biggest discount wins
        return hourDiscount > dayDiscount ? hourDiscount : dayDiscount;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}