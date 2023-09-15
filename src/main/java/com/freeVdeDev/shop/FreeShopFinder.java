package com.freeVdeDev.shop;

/**
 * Recherche de boutiques Orange.
 * 
 * Le point d'entr�e doit impl�menter cette interface.
 * 
 * 
 */
public interface FreeShopFinder {

	String findFreeShopWithMobileAvailable(double longitude, double latitude, String nameMobile);
}
