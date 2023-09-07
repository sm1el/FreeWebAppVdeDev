package com.freeVdeDev.shop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShopLocator {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/free_shop.csv"));
            String line;
            List<List<String>> data = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                data.add(Arrays.asList(values));
            }
            br.close();
            // Get the rows where the last three elements are not zero
            List<List<String>> filteredData = new ArrayList<>();
            for (List<String> row : data) {
                if (!row.get(3).equals("0") && !row.get(4).equals("0") && !row.get(5).equals("0")) {
                    filteredData.add(row);
                }
            }
            // Create variables for latitude and longitude of the user's position
            double latitude_mine = 43.49507;
            double longitude_mine = -1.51815;
            // Create a list to store distances and addresses
            List<String> addresses = new ArrayList<>();
            for (List<String> row : filteredData) {
                try {
                    double latitude_store = Double.parseDouble(row.get(1));
                    double longitude_store = Double.parseDouble(row.get(0));
                    double distance = haversineDistance(latitude_mine, longitude_mine, latitude_store, longitude_store);
                    addresses.add(row.get(2) + " (Distance: " + distance + " km)");
                } catch (NumberFormatException e) {
                    // Gérer les valeurs non valides ici, par exemple, ignorer cette ligne ou la journaliser.
                    System.err.println("Erreur de conversion de latitude ou de longitude : " + e.getMessage());
                }
            }
            // Ask the user for the brand of phone they want
            Scanner scanner = new Scanner(System.in);
            System.out.print("Veuillez saisir la marque de téléphone que vous recherchez (sunusng, ipom, ou weiwei) : ");
            String brand = scanner.nextLine().toLowerCase();
            // Find the stores that sell the desired brand
            List<String> matchingStores = new ArrayList<>();
            for (int i = 0; i < filteredData.size(); i++) {
                if (filteredData.get(i).get(getBrandIndex(brand)).equals("0")) {
                    continue; // Skip stores that don't have the desired brand
                }
                matchingStores.add(addresses.get(i));
            }
            // Find the closest store among the matching stores
            if (!matchingStores.isEmpty()) {
                int minIndex = 0;
                for (int i = 1; i < matchingStores.size(); i++) {
                    double distance1 = Double.parseDouble(matchingStores.get(i).split("Distance: ")[1].split(" km")[0]);
                    double distance2 = Double.parseDouble(matchingStores.get(minIndex).split("Distance: ")[1].split(" km")[0]);
                    if (distance1 < distance2) {
                        minIndex = i;
                    }
                }
                // Print the address of the closest store that sells the desired brand
                System.out.println("Le magasin le plus proche qui vend des téléphones " + brand + " est : " + matchingStores.get(minIndex));
            } else {
                System.out.println("Aucun magasin ne vend des téléphones de la marque " + brand + ".");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convertir les degrés en radians
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // Rayon de la Terre en kilomètres
        double earthRadius = 6371.0;

        // Différences de latitude et de longitude
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        // Formule haversine
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance en kilomètres
        double distance = earthRadius * c;

        return distance;
    }

    public static int getBrandIndex(String brand) {
        switch (brand) {
            case "sunusng":
                return 3;
            case "ipom":
                return 4;
            case "weiwei":
                return 5;
            default:
                return -1; // Invalid brand
        }
    }
}
