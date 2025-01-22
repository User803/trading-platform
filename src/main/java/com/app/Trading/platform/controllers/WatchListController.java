package com.app.Trading.platform.controllers;

import com.app.Trading.platform.model.Coin;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.model.WatchList;
import com.app.Trading.platform.services.CoinService;
import com.app.Trading.platform.services.UserService;
import com.app.Trading.platform.services.WatchListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {

    private final WatchListService watchListService;
    private final UserService userService;
    private final CoinService coinService;

    public WatchListController(WatchListService watchListService, UserService userService, CoinService coinService) {
        this.watchListService = watchListService;
        this.userService = userService;
        this.coinService = coinService;
    }

    @GetMapping("/user")
    public ResponseEntity<WatchList> getUserWatchlist(
            @RequestHeader("Authorization") String jwt) {

        User user = userService.findUserProfileByJwt(jwt);
        WatchList watchList = watchListService.findUserWatchlist(user.getId());

        return ResponseEntity.ok(watchList);
    }

    @PostMapping("/create")
    public ResponseEntity<WatchList> createWatchlist(
            @RequestHeader("Authorization") String jwt) {

        User user = userService.findUserProfileByJwt(jwt);
        WatchList watchList = watchListService.createWatchList(user);

        return new ResponseEntity<>(watchList, HttpStatus.CREATED);
    }

    @GetMapping("/{watchlistId}")
    public ResponseEntity<WatchList> createWatchlist(@PathVariable Long watchlistId) {
        WatchList watchList = watchListService.findById(watchlistId);

        return new ResponseEntity<>(watchList, HttpStatus.OK);
    }

    @PatchMapping("/add/coin/{coinId}")
    public ResponseEntity<Coin> addItemToWatchlist(
            @RequestHeader("Authorization") String jwt,
            @PathVariable String coinId) {

        User user = userService.findUserProfileByJwt(jwt);
        Coin coin = coinService.findById(coinId);
        Coin addedCoin = watchListService.addItemToWatchlist(coin, user);

        return new ResponseEntity<>(addedCoin, HttpStatus.OK);
    }
}
