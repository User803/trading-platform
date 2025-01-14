package com.app.Trading.platform.services;

import com.app.Trading.platform.model.Coin;

import java.util.List;

public interface CoinService {

    List<Coin> getCoinList(int page);
    String getMarketChart(String coinId, int days);
    String getCoinDetails(String coinId);
    String searchCoin(String keyword);
    String getTop50CoinsByMarketCapRank();
    String getTrendingCoins();
    Coin findById(String coinId);
}
