package dspoetry;

public class AmenaMadeleine{

	public static void main(String[] args) {
		String sound1 = "AH N T";
		String sound2 = "ER";
		String sound3 = "AH N";
		String sound4 = "IH NG";
		Poet.setVocab("SourceTexts/isaacasimov.txt");
			
			
		int counter = 0;
		for(Word w : Poet.wordList){
			if(w.partOfSpeech == "N" && w.endsWith(sound1)){
				System.out.print(w.word + " ");
				counter++;
				if(counter == 3){
					break;
				}
			}
			
			/*if(w.partOfSpeech == "A" && w.endsWith(sound1)){
				System.out.print(w.word + " " + "\n");
				counter++;
				if(counter == 1){
					break;
				}
			}*/
				}
		
		
		/*for(Word w : Poet.wordList){
			
			if(w.partOfSpeech == "V" && w.endsWith(sound2)){
				System.out.print(w.word + " ");
				counter++;
				if(counter == 3){
					break;
				}
			}
			
			if(w.partOfSpeech == "v" && w.endsWith(sound2)){
				System.out.print(w.word + " ");
				counter++;
				if(counter == 1){
					break;
				}
			}
				}
		
		for(Word w : Poet.wordList){
			
			if(w.partOfSpeech == "t" && w.endsWith(sound3)){
				System.out.print(w.word + " ");
				counter++;
				if(counter == 3){
					break;
				}
			}
			
			if(w.partOfSpeech == "A" && w.endsWith(sound3)){
				System.out.print(w.word + " ");
				counter++;
				if(counter == 1){
					break;
				}
			}
				}
	
		for(Word w : Poet.wordList){
			
			if(w.partOfSpeech == "v" && w.endsWith(sound4)){
				System.out.print(w.word + " ");
				counter++;
				if(counter == 3){
					break;
				}
			}
			
			if(w.partOfSpeech == "N" && w.endsWith(sound4)){
				System.out.print(w.word + " ");
				counter++;
				if(counter == 1){
					break;
				}
			}
				} */
	}
}
