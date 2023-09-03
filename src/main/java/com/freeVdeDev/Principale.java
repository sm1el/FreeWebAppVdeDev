package com.freeVdeDev;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principale {

	public static void main(String[] args) {

		// lire notre fichier csv
		ReadCSV readList = new ReadCSV();

		// transformer notre fichier en liste
		List<LongLat> listeCsv = readList.fromCsvToList();

		// scanner
		Scanner sc = new Scanner(System.in);

		// Afficher un message d'accueil
		System.out.println("Bienvenue dans nos magasins Orang shops");
		System.out.println();

		// Afficher les options de modèle de telephone
		System.out.println("Voici les types de modèles disponibles :");
		System.out.println("Tapez 1 pour le Modèle : Sunsung");
		System.out.println("tapez 2 pour le Modèle : Ipom");
		System.out.println("tapez 3 pour le Modèle : Weiwei");
		System.out.println();

		System.out.print("Veuillez choisir le numéro du modèle que vous préférez : ");
		int choixClient = sc.nextInt();

		// conditions
		if (choixClient == 1) {
			System.out.println("\nVous avez choisi le Modèle Sunsung");
			System.out.println("on va vous diriger vers notre magasin le plus proche");

			// stream qui filtre > 0 (tel est disponible)
			listeCsv = listeCsv.stream().filter(liste -> liste.getsunusng() > 0).collect(Collectors.toList());

			System.out.println("# ******************************************************* # ");

		} else if (choixClient == 2) {
			System.out.println("\nVous avez choisi le Modèle Ipom");
			System.out.println("on va vous diriger vers notre magasin le plus proche");

			listeCsv = listeCsv.stream().filter(liste -> liste.getipom() > 0).collect(Collectors.toList());

			System.out.println("# ******************************************************* # ");

		} else if (choixClient == 3) {
			System.out.println("\nVous avez choisi le Modèle Ipom");
			System.out.println("on va vous diriger vers notre magasin le plus proche");

			listeCsv = listeCsv.stream().filter(liste -> liste.getweiwei() > 0).collect(Collectors.toList());

			System.out.println("# ******************************************************* # ");

		} else {
			System.out
					.println("\nOn va vous rediriger vers notre boutique la plus proche, à défaut de rupture de stock");

			System.out.println("# ******************************************************* # ");
		}
		
        // Videz le tampon du scanner sc après nextInt() en-dessus
        sc.nextLine();

        // demander au client son adresse valide
        System.out.println("\nEntrez une adresse valide exemple : \n\t78 rue du Docteur Bauer, 93400 Saint-Ouen, France");
        
        String adresse = sc.nextLine();

		// Fermer le scanner
		sc.close();
		
		AddressToLatLng coordonnes = AddressToLatLng.getGPS(adresse);
//		AddressToLatLng coordonnes = AddressToLatLng.getGPS();
		
		/*
		 * Adresse valide exemple : respecter les majuscule et minuscule
		 * 
		 * 4 allee de la coudraie, 77400 Pomponne, France
		 * 78 rue du Docteur Bauer, 93400 Saint-Ouen, France
		 * 5 Avenue Anatole France, 75007 Paris, France
		 * Musée du Louvre, Rue de Rivoli, 75001 Paris, France
		 * Mont Saint-Michel, 50170 Le Mont-Saint-Michel, France
		 * Les Calanques, Marseille, France
		 * 
		 */
		
		double fixedLon = coordonnes.getLongitude();
		double fixedLat = coordonnes.getLatitude();
		
		if (fixedLat == 0 || fixedLon == 0) {
			fixedLon = 2.341092; // Longitude du point fixe en degrés VDE
			fixedLat = 48.907462; // Latitude du point fixe en degrés VDE
		}
		
		// on fixe notre distance minimal a la valeur max d'un double par défault
		double shortestDistance = Double.MAX_VALUE;
		LongLat closestShop = null; // instancier a null

		// parcourir notre liste avec les filtre
		for (LongLat unElement : listeCsv) {
			// appeler la methode static haversin
			double distance = LongLat.calculateHaversineDistance(fixedLat, fixedLon, unElement.getlatitude(),
					unElement.getlongitude());
			if (distance < shortestDistance) { // boucle pour trouver la distance minimal
				shortestDistance = distance;
				closestShop = unElement;
			}
		}
		System.out.println("\nLe point le plus proche est à une distance de " + shortestDistance + " kilomètres.");
		System.out.println("Coordonnées du point le plus proche : Latitude = " + closestShop.getlatitude()
				+ ", Longitude = " + closestShop.getlongitude());
		System.out.println();
		System.out.println(
				"le magasin le plus proche se trouve a l'adresse suivante : " + closestShop.getshopDescription());
	}

}
