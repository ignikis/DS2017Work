package dspoetry;

import java.util.Arrays;

class Word{
	/** 
	 * The actual word
	 */
	String word;

	/**
	 * Array of phonemes from cmupron.txt file
	 */
	String[] phonemes;

	/**
	 * Array of ints.
	 * 1 = primary stress
	 * 2 = secondary stress
	 * 0 = no stress
	 */
	int[] meter;

	/**
	 * Strings semantically related to this word
	 */
	String[] meaning;

	/**
	 * From the POS file
	 */
	String partOfSpeech;

	/**
	 * Positive/negative feelings about this word
	 * byte is an 8-bit number, -128 -- +127
	 */
	byte connotation;



	/**************************
	 * Constructors
	 **************************/

	/**
	 * Constructor with a parameter for every field
	 */
	public Word(String word, String[] phonemes, int[] meter,
			String[] meaning, String partOfSpeech, byte connotation){
		this.word = word;
		this.phonemes = phonemes;
		this.meter = meter;
		this.meaning = meaning;
		this.partOfSpeech = partOfSpeech;
		this.connotation = connotation;
	}


	/**
	 * Constructor given the word field
	 */
	public Word(String w){
		this(w, null, null, null, "", (byte)0);
	}



	// Generic constructor, fills in no values
	// The first line of a constructor can call another constructor
	public Word(){
		this("");
	}

	/** 
	 * Tell a Word object how to display itself on screen
	 */
	public String toString(){
		String rv = this.word;
		rv += ", ";
		rv += Arrays.toString(this.meter);
		rv += ", pos=";
		rv += this.partOfSpeech;
		return rv;
	}




	/**
	 * Returns true if this word ends with the phonemes given in the parameter r.
	 */
	boolean endsWith(String r){
		if(phonemes  == null)
			return false;
		String[] phones = r.split(" ");
		int Lphones   = phones.length;
		int Lphonemes = phonemes.length;
		if(Lphones > Lphonemes)
			return false;
		//System.out.print(Arrays.toString(phones));
		//System.out.print(" " + r + " ");
		//System.out.println(Arrays.toString(phonemes));
		for(int i = 0; i < Lphones; i++){
			if(phonemes[Lphonemes-Lphones + i].compareTo(phones[i]) != 0)
				return false; // Rhyme conflict.
		}
		// If we make it here, there were no rhyme conflicts
		return true;
	}





	/**
	 * Returns true if this word has meter matching the given string.
	 * "Matching" means that each stress in this word is at least as
	 * big as the desired stress. so there.
	 */
	boolean initialMeterMatch(String meterString){
		if(meter  == null)
			return false;
		String[] meters = meterString.split("");
		int[] meterInts = new int[meters.length];
		// Convert the strings to ints
		for(int i = 0; i < meters.length; i++)
			meterInts[i] = Integer.parseInt(meters[i]);
		
		// If we have more syllables than we are asked to match, No!
		if(meter.length > meterInts.length)
			return false;
		
		// Check syllable-by-syllable for matchingness
		for(int i = 0; i < meter.length; i++){
			if(meter[i] < meterInts[i]) // If my meter is less than what we're asked for
				return false; 
		}
		// If we make it here, there were no rhyme conflicts
		return true;
	}


	/** 
	 * Returns true if this word can be the part of speech
	 * given in pos
	 */
	boolean isPOS(String pos){
		// Check to see if this Word's partOfSpeech contains pos
		int index = partOfSpeech.indexOf(pos);
		
		// Return true if it was found, false otherwise
		if(index != -1)
			return true;
		else
			return false;
		
		// Note, this whole function could have been a one-liner
		// return partOfSpeech.indexOf(pos) != -1;
	}
	
	
	/**
	 * Counts the number of syllables in this word
	 * @return The number of syllables
	 */
	int numSyllables(){
		return meter.length;
	}
}

