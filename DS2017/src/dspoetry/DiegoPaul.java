package dspoetry;

public class DiegoPaul {
	public static void main(String[] args) {
		Poet.setVocab("SourceTexts/iTunesTermsAndConditions.txt");
		int words = 0;
		int lines = 0;
		System.out.println("");
		for(Word w : Poet.wordList){
			if(iambic(w) && Math.random() < 0.2){
				System.out.print(w.word.toLowerCase() + " ");
				words++;
				if(words == 4){
					System.out.println("\n");
					words = 0;
					lines++;
				}
				if(lines == 8){
					break;
				}
			}
		}
	}
	/**
	 * Tell whether the given word is a single iamb
	 * @param w a Word object
	 * @return boolean, true if w is an iamb, false if it is not
	 */
	static boolean iambic(Word w){
		int[] m = w.meter;
		if(m == null){
			return false;
		}
		return m.length == 2 && m[0] == 0 && m[1] != 0;
	}
}
