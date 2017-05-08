package dspoetry;

class PoemTest{
    public static void main(String[] args){
		Poet.setVocab("SourceTexts/MarkTwain.txt");
		String psonnet = Poet.writePetrarchanSonnet();
		// String tester = Poet.writeLineWithMeterAndAlliterationAndRhymesWith("010101010101", "FLY", "CLOSE");
		System.out.println(psonnet.toLowerCase());	
    }
}
