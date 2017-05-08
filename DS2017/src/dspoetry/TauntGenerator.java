package dspoetry;

import java.io.IOException;
import java.util.Scanner;

public class TauntGenerator extends Poet {

	public static void main(String[] args) {
		
	Poet.setVocab("SourceTexts/cmuBook.txt");
	//STEP 1: take one word from scanner
		Scanner scan = new Scanner(System.in);
		final String InputWord = scan.nextLine();
		
	//STEP 2: find InputWord in the CMUWordList
		//Poet.getphonemes.InputWord;
		
	//STEP 3: set final InputPhonemes to be the last phonemes in InputWord
		final String InputPhonemes = "AY T";
	//STEP 4; Get words that end in the input phonemes
	    /**
	     * Selects the first word from our wordMap that
	     * rhymes with the given phonemes
	     */
	    
	//STEP 5:Print a poem in which every word ends with the InputPhonemes
		int word = 0;
        int lline = 0;
        int stanza = 0;
        while (word < 10 && lline < 3 && stanza < 2) {
        	if(word < 10){
        		System.out.print(getRW(InputPhonemes) + " ");
                word++;
            }
            if(word == 10){
                System.out.print("\n");
                word = 0;
                lline++;
            }

            if(lline == 3){
                System.out.print("\n");
                lline = 0;
                stanza++;
            }

        } // end of while loop


	//	for(Word : Poet.wordList){
	//		if(w.endsWith(InputPhonemes)&& Math.random() < 0.2){
			//	System.out.print(InputWord);  // w.word + " "
	//		
		}

	}

