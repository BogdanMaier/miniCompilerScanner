package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			String sourceCode = new Scanner(new File("exampleCode.mb")).useDelimiter("\\Z").next();
			libs.Scanner scanner = new libs.Scanner(sourceCode);
			scanner.tokenize();
		} catch (FileNotFoundException e) {
			System.out.println(":: File not found !");
		}
		
	}

}
