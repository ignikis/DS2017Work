package dspoetry;

public class Julian_David {

	private static String rhymingWord;
	private static String sound;
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String firstline = "The word, ";
		String secondLine = "Rhymes with, ";
		sound = "L ER";
		System.out.println(firstline + findRhymingWord(sound));
		System.out.println(secondLine + findRhymingWord(sound));

	}
	
	static String findRhymingWord (String sound){
		Poet.setVocab("SourceTexts/Ulysses.txt");
		int counter = 0;
		for(Word w1 : Poet.wordList){
			if(w1.endsWith(sound) && Math.random() < 0.3){
				counter ++;
				if (counter == 1)
					rhymingWord = w1.word;
				return rhymingWord;
			
			}
		}
		return rhymingWord;
	}

}
