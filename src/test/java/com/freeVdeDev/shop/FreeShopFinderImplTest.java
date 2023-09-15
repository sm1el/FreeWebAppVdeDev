package com.freeVdeDev.shop;

//uTILISATION DE jUNIT 5
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FreeShopFinderImplTest {

    @Test
    public void testFindOrangeShopWithMobileAvailable() {
        // Arrange
        FreeShopFinderImpl shopFinder = new FreeShopFinderImpl("FreeWebAppVdeDev/src/main/resources/free_shop.csv");

        // Act avec des coordonnées et un mobile donnés
        String shop = shopFinder.findFreeShopWithMobileAvailable(-1.51815, 43.49507, "sunusng");

        // Assert
        Assertions.assertEquals("[Free] 64 Anglet (Avenue Belle Marion)", shop);
    }
}
