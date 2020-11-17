package com.pokemons.pokemons.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Auction {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Card card;
    private int quantity;
    private int price;

    public Auction() {
    }

    public Auction(Card card, int quantity, int price) {
        this.card = card;
        this.quantity = quantity;
        this.price = price;
    }

    public Card getCard() {
        return card;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", card=" + card +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
