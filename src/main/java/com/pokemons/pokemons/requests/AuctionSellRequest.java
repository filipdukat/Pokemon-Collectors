package com.pokemons.pokemons.requests;

import com.pokemons.pokemons.model.Card;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class AuctionSellRequest {
    private String cardID;
    @Min(value = 1,message = "Minimal amount is 1.")
    private int amount;
    @Min(value = 1,message = "Minimal price is 1.")
    private int price;

    public String getCardID() {
        return cardID;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AuctionSellRequest{" +
                "card=" + cardID +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
