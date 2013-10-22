package libs;

import java.util.Map;
import java.util.TreeMap;

public class Container {
	CsvLoaderToMap csl = new CsvLoaderToMap();
	private Map<String, String> pif;
	private Map<String, String> st;
	private Map<String, String> def;
	
	
	public Container(){
		def = new TreeMap<String, String>();
		st = new TreeMap<String, String>();
		pif = new TreeMap<String, String>();
		csl.readCsv("src//libs//defaults.csv", def);
	}
}