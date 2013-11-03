package libs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class Container {
	private static int stCounter = 1;
	private static int constCounter = 1;
	private CsvLoaderToMap csl;
	private ArrayList<String[]> pif;
	private SortedMap<String, String> st;
	private Map<String, String> constants;
	private Map<String, String> def;

	public Container() {
		def = new TreeMap<String, String>();
		st = new TreeMap<String, String>();
		pif = new ArrayList<String[]>();
		constants = new TreeMap<String, String>();
		csl = new CsvLoaderToMap();
		csl.readCsv("src//libs//defaults.csv", def);
	}

	public boolean addNewElement(String toBeChecked) {
		// if in defaults table
		if (checkIfDef(toBeChecked)) {
			for (Map.Entry<String, String> e : def.entrySet()) {
				if (toBeChecked.equals(e.getValue())) {
					String[] t = new String[2];
					t[0] = e.getKey();
					t[1] = "-1";
					pif.add(t);
					return true;
				}
			}
		}
		// if in Symbol table
		else if (checkIfSt(toBeChecked)) {
			System.out.println("- found symbol table ");
			for (Map.Entry<String, String> e : st.entrySet()) {
				if (toBeChecked.equals(e.getValue())) {
					String[] t = new String[2];
					t[0] = "0";
					t[1] = e.getKey();
					pif.add(t);
					return true;
				}
			}
		}

		// if in constants
		else if (checkIfConstants(toBeChecked)) {
			System.out.println("- found constants ");
			for (Map.Entry<String, String> e : constants.entrySet()) {
				if (toBeChecked.equals(e.getValue())) {
					String[] t = new String[2];
					t[0] = "1";
					t[1] = e.getKey();
					pif.add(t);
					return true;
				}
			}
		}

		else {
			try {
				Integer.parseInt(toBeChecked.trim());
				st.put(Integer.toString(stCounter), toBeChecked);
				String[] t = new String[2];
				t[0] = "0";
				t[1] = st.lastKey();
				pif.add(t);
				stCounter++;
				return true;
			} catch (NumberFormatException e) {
				if (toBeChecked.length() > 9) {
					System.err
							.println("::ERROR: --- Lenght is to big, 8 lenght max for identifier : "
									+ toBeChecked);
					System.exit(1);
				}
				constants.put(Integer.toString(constCounter), toBeChecked);
				String[] t = new String[2];
				t[0] = "1";
				t[1] = Integer.toString(constCounter);
				pif.add(t);
				constCounter++;
				return true;
			}
		}
		return false;
	}

	private boolean checkIfDef(String toBeChecked) {
		return def.containsValue(toBeChecked);
	}

	private boolean checkIfSt(String toBeChecked) {
		return st.containsValue(toBeChecked);
	}

	private boolean checkIfConstants(String toBeChecked) {
		return constants.containsValue(toBeChecked);
	}

	public void printMaps() {
		System.out.println("DEFS");
		System.out.println(def.toString());
		System.out.println("SYMB TABLE");
		System.out.println(st.toString());
		System.out.println("CONSTANTS");
		System.out.println(constants.toString());
		System.out.println("PIF");
		for (String[] e : pif) {
			System.out.println(e[0] + " " + e[1]);
		}
	}
}