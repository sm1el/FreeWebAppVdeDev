package com.freeVdeDev.shop;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FreeShopFinderImpl implements FreeShopFinder {

    private List<Line> shopLines;

    public FreeShopFinderImpl(String csvFilePath) {
        // Création d'un objet FileShopReader pour lire le fichier CSV et obtenir les lignes
        csvFileShopManipulation fileShopReader = new csvFileShopManipulation();
        shopLines = fileShopReader.setAllLine();
    }

    @Override
    public String findFreeShopWithMobileAvailable(double longitude, double latitude, String nameMobile) {
        List<FreeShop> orangeShops = convertLinesToFreeShops(shopLines);

        // Recherche de la boutique la plus proche avec un stock disponible pour le mobile spécifié
        Optional<FreeShop> closestShop = orangeShops.stream()
                .filter(shop -> shop.getStockForMobile(nameMobile) > 0)
                .min(Comparator.comparingDouble(shop -> calculateDistance(longitude, latitude, shop.getLongitude(), shop.getLatitude())));

        // Description de la boutique la plus proche, ou un message d'absence de boutique avec stock disponible
        return closestShop.map(FreeShop::getShopDescription)
                .orElse("Aucune boutique trouvée avec un stock disponible pour le mobile spécifié.");
    }


    private List<FreeShop> convertLinesToFreeShops(List<Line> lines) {
        return lines.stream()
                .map(line -> {
                    // Récupération des valeurs des champs de la ligne
                    double longitude = Double.parseDouble(line.getField1());
                    double latitude = Double.parseDouble(line.getField2());
                    String description = line.getField3();
                    int stockSunusng = Integer.parseInt(line.getField4());
                    int stockIpom = Integer.parseInt(line.getField5());
                    int stockWeiwei = Integer.parseInt(line.getField6());

                    //Création d'un objet OrangeShop avec les valeurs des champs
                    FreeShop shop = new FreeShop(longitude, latitude, description);
                    shop.setStock("sunusng", stockSunusng);
                    shop.setStock("ipom", stockIpom);
                    shop.setStock("weiwei", stockWeiwei);

                    return shop;
                })
                .collect(Collectors.toList());
    }


    private double calculateDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double earthRadius = 6371.0; // Rayon  de la Terre en kilomètres

        // Conversion en radians
        double lon1Rad = Math.toRadians(longitude1);
        double lat1Rad = Math.toRadians(latitude1);
        double lon2Rad = Math.toRadians(longitude2);
        double lat2Rad = Math.toRadians(latitude2);

        // Calcul des différences de latitude et de longitude
        double dLon = lon2Rad - lon1Rad;
        double dLat = lat2Rad - lat1Rad;

        //  distance Haversine
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance;
    }
}

