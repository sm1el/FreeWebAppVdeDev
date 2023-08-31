package com.freeVdeDev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaCsv {
    public static List<String[]> fromCsvToList() {
		// instancier un String de notre fichier csv (le chemin de notre fichier CSV)
		// String csvFichierPath = "C:\\Users\\juba_\\OneDrive\\Bureau\\VILLAGE EMPLOI\\AXE_2\\ProjetVDE\\FreeWebAppVdeDev\\src\\main\\resources\\free_shop.csv";
        String csvFichierPath = "src\\main\\java\\com\\freeVdeDev\\ReaCsv.java";
		// Declarer une liste qui va prendre comme élément un tableau de String
		List<String[]> listeDeDonnees = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFichierPath))) {
			String ligne;
			while ((ligne = br.readLine()) != null) { // tantque la ligne n'est pas vide not null
				// Divisez la ligne en éléments en utilisant le point-virgule (;) comme séparateur
				String[] elements = ligne.split(";");
				listeDeDonnees.add(elements);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String[]> listeCSV = new ArrayList<>();
		listeCSV.addAll(listeDeDonnees.subList(1, listeDeDonnees.size())); // supprimer la 1ère ligne
		return listeCSV;
	}

	public static List<String> listLong(List<String[]> listeCSV) {
		List<String> tabLongitude = new ArrayList<>();

		for (int i = 0; i < listeCSV.size(); i++) {
			String[] uneLigne = listeCSV.get(i);
			tabLongitude.add(uneLigne[0]);
		}
		return tabLongitude;
	}

	public static List<String> listLat(List<String[]> listeCSV) {

		List<String> tabLatitude = new ArrayList<>();

		for (int i = 0; i < listeCSV.size(); i++) {
			String[] uneLigne = listeCSV.get(i);
			tabLatitude.add(uneLigne[1]);
		}
		return tabLatitude;
	}

	public static List<String> listShop(List<String[]> listeCSV) {

		List<String> tabShopDescription = new ArrayList<>();

		for (int i = 0; i < listeCSV.size(); i++) {
			String[] uneLigne = listeCSV.get(i);
			String str = uneLigne[2].replaceAll("\\[.*?\\]\\s*", ""); // supprimer "[orange ]' '"
//			System.out.println(str.charAt(0));
			tabShopDescription.add(str);
		}
		return tabShopDescription;
	}

	public static void main(String[] args) {

        fromCsvToList();
		List<String[]> listeDeDonnees = fromCsvToList();
		listLong(listeDeDonnees);
		listLat(listeDeDonnees);
		listShop(listeDeDonnees);

		// Maintenant, vous avez vos données CSV dans tableauDeDonnees
		// Vous pouvez parcourir le tableau et accéder aux éléments comme suit :
//		for (String[] ligne : listeDeDonnees) {
//			for (String element : ligne) {
//				System.out.print(element + "\t"); // Affiche les éléments du tableau
//			}
//			System.out.println(); // Passage à la ligne suivante
//		}

//		for (String s : tabShopDescription) {
//			System.out.println("s = "+ s);
//		}

	}
}
