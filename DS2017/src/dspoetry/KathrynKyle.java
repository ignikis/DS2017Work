package dspoetry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// Kathryn Kyle

public class KathrynKyle{

	public static void main(String[] args){
		//poet.setVocab("SourceTexts/Chaucer.txt");
		try { 

			FileReader f = new FileReader("SourceTexts/Chaucer.txt");         
			BufferedReader reader = new BufferedReader(f);
			// Pronunciation reader
			FileReader g = new FileReader("SourceTexts/cmupron.txt");            
			BufferedReader rhymingReader = new BufferedReader(g);
			rhymingReader.mark(3139728); // Set the reset point to the start of file (magic number)
			String line = null;

			int word = 0;
			int lline = 0;
			int stanza = 0;

			while (word < 7 && lline < 4 && stanza < 4) {

				if(word < 6){
					System.out.print(getWord(reader).toLowerCase() + " ");
					word++;
				}

				if(word == 6 && lline == 1 || lline == 3){
					System.out.print(getRhymingWord(reader, rhymingReader, "AY1 T").toLowerCase());
					word++;
				}

				if(word == 6 && lline == 0 || lline == 2){
					System.out.print(getWord(reader).toLowerCase() + " ");
					word++;
				}

				if(word == 7){
					System.out.print("\n");
					word = 0;
					lline++;
				}

				if(lline == 4){
					System.out.print("\n");
					lline = 0;
					stanza++;
				}

			} // end of while loop
			reader.close();
		} catch (IOException x) {
			System.err.format("IOException: %s\n", x);
		}

	} // End of main

	static String getRhymingWord(BufferedReader reader, BufferedReader rhymingReader, String r) throws IOException{
		String[]   words = null;
		String line;

		while((line = reader.readLine()) != null){
			line = line.replaceAll("[^a-zA-Z ]", "");
			words = line.trim().toUpperCase().split(" ");

			if(words.length < 5) // Move along if the line is short
				continue;

			for(String w : words){
				// Put code here that says:
				// "If w ends with the phonemes in r, return it" - okay
				String rw = lookup(rhymingReader, w);
				if(rw.endsWith(r)) //want to add language that says AND is greater than 2 characters
					return w;
			}
			// This line below looks up string w in the dictionary. Good line to make use of.
		}

		// If we get here, then we didn't find a matching word
		return "Couldn't find the desired rhyme: " + r;
	}
	static String getWord(BufferedReader reader) throws IOException {
		boolean foundWord = false;
		String[] words = null;
		int index = 0;

		while (foundWord == false) {
			String line = reader.readLine();
			line = line.replaceAll("[^a-zA-Z ]", "");
			words = line.trim().split(" ");

			if (words.length < 5)
				continue;

			int l = words.length;
			index = (int) Math.floor(Math.random() * l);
			foundWord = true;
		}
		return words[index];
	}
	/**
	 * Looks up a word w in the cmupron file, and returns the line containing that word 
	 * @param rr BufferedReader object that wraps the cmupron file
	 * @param w  The English word we wish to find in that file
	 * @return   The line in the file that has the pronunciation for word w
	 * @throws IOException
	 */
	static String lookup(BufferedReader rr, String w) throws IOException
	{
		String[]   words = null;
		String line;
		rr.reset();
		while((line = rr.readLine()) != null){
			// This will loop through all the lines in the pronunciation file
			// You should put code here to find the word w in the dictionary
			// Probably best to return all of "line"
			// And then have the getRhymingWord method check the end
			if(line.contains(w)){
				return line;
			}
		}

		// If we get here, then we didn't find a matching word
		return "Not in dictionary: " + w;
	}
}