package libs;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Container {
	private static int stCounter = 1;
	private static int constCounter = 1;
	private CsvLoaderToMap csl;
	private Map<String, String> pif;
	private SortedMap<String, String> st;
	private Map<String, String> constants;
	private Map<String, String> def;
	
	
	public Container(){
		def = new TreeMap<String, String>();
		st = new TreeMap<String, String>();
		pif = new TreeMap<String, String>();
		constants =  new TreeMap<String, String>();
		csl = new CsvLoaderToMap();
		csl.readCsv("src//libs//defaults.csv", def);
	}
	
	public boolean addNewElement(String toBeChecked){
		// if in defaults table
		if (checkIfDef(toBeChecked)) {
			for(Map.Entry<String, String> e : def.entrySet()) {
				if (toBeChecked.equals(e.getValue())) {
					pif.put(e.getKey(),"-1");
					return true;
				}
			}
		}
		// if in Symbol table
		else if (checkIfSt(toBeChecked)) {
			for(Map.Entry<String, String> e : st.entrySet()){
				if (toBeChecked.equals(e.getValue())) {
					pif.put("0",e.getKey());
					return true;
				}
			}
		}

		// if in constants
		else if (checkIfConstants(toBeChecked)) {
			for(Map.Entry<String, String> e : constants.entrySet()){
				if (toBeChecked.equals(e.getValue())) {
						pif.put("1", e.getKey());
					return true;
				}
			}
		}
		
		else{
			try {
				Integer.parseInt(toBeChecked);
				st.put(Integer.toString(stCounter), toBeChecked);
				pif.put(Integer.toString(stCounter), "0");
				stCounter++;
				return true;
			} catch (NumberFormatException e) {
				constants.put(Integer.toString(constCounter), toBeChecked);
				pif.put(Integer.toString(stCounter), "1");
				constCounter++;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkIfDef(String toBeChecked){
		return def.containsValue(toBeChecked);
	}
	
	private boolean checkIfSt(String toBeChecked){
		return st.containsValue(toBeChecked);
	}
	
	private boolean checkIfConstants(String toBeChecked){
		return constants.containsValue(toBeChecked);
	}
	
	public void printMaps(){
		System.out.println("DEFS");
		System.out.println(def.toString());
		System.out.println("SYMB TABLE");
		System.out.println(st.toString());
		System.out.println("CONSTANTS");
		System.out.println(constants.toString());
		System.out.println("PIF");
		System.out.println(pif);
	}
}