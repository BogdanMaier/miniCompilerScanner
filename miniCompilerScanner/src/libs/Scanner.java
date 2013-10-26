package libs;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Scanner {
	private String sourceCode;
	private Map<Integer, String> tokenizedCode;
	private Container cont;
	
	public Scanner(String sourceCode){
		this.sourceCode = sourceCode;
		tokenizedCode = new TreeMap<>();
		cont = new Container();
	}
	
	public void tokenize(){
//		StringTokenizer st = new StringTokenizer(this.sourceCode, "\\s+");
		String[] tokens  = sourceCode.split("\\s+");
		int counter = 0;
		for (String string : tokens) {
			tokenizedCode.put(counter, string);
			counter++;
		}
		
		
		
//		while (st.hasMoreElements()) {
//			String token= (String) st.nextElement();
//			tokenizedCode.put(counter, token.trim());
//			counter++;
//		}
		System.out.println("	-- Code --" );
		for(Map.Entry<Integer, String> entry : tokenizedCode.entrySet()) {
			System.out.println(entry.getValue());
		}
				
	}
	
	public void putTokens(){
		for (String toBeChecked : tokenizedCode.values()) {
			cont.addNewElement(toBeChecked);
		}
		cont.printMaps();
	}

}
