package com.freeVdeDev.shop;

/**
 * 
 * @author Abdelhadi
 */
public class Line {

	private String longitude;
	private String latitude;
	private String shopDescription;
	private String sunusng;
	private String ipom;
	private String weiwei;

	public Line() {
		super();
	}

	public Line(String longitude, String latitude, String shopDescription, String sunusng, String ipom, String weiwei) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.shopDescription = shopDescription;
		this.sunusng = sunusng;
		this.ipom = ipom;
		this.weiwei = weiwei;
	}

	public Line(String... fields) {
		this.longitude = fields[0];
		this.latitude = fields[1];
		this.shopDescription = fields[2];
		this.sunusng = fields[3];
		this.ipom = fields[4];
		this.weiwei = fields[5];
	}

	public String getlongitude() {
		return longitude;
	}

	public void setlongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getlatitude() {
		return latitude;
	}

	public void setlatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getshopDescription() {
		return shopDescription;
	}

	public void setshopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}

	public String getsunusng() {
		return sunusng;
	}

	public void setsunusng(String sunusng) {
		this.sunusng = sunusng;
	}

	public String getipom() {
		return ipom;
	}

	public void setipom(String ipom) {
		this.ipom = ipom;
	}

	public String getweiwei() {
		return weiwei;
	}

	public void setweiwei(String weiwei) {
		this.weiwei = weiwei;
	}

}
