package com.app.Trading.platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;

@Entity
public class  Coin {

    @Id
    @JsonProperty
    private String id;

    @JsonProperty
    private String symbol;

    @JsonProperty
    private String name;

    @JsonProperty
    private String image;

    @JsonProperty("current_price")
    private double currentPrice;

    @JsonProperty("market_cap")
    private long marketCap;

    @JsonProperty("market_cap_rank")
    private int marketCapRank;

    @JsonProperty("fully_diluted_valuation")
    private long fullyDilutedValuation;

    @JsonProperty("total_volume")
    private long totalVolume;

    @JsonProperty("high_24h")
    private double high24h;

    @JsonProperty("low_24h")
    private double low24h;

    @JsonProperty("price_change_24h")
    private double priceChange24h;

    @JsonProperty("price_change_percentage_24h")
    private double priceChangePercentage24h;

    @JsonProperty("market_cap_change_24h")
    private long marketCapChange24h;

    @JsonProperty("market_cap_change_percentage_24h")
    private double marketCapChangePercentage24h;

    @JsonProperty("circulating_supply")
    private double circulatingSupply;

    @JsonProperty("total_supply")
    private double totalSupply;

    @JsonProperty("max_supply")
    private double maxSupply;

    @JsonProperty
    private double ath;

    @JsonProperty("ath_change_percentage")
    private double athChangePercentage;

    @JsonProperty("ath_date")
    private Date athDate;

    @JsonProperty
    private double atl;

    @JsonProperty("atl_change_percentage")
    private double atlChangePercentage;

    @JsonProperty("atl_date")
    private Date atlDate;

    @JsonProperty
    @JsonIgnore
    private String roi;

    @JsonProperty("last_updated")
    private Date lastUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    public int getMarketCapRank() {
        return marketCapRank;
    }

    public void setMarketCapRank(int marketCapRank) {
        this.marketCapRank = marketCapRank;
    }

    public long getFullyDilutedValuation() {
        return fullyDilutedValuation;
    }

    public void setFullyDilutedValuation(long fullyDilutedValuation) {
        this.fullyDilutedValuation = fullyDilutedValuation;
    }

    public long getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(long totalVolume) {
        this.totalVolume = totalVolume;
    }

    public double getHigh24h() {
        return high24h;
    }

    public void setHigh24h(double high24h) {
        this.high24h = high24h;
    }

    public double getLow24h() {
        return low24h;
    }

    public void setLow24h(double low24h) {
        this.low24h = low24h;
    }

    public double getPriceChange24h() {
        return priceChange24h;
    }

    public void setPriceChange24h(double priceChange24h) {
        this.priceChange24h = priceChange24h;
    }

    public double getPriceChangePercentage24h() {
        return priceChangePercentage24h;
    }

    public void setPriceChangePercentage24h(double priceChangePercentage24h) {
        this.priceChangePercentage24h = priceChangePercentage24h;
    }

    public long getMarketCapChange24h() {
        return marketCapChange24h;
    }

    public void setMarketCapChange24h(long marketCapChange24h) {
        this.marketCapChange24h = marketCapChange24h;
    }

    public double getMarketCapChangePercentage24h() {
        return marketCapChangePercentage24h;
    }

    public void setMarketCapChangePercentage24h(double marketCapChangePercentage24h) {
        this.marketCapChangePercentage24h = marketCapChangePercentage24h;
    }

    public double getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(double circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public double getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public double getAth() {
        return ath;
    }

    public void setAth(double ath) {
        this.ath = ath;
    }

    public double getAthChangePercentage() {
        return athChangePercentage;
    }

    public void setAthChangePercentage(double athChangePercentage) {
        this.athChangePercentage = athChangePercentage;
    }

    public Date getAthDate() {
        return athDate;
    }

    public void setAthDate(Date athDate) {
        this.athDate = athDate;
    }

    public double getAtl() {
        return atl;
    }

    public void setAtl(double atl) {
        this.atl = atl;
    }

    public double getAtlChangePercentage() {
        return atlChangePercentage;
    }

    public void setAtlChangePercentage(double atlChangePercentage) {
        this.atlChangePercentage = atlChangePercentage;
    }

    public Date getAtlDate() {
        return atlDate;
    }

    public void setAtlDate(Date atlDate) {
        this.atlDate = atlDate;
    }

    public String getRoi() {
        return roi;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Coin coin = (Coin) o;
        return Double.compare(currentPrice, coin.currentPrice) == 0 && marketCap == coin.marketCap && marketCapRank == coin.marketCapRank && fullyDilutedValuation == coin.fullyDilutedValuation && totalVolume == coin.totalVolume && Double.compare(high24h, coin.high24h) == 0 && Double.compare(low24h, coin.low24h) == 0 && Double.compare(priceChange24h, coin.priceChange24h) == 0 && Double.compare(priceChangePercentage24h, coin.priceChangePercentage24h) == 0 && marketCapChange24h == coin.marketCapChange24h && Double.compare(marketCapChangePercentage24h, coin.marketCapChangePercentage24h) == 0 && Double.compare(circulatingSupply, coin.circulatingSupply) == 0 && Double.compare(totalSupply, coin.totalSupply) == 0 && Double.compare(maxSupply, coin.maxSupply) == 0 && Double.compare(ath, coin.ath) == 0 && Double.compare(athChangePercentage, coin.athChangePercentage) == 0 && Double.compare(atl, coin.atl) == 0 && Double.compare(atlChangePercentage, coin.atlChangePercentage) == 0 && Objects.equals(id, coin.id) && Objects.equals(symbol, coin.symbol) && Objects.equals(name, coin.name) && Objects.equals(image, coin.image) && Objects.equals(athDate, coin.athDate) && Objects.equals(atlDate, coin.atlDate) && Objects.equals(roi, coin.roi) && Objects.equals(lastUpdated, coin.lastUpdated);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(symbol);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(image);
        result = 31 * result + Double.hashCode(currentPrice);
        result = 31 * result + Long.hashCode(marketCap);
        result = 31 * result + marketCapRank;
        result = 31 * result + Long.hashCode(fullyDilutedValuation);
        result = 31 * result + Long.hashCode(totalVolume);
        result = 31 * result + Double.hashCode(high24h);
        result = 31 * result + Double.hashCode(low24h);
        result = 31 * result + Double.hashCode(priceChange24h);
        result = 31 * result + Double.hashCode(priceChangePercentage24h);
        result = 31 * result + Long.hashCode(marketCapChange24h);
        result = 31 * result + Double.hashCode(marketCapChangePercentage24h);
        result = 31 * result + Double.hashCode(circulatingSupply);
        result = 31 * result + Double.hashCode(totalSupply);
        result = 31 * result + Double.hashCode(maxSupply);
        result = 31 * result + Double.hashCode(ath);
        result = 31 * result + Double.hashCode(athChangePercentage);
        result = 31 * result + Objects.hashCode(athDate);
        result = 31 * result + Double.hashCode(atl);
        result = 31 * result + Double.hashCode(atlChangePercentage);
        result = 31 * result + Objects.hashCode(atlDate);
        result = 31 * result + Objects.hashCode(roi);
        result = 31 * result + Objects.hashCode(lastUpdated);
        return result;
    }
}
