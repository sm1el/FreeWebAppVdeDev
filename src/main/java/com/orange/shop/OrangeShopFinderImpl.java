package com.orange.shop;

import java.util.ArrayList;
import java.util.List;

public class OrangeShopFinderImpl implements OrangeShopFinder {

    private List<Line> shopLines;

    public OrangeShopFinderImpl(String csvFilePath) {
        FileShopReader fileShopReader = new FileShopReader();
        shopLines = fileShopReader.setAllLine();
    }

    @Override
    public String findOrangeShopWithMobileAvailable(double longitude, double latitude, String nameMobile) {
        // Convert Line objects to OrangeShop objects
        List<OrangeShop> orangeShops = convertLinesToOrangeShops(shopLines);

        OrangeShop closestShop = null;
        double minDistance = Double.MAX_VALUE;

        for (OrangeShop shop : orangeShops) {
            double distance = calculateDistance(longitude, latitude, shop.getLongitude(), shop.getLatitude());
            if (distance < minDistance && shop.getStockForMobile(nameMobile) > 0) {
                closestShop = shop;
                minDistance = distance;
            }
        }

        if (closestShop != null) {
            return closestShop.getShopDescription();
        } else {
            return "Aucune boutique trouvée avec un stock disponible pour le mobile spécifié.";
        }
    }

    private List<OrangeShop> convertLinesToOrangeShops(List<Line> lines) {
        List<OrangeShop> orangeShops = new ArrayList<>();

        for (Line line : lines) {
            double longitude = Double.parseDouble(line.getField1());
            double latitude = Double.parseDouble(line.getField2());
            String description = line.getField3();
            int stockSunusng = Integer.parseInt(line.getField4());
            int stockIpom = Integer.parseInt(line.getField5());
            int stockWeiwei = Integer.parseInt(line.getField6());

            OrangeShop shop = new OrangeShop(longitude, latitude, description);
            shop.setStock("sunusng", stockSunusng);
            shop.setStock("ipom", stockIpom);
            shop.setStock("weiwei", stockWeiwei);

            orangeShops.add(shop);
        }

        return orangeShops;
    }

    private double calculateDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double earthRadius = 6371.0; // Rayon moyen de la Terre en kilomètres

        // Conversion des coordonnées en radians
        double lon1Rad = Math.toRadians(longitude1);
        double lat1Rad = Math.toRadians(latitude1);
        double lon2Rad = Math.toRadians(longitude2);
        double lat2Rad = Math.toRadians(latitude2);

        // Calcul des différences de latitude et de longitude
        double dLon = lon2Rad - lon1Rad;
        double dLat = lat2Rad - lat1Rad;

        // Formule de la distance Haversine
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance;
    }

}

