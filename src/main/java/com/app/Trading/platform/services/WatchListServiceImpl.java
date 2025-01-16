package com.app.Trading.platform.services;

import com.app.Trading.platform.model.Coin;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.WatchList;
import com.app.Trading.platform.repositories.WatchListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class WatchListServiceImpl implements WatchListService{

    private final WatchListRepository watchListRepository;

    public WatchListServiceImpl(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    @Override
    public WatchList findUserWatchlist(Long userId) {
        WatchList watchList = watchListRepository.findByUserId(userId);
        if (watchList == null) {
            throw new RuntimeException("Watchlist not found");
        }

        return watchList;
    }

    @Override
    public WatchList createWatchList(User user) {
        WatchList watchList = new WatchList();
        watchList.setUser(user);

        return watchListRepository.save(watchList);
    }

    @Override
    public WatchList findById(Long id) {
        Optional<WatchList> optionalWatchList = watchListRepository.findById(id);
        if (optionalWatchList.isEmpty()) {
            throw new RuntimeException("Watchlist not found");
        }

        return optionalWatchList.get();
    }

    @Override
    public Coin addItemToWatchlist(Coin coin, User user) {
        WatchList watchList = findUserWatchlist(user.getId());
        if (watchList.getCoins().contains(coin)) {
            watchList.getCoins().remove(coin);
        } else watchList.getCoins().add(coin);

        watchListRepository.save(watchList);

        return coin;
    }
}
