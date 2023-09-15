package com.freeVdeDev.shop;

//uTILISATION DE jUNIT 5


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class csvFileShopManipulationTest {

	@Test
	public void testName() throws Exception {
		csvFileShopManipulation fileReader = new csvFileShopManipulation();
		List<Line> setAllLine = fileReader.setAllLine();

		Assertions.assertEquals(setAllLine.size(), 226);
		Assertions.assertEquals(setAllLine.get(0).getField1(), "-1.51815");
		Assertions.assertEquals(setAllLine.get(0).getField2(), "43.49507");
		Assertions.assertEquals(setAllLine.get(0).getField3(), "[Free] 64 Anglet (Avenue Belle Marion)");
		Assertions.assertEquals(setAllLine.get(0).getField4(), "5");
		Assertions.assertEquals(setAllLine.get(0).getField5(), "7");
		Assertions.assertEquals(setAllLine.get(0).getField6(), "10");

	}
}
