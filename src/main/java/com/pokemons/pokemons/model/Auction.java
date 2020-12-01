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
    @ManyToOne
    private Trainer trainer;
    private int quantity;
    private int price;

    public Auction() {
    }

    public Auction(Card card, Trainer trainer, int quantity, int price) {
        this.card = card;
        this.trainer = trainer;
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

    public Trainer getTrainer() {
        return trainer;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", card=" + card +
                ", trainer=" + trainer +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
