package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    /**
     * 
     * @param customer      customer object of reservation
     * @param showing       showing the customer reserved
     * @param audienceCount number of tickets being reserved
     */
    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public double totalFee() {
        return showing.getMovieFee() * audienceCount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Showing getShowingInfo() {
        return showing;
    }
}