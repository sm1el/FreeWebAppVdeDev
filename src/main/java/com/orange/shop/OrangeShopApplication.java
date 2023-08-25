package com.orange.shop;

public class OrangeShopApplication {
    public static void main(String[] args) {
        String csvFilePath = "orangeShop/src/main/resources/orange_shop.csv";
        OrangeShopFinder orangeShopFinder = new OrangeShopFinderImpl(csvFilePath);

        double longitude = 1.2345; // Valeur de longitude souhaitée
        double latitude = 2.3456; // Valeur de latitude souhaitée
        String mobileName = "sunusng"; // Nom du mobile recherché

        String closestShop = orangeShopFinder.findOrangeShopWithMobileAvailable(longitude, latitude, mobileName);

        System.out.println("Boutique la plus proche : " + closestShop);
    }
}
