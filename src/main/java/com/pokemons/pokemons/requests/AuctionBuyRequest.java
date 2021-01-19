package com.pokemons.pokemons.requests;

import javax.validation.constraints.Min;

public class AuctionBuyRequest {
    private int auctionID;
    @Min(value = 1,message = "Minimal amount is 1.")
    private int amount;


    public int getAuctionID() {
        return auctionID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AuctionBuyRequest{" +
                "auctionID='" + auctionID + '\'' +
                ", amount=" + amount +
                '}';
    }
}
