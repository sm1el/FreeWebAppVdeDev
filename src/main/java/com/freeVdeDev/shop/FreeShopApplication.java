package com.freeVdeDev.shop;

public class FreeShopApplication {
    public static void main(String[] args) {
        String csvFilePath = "FreeWebAppVdeDev/src/main/resources/free_shop.csv";
        FreeShopFinder freeShopFinder = new FreeShopFinderImpl(csvFilePath);

        double longitude = -1.42988; // Valeur de longitude souhaitée 5.06200;43.93070
        double latitude = 46.66976; // Valeur de latitude souhaitée
        String mobileName = "sunusng"; // Nom du mobile recherché

        String closestShop = freeShopFinder.findFreeShopWithMobileAvailable(longitude, latitude, mobileName);

        System.out.println("La boutique la plus proche est: " + closestShop);
    }
}
