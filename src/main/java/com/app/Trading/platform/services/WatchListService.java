package com.app.Trading.platform.services;

import com.app.Trading.platform.model.Coin;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.WatchList;

public interface WatchListService {

    WatchList findUserWatchlist(Long userId);
    WatchList createWatchList(User user);
    WatchList findById(Long id);
    Coin addItemToWatchlist(Coin coin, User user);
}
