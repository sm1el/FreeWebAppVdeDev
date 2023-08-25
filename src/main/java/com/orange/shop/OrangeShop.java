//package com.orange.shop;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class OrangeShop {
//
//    private double longitude;
//    private double latitude;
//    private String shopDescription;
//    private Map<String, Integer> mobileStocks;
//
//    public OrangeShop(double longitude, double latitude, String shopDescription) {
//        this.longitude = longitude;
//        this.latitude = latitude;
//        this.shopDescription = shopDescription;
//        this.mobileStocks = new HashMap<>();
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public String getShopDescription() {
//        return shopDescription;
//    }
//
//    public void setStock(String mobileName, int stock) {
//        mobileStocks.put(mobileName, stock);
//    }
//
//    public int getStockForMobile(String mobileName) {
//        return mobileStocks.getOrDefault(mobileName, 0);
//    }
//}
