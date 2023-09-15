package com.freeVdeDev.shop;

//uTILISATION DE jUNIT 5
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FreeShopApplicationTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testMainApplication() {

        // Arrange
        FreeShopApplication.main(new String[]{});

        // Act
        String consoleOutput = outputStreamCaptor.toString().trim();

        // Assert
        Assertions.assertTrue(consoleOutput.contains("La boutique la plus proche est:"));

    }
}
