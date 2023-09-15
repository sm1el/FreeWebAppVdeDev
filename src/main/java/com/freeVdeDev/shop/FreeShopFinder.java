package com.freeVdeDev.shop;

/**
 * Recherche de boutiques Orange.
 * 
 * Le point d'entrée doit implémenter cette interface.
 * 
 * 
 */
public interface FreeShopFinder {

	String findFreeShopWithMobileAvailable(double longitude, double latitude, String nameMobile);
}
