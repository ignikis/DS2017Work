package dspoetry;
import java.util.LinkedList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordLadder extends Poet{

	final static int WORDLEN = 5;

	// Holds our list of words from the file
	static ArrayList<String> wordList = new ArrayList<String>();

	public static void main(String[] args){
		// LinkedList q= new LinkedList();
		
		
		System.out.print("What words would you like to match?");
		System.out.println("");
		Scanner start = new Scanner(System.in);
		Scanner end = new Scanner(System.in);
		String start1 = start.next();
		String end1 = end.nextLine();
		System.out.println("How many mismatches would you like to allow?");
		//System.out.println("");
		Scanner mismatch = new Scanner(System.in);
		int mismatch1 = mismatch.nextInt();
		
		Poet.setVocab("SourceTexts/JaneAusten.txt");
		buildLadder(end1, start1, mismatch1);
		
	}

	/**
	 * Function to build a word ladder starting at start,
	 * and ending at end.
	 */
	static void buildLadder(String start, String end, int m){
		ArrayList<String> visited = new ArrayList<String>();
		ArrayList<String> queue = new ArrayList<String>();
		// Maps words to their parents
		HashMap<String, String> parents = new HashMap<String, String>();

		for(String w : wordList){
			parents.put(w, null);
		}

		queue.add(start);
		while(!queue.isEmpty()){
			String x = queue.get(0);
			queue.remove(0);
			
			ArrayList<String> neighbors = Poet.matchPhonemes(x, m);
			for(String w: neighbors){
				if(visited.contains(w)){
					continue;
				}
				else {
					queue.add(w);
					visited.add(w);
					if(w != x)
						parents.put(w, x);
					//System.out.println(w);
				}
			}
		}
		parents.remove(start);
		
		/**
		for(String key : parents.keySet()){
			String value = parents.get(key);
			System.out.println(key + "->" + value);
		}
		*/
		
	
		String word = end;
		while(word != null){
			System.out.println(word);
			word = parents.get(word);
		}
		
	}




	/**
	 * finds all words 1 letter different from "word" and
	 * returns one of them at random

	static String takeOneStep(String word){
		LinkedList<String> adj = matchPhonemes(word);
		System.out.println(word + ":" + Arrays.toString(adj.toArray()));
		int num = adj.size();
		Random r = new Random();
		int selection = r.nextInt(num);
		return (String) adj.get(selection);
	}
	 */



	/**
	 * Return all words that differ by 1 letter from "word"

	static LinkedList<String> findAllAdjacentMeteredWords(String word){
		LinkedList<String> rv = new LinkedList<String>();
		String METER = Poet.findWordWithStressPrefix(word);
		int METERLENGTH = METER.length();

		for(int i = 0; i < METERLENGTH; i++){
			for(int b = 0; b <= 1; b++){
				String newMeter = METER.substring(0,i) + Integer.toString(b) + METER.substring(i+1);
				if(newMeter.equals(METER))
					continue;
				if(Poet.findWordWithStressPrefix(newMeter) != null)
					rv.add(newMeter);

			}
		}
		return rv;
	}
	 */
}
