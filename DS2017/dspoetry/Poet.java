package dspoetry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class of static methods and fields useful for writing poems
 */
class Poet{

	/**
	 * The pronunciations array will hold the cmupron.txt file
	 */
	static String[] pronunciations = null; // initializing it to null

	/**
	 * the wordList array will hold our Word objects. 
	 * Then each word will know its own pronunciation
	 */
	static Word[] wordList = null;         // our large array of Word objects

	/**
	 * maps words to the Word objects that contain that word
	 */
	static HashMap<String, Word> wordMap = null;

	/**
	 * vocab holds the name of the file we'll get words from
	 */
	private static String vocab = null;
	static BufferedReader vocabReader = null;

	/**
	 * Selects the first word from our wordMap that
	 * rhymes with the given phonemes
	 */
	static String getRW(String phonemes){
		for(String w : wordMap.keySet()){
			if(wordMap.get(w).endsWith(phonemes))
				return wordMap.get(w).word;
		}
		return "Nono";
	}

	/**
	 * Selects the first word from our wordMap that
	 * rhymes with the given phonemes
	 */
	static String getPOSword(String pos){
		for(String w : wordMap.keySet()){
			if(wordMap.get(w).isPOS(pos))
				return wordMap.get(w).word;
		}
		return "Nono";
	}



	/**
	 * 
	 * 
	 * @param meter The desired meter for this line of poetry
	 * @return A string of poetry with the desired meter
	 * 
	 * for example, if meter = "0101010101" it writes a line of 
	 * iambic pentameter. "Oh day of fortune, every day is great!
	 * 
	 */
	static String writeLineWithMeter(String meter){
		// Add words whose prefixes match what we are looking for.
		String rv = "";
		while(meter.length() > 0){
			String nextWord = findWordWithStressPrefix(meter);
			meter = meter.substring(numSyllables(nextWord));
			rv = rv + " " + nextWord; // + concatenates strings
		}
		return rv;
	}


	/**
	 * Writes a sonnet with ABAB CDCD EFEF GG rhyme scheme,
	 * and iambic pentameter*/
	static String writeSonnet(){
		String sonnet = "";
		for(int i = 0; i < 3; i++){
			String line1 = writeLineWithMeter("0101010101");
			String line2 = writeLineWithMeter("0101010101");
			String line3 = writeLineWithMeterAndRhymeswith("0101010101", line1);
			String line4 = writeLineWithMeterAndRhymeswith("0101010101", line2);
			String stanza = line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + "\n";
			sonnet += stanza;
		}
		String line = writeLineWithMeter("0101010101");
		sonnet = sonnet + line + "\n";
		line = writeLineWithMeterAndRhymeswith("0101010101", line);
		sonnet = sonnet + line + "\n";
		return sonnet;
	}



	/**
	 * Tell if a meter matches the end of another meter
	 * @param meter the meter whose end will be used in the comparison
	 * @param wmeter the meter that will be compared to the end of meter
	 * @return true if the meters match, false if they do not
	 */
	static boolean endMeterMatch(String meter, int[] wmeter){
		if( meter.length() < wmeter.length ){
			return false;
		}
		int dif = meter.length() - wmeter.length;
		for( int i = wmeter.length - 1; i >= 0; i-- ){
			String aMet = meter.substring(dif + i, dif + i + 1);
			if( wmeter[i] == 0 && !aMet.equals("0")){
				return false;
			}else if( wmeter[i] != 0 && aMet.equals("0")){
				return false;
			}
		}
		return true;
	}

	/**
	 * Writes a line with the given meter, such that the end rhymes with the target string
	 * @param meter The meter to generate
	 * @param target The string to rhyme with
	 *
	 * @return A line of poetry of the type described.
	 */
	static String writeLineWithMeterAndRhymeswith(String meter, String target){
		// Get the phonemes that we need to rhyme
		String targetPhonemes = getTargetPhonemes(target);
		String rhymingWord = "";
		for( Word w : wordList ){
			if( w.endsWith(targetPhonemes) && endMeterMatch(meter, w.meter) ){
				rhymingWord = w.word;
				meter = meter.substring(0, meter.length() - w.numSyllables());
				break;
			}
		}

		return writeLineWithMeter(meter) + " " + rhymingWord;
	}


	/**
	 * Finds the phonemes at the end of the target string required for a valid rhyme
	 * @param target The string to rhyme
	 *
	 * @return A string containing the phonemes
	 *
	 * For example, given "Life is wonderful" would return "ER F AH L"
	 * or perhaps "AH N D ER F AH L"
	 * Though perhaps, in this second case, it would be hard to find a word to
	 * rhyme all at once, and you'd have to do it the way we did with stresses ---
	 * a bit at a time, creating multi-word rhymes like "under full"
	 */
	static String getTargetPhonemes(String target){

		String[] words = target.split(" ");
		String lastWord = words[words.length-1];
		String[] phones = wordMap.get(lastWord).phonemes;
		String lastPhones = "";
		for( int i = phones.length - 1; i >= 0; i-- ){
			lastPhones = phones[i] + " " + lastPhones;
			if( phones[i].length() == 2 ){
				break;
			}
		}
		return lastPhones;
	}


