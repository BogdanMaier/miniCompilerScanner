package libs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Scanner {
	private String sourceCode;
	private Map<Integer, String> tokenizedCode;
	private Container cont;

	public Scanner(String sourceCode) {
		this.sourceCode = sourceCode;
		tokenizedCode = new TreeMap<>();
		cont = new Container();
	}

	public void tokenize() {
		int tokCounter = 0;
		int lineNr = 0;
		boolean needToConc = false;
		String tempTok = null;
		
		String sourceCode = fixSource();
		for (String l : sourceCode.split("\\r?\\n")) {
			String line = l.trim();
			int stringLine = -1;
			lineNr++;
			String[] st = line.split("\\s+|[,;{}]");
			ArrayList<String> list = new ArrayList<>(Arrays.asList(st));
			System.out.println("Line : " + Arrays.deepToString(st));
			for (String token : st) {
				if (needToConc && token.matches(".*\"")) {
					System.out.println("String at line " + lineNr + "  | End");
					tokenizedCode.put(tokCounter, tempTok+ " "+token.trim());
					tokCounter++;
					needToConc = false;
					continue;
				}
				if (needToConc &&  !token.matches(".*\"")) {
					System.out.println("String  at line " + lineNr + " | Middle");
					tempTok = new String(tempTok + " " + token);
				}
				
				if (token.matches("\".*(\")?")) {
					System.out.println("String  at line " + lineNr + " | Start");
					stringLine = lineNr;
					tempTok = new String(token);
					needToConc = true;
					continue;
				}
				
				if (token.matches("\".*\"")) {
					System.out.println("String  at line " + lineNr + " | Full");
					tokenizedCode.put(tokCounter, token.trim());
					tokCounter++;
					continue;
				}
				
				tokenizedCode.put(tokCounter, token.trim());
				tokCounter++;
			}
			if (needToConc) {
				System.err.println("::ERROR: --- Close String at line : " + stringLine);
				System.exit(1);
			}
		}
		System.out.println(tokenizedCode.toString());
	}

	public void putTokens() {
		for (String toBeChecked : tokenizedCode.values()) {
			cont.addNewElement(toBeChecked);
		}
		cont.printMaps();
	}

	private String fixSource(){
		char[] sourceCodeList = sourceCode.toCharArray();
		ArrayList<Character> rezSourceCodeList = new ArrayList<>();
		for (int i = 0; i < sourceCodeList.length; i++) {
			if (sourceCodeList[i] == '#') {
				while(sourceCodeList[i] != '\n'){
					i++;
				}
				continue;
			}
			if (sourceCodeList[i] == '=' && sourceCodeList[i+1] == '=') {
				rezSourceCodeList.add(' ');
				rezSourceCodeList.add(sourceCodeList[i]);
				rezSourceCodeList.add(sourceCodeList[i+1]);
				rezSourceCodeList.add(' ');
				i++;
				continue;
			}
			if (sourceCodeList[i] == '+' || sourceCodeList[i] == '-' || sourceCodeList[i] == '*' ||
				sourceCodeList[i] == '%' || sourceCodeList[i] == '='){
				rezSourceCodeList.add(new Character(' '));
				rezSourceCodeList.add(sourceCodeList[i]);
				rezSourceCodeList.add(' ');
				continue;
			}
			rezSourceCodeList.add(sourceCodeList[i]);
		}
		StringBuilder builder = new StringBuilder(rezSourceCodeList.size());
	    for(Character ch: rezSourceCodeList)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}
}
