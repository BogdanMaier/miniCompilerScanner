package libs;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Scanner {
	private String sourceCode;
	private Map<Integer, String> tokenizedCode;
	
	public Scanner(String sourceCode){
		this.sourceCode = sourceCode;
		tokenizedCode = new TreeMap<>();
	}
	
	public void tokenize(){
		StringTokenizer st = new StringTokenizer(this.sourceCode, "\\s+|\\.+|\\;+|\\(+|\\)+|\\\"+|\\:+|\\[+|\\]+");
		int counter = 0;
		while (st.hasMoreElements()) {
			String token= (String) st.nextElement();
			tokenizedCode.put(counter, token.trim());
			counter++;
		}
		System.out.println("	" + tokenizedCode.toString());
				
	}

}
