package gwt.jsChalleng.shared;

public class WordGenerator {
	public StringBuilder wordBuilder;

	public WordGenerator() {
		this("");
	}
	
	public WordGenerator(String s) {
		wordBuilder = new StringBuilder(s);
	}
	
	public void setWord(String word) {
		wordBuilder = new StringBuilder(word);
	}
	
	public StringBuilder nextWord() {
		return nextWord(wordBuilder.length()-1);
	}
	
	public StringBuilder nextWord(int position) {
		
		int charToHandle = wordBuilder.charAt(position);
		//if ((int)charToHandle>168) { //if arrive at last possible value
		if ((int)charToHandle>126) { //if arrive at last possible value
			if (position!=0) {
				return nextWord(position-1);
			} else {
				return generateBiggerWord();
			}
		}
		
		wordBuilder.setCharAt(position, (char)(charToHandle+1));
		for (int i = position+1; i < wordBuilder.length(); i++) {
			//reset char after
			wordBuilder.setCharAt(i, (char)32);
		}
		
		return wordBuilder;
	}

	private StringBuilder generateBiggerWord() {
		int newLen = wordBuilder.length()+1;
		
		wordBuilder = new StringBuilder();
		wordBuilder.setLength(newLen);
		
		for (int i = 0; i < newLen; i++) {
			wordBuilder.setCharAt(i, (char)32);
		}
		return wordBuilder;
	}
	
	public String getWord() {
		return wordBuilder.toString();
	}
}
