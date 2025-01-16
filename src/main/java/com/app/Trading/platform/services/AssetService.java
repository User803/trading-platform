package com.app.Trading.platform.services;

import com.app.Trading.platform.model.Asset;
import com.app.Trading.platform.model.Coin;
import com.app.Trading.platform.model.User;

import java.util.List;

public interface AssetService {

    Asset createAsset(User user, Coin coin, double quantity);
    Asset getAssetById(Long assetId);
    Asset getAssetByUserIdAndAssetId(Long userId, Long assetId);
    Asset findAssetByUserIdAndCoinId(Long userId, String coinId);
    Asset updateAsset(Long assetId, double quantity);
    List<Asset> getUserAssets(Long userId);
    void deleteAsset(Long assetId);
}
