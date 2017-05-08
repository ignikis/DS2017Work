package dspoetry;

public class MaryYeabkal {

	public static void main(String[] args) {
		Poet.setVocab("SourceTexts/MarkTwain.txt");
		String sound = "AH N D";
		int wordcount=0, linecount=0;
		Word w = new Word();
		for(int i=0;;i++)
		{
			w= Poet.wordList[i];
			if(wordcount!=(7-linecount))
			{
				System.out.print(w.word + " ");
				wordcount++;
			}
			else
			{
				if(w.endsWith(sound))
				{
					System.out.println(w.word);
					wordcount=0;
					linecount++;
				}
				else
					continue;
			}
			if(linecount==7)
				break;
			}
	}
}