	/**
	 * Finds a word whose meter matches some prefix of the given meter parameter
	 * @param meter The meter to match
	 * @return A word (String) whose meter works
	 */
	private static String findWordWithStressPrefix(String meter) {
		int N = wordList.length;
		int i = (int)(N * Math.random());
		for(int j = 0; j < N; j++){
			int index = (i + j) % N;
			if(wordList[index].initialMeterMatch(meter))
				return wordList[index].word;
		}
		return "llama";

	}


	/**
	 * Finds a word whose meter matches the given meter parameter
	 * @param meter The meter to match
	 * @return A word (String) whose meter works
	 */
	public static String findWordWithStresses(String meter) {
		int N = wordList.length;
		int i = (int)(N * Math.random());
		for(int j = 0; j < N; j++){
			int index = (i + j) % N;
			if(wordList[index].meter == null)
				continue;
			System.out.println(">>> " + wordList[index]);
			if(wordList[index].meter.length == meter.length() && wordList[index].initialMeterMatch(meter))
				return wordList[index].word;
		}
		return "llama";

	}

	/**
	 * Finds a word whose phonemes are close to this of the given parameter
	 * @param word The word whose phonemes should be matched
	 * @return A word (String) that is close
	 */
	public static ArrayList<String> matchPhonemes(String word, int maxDist) {
		// Find the Word object for the word
		Word theWord = wordMap.get(word);
		ArrayList<String> matchSet = new ArrayList<String>();
		if(theWord == null) {
			System.out.println("Word not found in current Vocab");
			matchSet.add("nothing here");
			return matchSet;
		}
		String[] wordPhones = theWord.phonemes;
		
		// Search the word list for a match
		int N = wordList.length;
		int i = (int)(N * Math.random());
		for(int j = 0; j < N; j++){
			int index = (i + j) % N;
			if(wordList[index].phonemes == null)
				continue;
			//System.out.println(">>> " + wordList[index]);
			if(wordList[index].phonemes.length != wordPhones.length)
				continue;
			if(wordList[index].word == word)
				continue;
			//double phonemes = wordList[index].phonemes.length;
			//int mismatch = (int) Math.floor(phonemes/2) + 1;
			int mismatch = 0;
			for(int p = 0; p < wordPhones.length; p++)
				if(!wordList[index].phonemes[p].equals(wordPhones[p]))
					mismatch++;
			if(mismatch <= maxDist) {
					matchSet.add(wordList[index].word);
			}
		}
		return matchSet;

	}




	/**
	 * Counts syllables of a given word
	 * 
	 * @param w The input word
	 * @return The number of syllables in the input word
	 */
	static int numSyllables(String w){
		Word wobj = wordMap.get(w);
		return wobj.numSyllables();
	}







