package com.freeVdeDev;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.*;
import java.util.ArrayList;
import java.util.List;

public class AddressToLatLng {

	private double latitude;
	private double longitude;
	
	public AddressToLatLng() {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public AddressToLatLng(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Latitude: " + latitude + ", Longitude: " + longitude;
	}

	/*
	 * Si vous ne souhaitez pas utiliser une clé API pour accéder aux services de
	 * géocodage, vous pouvez utiliser une alternative open source comme Geonames ou
	 * Nominatim. Voici un exemple de code Java pour utiliser Nominatim, un service
	 * de géocodage open source basé sur OpenStreetMap
	 */
	public static AddressToLatLng getGPS(String address) {
//	public static AddressToLatLng getGPS() {

//		String address1 = "4 allee de la coudraie, 77400 Pomponne, France";
//		String address = "78 rue du Docteur Bauer, 93400 Saint-Ouen, France";
		
		String encodedAddress = "";
		try {
			encodedAddress = URLEncoder.encode(address, "UTF-8");
			System.out.println("encodedAddress : "+ encodedAddress);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		AddressToLatLng coordonnees = new AddressToLatLng();

		try {
			HttpClient httpClient = HttpClients.createDefault();
			String nominatimUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + encodedAddress;
			HttpGet httpGet = new HttpGet(nominatimUrl);
			String response = EntityUtils.toString(httpClient.execute(httpGet).getEntity());

			JSONArray resultsArray = new JSONArray(response);
			if (resultsArray.length() > 0) {
				JSONObject firstResult = resultsArray.getJSONObject(0);
				double latit = firstResult.getDouble("lat");
				double longit = firstResult.getDouble("lon");

				coordonnees.setLatitude(latit);
				coordonnees.setLongitude(longit);

//				System.out.println("Latitude : " + latit);
//				System.out.println("Longitude : " + longit);
			} else {
				System.out.println("Aucun résultat trouvé pour cette adresse.");
			}
			System.out.println(coordonnees.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return coordonnees;

	}

}
