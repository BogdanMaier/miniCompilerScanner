package libs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class CsvLoaderToMap {
	BufferedReader br = null;
	String line = "";

	public void readCsv(String path, Map<String, String> map) {
		try {

			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				map.put(data[1], data[0]);
				System.out.println("DATA: " + Arrays.deepToString(data));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}
}
