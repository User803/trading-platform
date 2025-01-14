package com.app.Trading.platform.repositories;

import com.app.Trading.platform.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, String> {
}
