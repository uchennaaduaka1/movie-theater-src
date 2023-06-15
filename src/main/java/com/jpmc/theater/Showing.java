package com.jpmc.theater;

import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private int showSequence;
    private LocalDateTime showStartTime;

    /**
     * 
     * @param movie         movie part of the showing
     * @param showSequence  sequence of the movie
     * @param showStartTime start time of the show
     * @return showing object representing the movie that is being shown
     */
    public Showing(Movie movie, int showSequence, LocalDateTime showStartTime) {
        this.movie = movie;
        this.showSequence = showSequence;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return getSequenceOfTheDay() == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice(this);
    }

    public int getSequenceOfTheDay() {
        return showSequence;
    }
}