	/**
	 * Reads the cmupron file, which contains information about
	 * pronunciation and meter
	 * and, for each word in our wordMap, that is, words that
	 * appeared in our source text, add the info from cmupron
	 */
	static void newBuildPronunciations(){
		// Open up the cmupron.txt file for reading
		try{
			FileReader g = null;
			g = new FileReader("SourceTexts/cmupron.txt");
			BufferedReader rhymingReader = new BufferedReader(g);
			String line = null;

			// Read the cmpupron file
			while((line = rhymingReader.readLine()) != null){
				String[] parts = line.trim().split(" ");
				if(!wordMap.containsKey(parts[0]))
					continue;
				Word w = wordMap.get(parts[0]);
				ArrayList<String> phonemes = new ArrayList<String>();
				ArrayList<Integer> stresses = new ArrayList<Integer>();
				for(int i = 1; i < parts.length; i++){
					String letters = parts[i].replaceAll("[0-9]", "");
					if(letters.length() != 0)
						phonemes.add(letters);
					String number  = parts[i].replaceAll("[^0-9]", "");
					if(number.length() != 0)
						stresses.add(Integer.parseInt(number));
				}
				// Copy the phonemes ArrayList into a fixed-size array
				String[] p = new String[phonemes.size()];
				for(int i = 0; i < phonemes.size(); i++)
					p[i] = phonemes.get(i);
				w.phonemes = p; // Set it in the Word object

				// Copy the stresses Array list into a fixed-size array 
				int[] s = new int[stresses.size()];
				for(int i = 0; i < stresses.size(); i++)
					s[i] = stresses.get(i);
				w.meter = s; // Set it in the Word object
			}
			rhymingReader.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}




	// Ends additional Items for Kathryn's Project

	/**
	 * Original 
	 * Open the vocab reader
	 */
	static void openVocabReader(){
		try{
			FileReader f = new FileReader(vocab);
			vocabReader = new BufferedReader(f);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}


	/**
	 * Set the name of the file we should get our words from.
	 * That is, the vocab filename
	 */
	public static void setVocab(String filename){
		vocab = filename;
		buildDataStructuresForNewFile();
	}

	/**
	 * Builds wordMap and fills the words with data from language-information files
	 */
	private static void buildDataStructuresForNewFile(){
		// Read the vocab file, and build the initial wordMap
		openVocabReader();	               // Create the BufferedReader for our file
		wordMap = new HashMap<String, Word>(); // Initial, empty map

		try{
			String line = null;
			while((line = vocabReader.readLine()) != null){
				line = line.replaceAll("[^a-zA-Z ]", "");
				// words is an array of clean words (Strings) from the text
				String[] words = line.trim().toUpperCase().split(" ");
				for(String w : words){
					if(wordMap.containsKey(w))
						continue;
					Word word = new Word(w);
					wordMap.put(w, word);
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		//System.out.println("Found " + wordMap.size() + " words in " + vocab);


		// Build the  wordList array of Word structures
		// Note that you must create the array of the right size first,
		// then call the toArray() method, giving the array wordList as an argument
		wordList = new Word[wordMap.size()];
		wordMap.values().toArray(wordList);

		// Now add the pronunciations and meter for those words
		newBuildPronunciations();

		// And read the parts of speech
		readPOSFile();

		/*
		// Print out the Word objects, to see how we done!
		for(String w : wordMap.keySet()){
			System.out.println(wordMap.get(w));
		}
		 */
	}


	/**
	 * Creates an array of ints corresponding to the stresses in the given string.
	 * For example: 
	 * "BRASHERS  B R AE1 SH ER0 Z" --> [1, 0]
	 * "SUPERMINICOMPUTER  S UW1 P ER0 M IH2 N IH0 K AH0 M P Y UW2 T ER0" --> [1, 0, 2, 0, 0, 2, 0]
	 */
	static int[] getMeter(String cmuline){
		return new int[] {1, 0}; // Of course, you need to find the actual meter...
	}

	/**
	 * Read the mpos.txt file to get parts of speech for our words
	 * Each line of this file looks like: abbreviate*t
	 * word, then *, then the part of speech.
	 */
	static void readPOSFile(){
		try { 
			FileReader f = new FileReader("SourceTexts/mpos.txt");
			BufferedReader reader = new BufferedReader(f);
			String line = null;
			int linesRead = 0;
			while ((line = reader.readLine()) != null) {
				linesRead++;
				if(linesRead % 10000 == 0)
					System.out.print("" + (int)(linesRead * 100 / 232123) + "% ");	
				String[] parts = line.split("\\*");
				String word = parts[0];
				String pos  = parts[1];
				if(wordMap.containsKey(word.toUpperCase()))
					wordMap.get(word.toUpperCase()).partOfSpeech = pos;
			}
			System.out.println("");
			reader.close();
		} catch (IOException x) {
			System.err.format("IOException: %s\n", x);
		}
	}


	/** 
	 * Search our wordList for a particular word
	 * Return the Word for word w, or null if it's not in wordList
	 */
	static Word searchWordList(String w){
		// Binary search pronunciations array for wupper
		int lower = 0;
		int upper = wordList.length - 1;
		while(lower <= upper){
			//System.out.println("BS: " + wordList[lower]);
			int guess = (lower + upper) / 2;
			if(wordList[guess].word.equals(w))
				return wordList[guess];

			// check to see how wupper+" " compares to guess
			if(wordList[guess].word.compareTo(w) < 0) // guess < wupper
				lower = guess + 1;
			else
				upper = guess - 1;
		}

		// If we make it to here, then the word is not in our word list
		return null;
	}


	/**
	 * Finds a word matching a given meter, part of speech and rhyme
	 * 
	 * @param meter The returned word must match a prefix of this meter
	 * @param pos   The part of speech
	 * @param phonemes The phonemes that the word must end with
	 * @return A word (String) meeting the requirements.
	 */
	public static String getSpecificWord(String meter, String pos, String phonemes){
		int l = wordList.length;
		int start = (int)Math.random() * l;
		for(int i = 0; i < l; i++){
			Word w = wordList[(i+start) % l];
			if(w.endsWith(phonemes) && w.initialMeterMatch(meter) && w.isPOS(pos))
				return w.word;
		}
		return "Blecch!";
	}


	/*
	 * Getter method for the private vocab field
	 */
	public static String getVocab() {
		return vocab;
	}


}
