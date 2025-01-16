package com.app.Trading.platform.repositories;

import com.app.Trading.platform.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {
    WatchList findByUserId(Long userId);
}
