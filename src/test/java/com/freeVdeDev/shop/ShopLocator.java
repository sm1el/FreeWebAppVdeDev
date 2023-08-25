package com.freeVdeDev.shop;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.List;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ShopLocator {

    public static void main(String[] args) throws IOException {
        double latitude_mine = 48.907373;
        double longitude_mine = 2.341175;

        List<Shop> shops = CSVShopReader.readShopsFromCSV("src/main/resources/free_shop.csv");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a phone brand: sunusng, ipom, or weiwei");
        String chosenBrand = scanner.nextLine().toLowerCase();

        Shop nearestShop = findNearestShop(latitude_mine, longitude_mine, chosenBrand, shops);

        System.out.println("Nearest shop details:");
        System.out.println("Brand: " + nearestShop.getBrand());
        System.out.println("Address: " + nearestShop.getAddress());
        System.out.println("Distance: " + nearestShop.getDistance() + " km");
    }

    public static Shop findNearestShop(double myLatitude, double myLongitude, String brand, List<Shop> shops) {
        Shop nearestShop = null;
        double minDistance = Double.MAX_VALUE;

        for (Shop shop : shops) {
            if (shop.getBrand().equals(brand)) {
                double distance = haversineDistance(myLatitude, myLongitude, shop.getLatitude(), shop.getLongitude());
                shop.setDistance(distance);

                if (distance < minDistance) {
                    minDistance = distance;
                    nearestShop = shop;
                }
            }
        }

        return nearestShop;
    }

    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);


        double earthRadius = 6371.0;


        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;


        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));


        double distance = earthRadius * c;

        return distance;
    }

}

