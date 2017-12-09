package dspoetry;

class PoemTest{
    public static void main(String[] args){
		Poet.setVocab("SourceTexts/JaneAusten.txt");
		/**
		DumbPoem dp = new DumbPoem();
		String p = dp.generatePoem();
		System.out.println("Here's a dumb poem for you:\n" + p);
		
		
		String poem = Poet.writeLineWithMeter("0101010101");
		System.out.println(poem);
		poem = Poet.writeLineWithMeterAndRhymeswith("0101010101", poem);
		System.out.println(poem);
		poem = Poet.writeLineWithMeter("0101010101");
		System.out.println(poem);
		poem = Poet.writeLineWithMeterAndRhymeswith("0101", poem);
		System.out.println(poem);	
		*/
		//String sonnet = Poet.writeSonnet();
		//System.out.println(sonnet);

		System.out.println(Poet.matchPhonemes("HAPPY", 3));
		
    }
}
