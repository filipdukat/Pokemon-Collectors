package com.pokemons.pokemons.view_model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuctionBuyPageViewModel {
    private List<AuctionViewModel> auctions;
    private int userCash;
}
