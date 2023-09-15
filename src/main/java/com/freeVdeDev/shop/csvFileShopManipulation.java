package com.freeVdeDev.shop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class csvFileShopManipulation {

	private static final String NAME_FILE = "free_shop.csv";

	public List<Line> setAllLine() {
		List<String> allLines = readShopFile();

		removeHeaderLine(allLines);

		return createListShops(allLines);

	}

	private static List<String> readShopFile() {
		URI uri = null;
		List<String> lines = new ArrayList<String>();
		try {
			uri = ClassLoader.getSystemResource(NAME_FILE).toURI();
			lines = Files.readAllLines(Paths.get(uri));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

		return lines;
	}
	private static List<String> readShopFfff() throws URISyntaxException, IOException {
		URI urii = null;
		List<String> lines = new ArrayList<>();
		urii = ClassLoader.getSystemResource(NAME_FILE).toURI();
		lines = Files.readAllLines(Paths.get(urii));

		return lines;
	}

	private void removeHeaderLine(List<String> allLines) {
		allLines.remove(0);
	}

	private List<Line> createListShops(List<String> allLines) {
		return allLines.stream().map(line -> line.split(";")) //
				.map(line -> new Line(line[0], line[1], line[2], line[3], line[4], line[5])) //
				.collect(Collectors.toList());
	}

}
