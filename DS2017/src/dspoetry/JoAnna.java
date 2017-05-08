package dspoetry;

public class JoAnna {

	public static void main(String[] args) {

		String sound = "IH NG";
		Poet.setVocab("SourceTexts/Screenplay-Inception.txt");
		String line = "";
		//line = Poet.writeLineWithMeter("0101010101");
		
		for (int i = 0; i < 4; i++) {
			line = Poet.writeLineWithMeter("010101")+" ";
			for(Word w : Poet.wordList){
				if(w.endsWith(sound) && Math.random() < 0.3){
					line+=w.word;
					break;
				}
			}
			System.out.println(line);
		}
		
	}
}