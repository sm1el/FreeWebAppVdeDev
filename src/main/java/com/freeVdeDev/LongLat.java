package com.freeVdeDev;

/*
 * 
 * @author Abdelhadi
 */
public class LongLat {

	// ******** Attributs ************
	private Double longitude;
	private Double latitude;
	private String shopDescription;
	private int sunusng;
	private int ipom;
	private int weiwei;

	
	// ************* Constructeru ***************
	public LongLat(Double longitude, Double latitude, String shopDescription, int sunusng, int ipom, int weiwei) {

		this.longitude = longitude;
		this.latitude = latitude;
		this.shopDescription = shopDescription;
		this.sunusng = sunusng;
		this.ipom = ipom;
		this.weiwei = weiwei;
	}

	// *********** Getter & setter *********
	public Double getlongitude() {
		return longitude;
	}

	public void setlongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getlatitude() {
		return latitude;
	}

	public void setlatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getshopDescription() {
		return shopDescription;
	}

	public void setshopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}

	public int getsunusng() {
		return sunusng;
	}

	public void setsunusng(int sunusng) {
		this.sunusng = sunusng;
	}

	public int getipom() {
		return ipom;
	}

	public void setipom(int ipom) {
		this.ipom = ipom;
	}

	public int getweiwei() {
		return weiwei;
	}

	public void setweiwei(int weiwei) {
		this.weiwei = weiwei;
	}

	// ***** methode afficher
	public String affiche() {
		return longitude + "  " + latitude + "  " + shopDescription + "  " + sunusng + "  " + ipom + "  " + weiwei;
	}

	// ******** methode toString() redefinie
	@Override
	public String toString() {
		return "long : " + longitude + " lat : " + latitude + " adresse : " + shopDescription + " sunsung : " + sunusng
				+ " ipom : " + ipom + " weiwei : " + weiwei;
	}
	
	// methode haversin
	public static double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
		double earthRadius = 6371; // Rayon moyen de la Terre en kilomètres

		// Conversion des latitudes et longitudes de degrés à radians
		double lat1Rad = Math.toRadians(lat1);
		double lon1Rad = Math.toRadians(lon1);
		double lat2Rad = Math.toRadians(lat2);
		double lon2Rad = Math.toRadians(lon2);

		// Différences de latitude et de longitude entre les deux points
		double dLat = lat2Rad - lat1Rad;
		double dLon = lon2Rad - lon1Rad;

		// Calcul de la formule haversine
		double a = Math.pow(Math.sin(dLat / 2), 2)
				+ Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dLon / 2), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = earthRadius * c;

		return distance;
	}

}
