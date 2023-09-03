package com.freeVdeDev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ReadCSV {

	public List<LongLat> fromCsvToList() {
		// instancier un String de notre fichier csv (le chemin de notre fichier CSV)
		String csvFichierPath = "src\\main\\resources\\free_shop.csv";

		// Declarer une liste qui va prendre comme élément un classe LongLat
		List<LongLat> listeCSV = new ArrayList<>();

		// mettre un fileReader dans un BufferReader pour lire file plus rapidement
		try (BufferedReader br = new BufferedReader(new FileReader(csvFichierPath))) {
			String ligne;
			
			boolean skipFirstLigne = true; // Cette variable permettra de sauter la première ligne

			while ((ligne = br.readLine()) != null) { // tantque la ligne n'est pas vide not null

				// if true
				if (skipFirstLigne) {
					skipFirstLigne = false; // mettre a faux
					continue; // Saute la première ligne et on continue la lecture
				}

				// Divisez la ligne en éléments en utilisant le point-virgule comme séparateur
				String[] elements = ligne.split(";");

				// cast de chaque elements
				double longit = Double.parseDouble(elements[0]);
				double lat = Double.parseDouble(elements[1]);
				String adresse = elements[2].replaceAll("\\[.*?\\]\\s*", "");
				int sung = Integer.parseInt(elements[3]);
				int ipomm = Integer.parseInt(elements[4]);
				int weiw = Integer.parseInt(elements[5]);

				// appele du consructeur et instancier l'element
				LongLat unElement = new LongLat(longit, lat, adresse, sung, ipomm, weiw);

				// ajouter l'element de type objet de notre classe dans la list
				listeCSV.add(unElement);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listeCSV; // liste a retourner a la fin (une liste d'objets type longlat)
	}

}
