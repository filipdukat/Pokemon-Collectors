package com.pokemons.pokemons.view_model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuctionViewModel {
    private int auctionID;
    private int cardPrice;
    private int cardQuantity;
    private String auctionOwner;
    private String cardURL;

}
