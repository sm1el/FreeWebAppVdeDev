package com.freeVdeDev.shop;

//uTILISATION DE jUNIT 5
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FreeShopTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange avec des valeurs de test
        FreeShop shop = new FreeShop(1.2345, 2.3456, "[Free] 64 Anglet (Avenue Belle Marion)");

        // Act
        shop.setStock("sunusng", 5);
        shop.setStock("ipom", 7);
        shop.setStock("weiwei", 10);

        // Assert
        Assertions.assertEquals(1.2345, shop.getLongitude());
        Assertions.assertEquals(2.3456, shop.getLatitude());
        Assertions.assertEquals("[Free] 64 Anglet (Avenue Belle Marion)", shop.getShopDescription());
        Assertions.assertEquals(5, shop.getStockForMobile("sunusng"));
        Assertions.assertEquals(7, shop.getStockForMobile("ipom"));
        Assertions.assertEquals(10, shop.getStockForMobile("weiwei"));
    }
}
