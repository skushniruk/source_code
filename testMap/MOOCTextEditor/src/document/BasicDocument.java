package document;

import java.util.List;

/** 
 * A naive implementation of the Document abstract class. 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document 
{
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text)
	{
		super(text);
	}
	
	
	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		if (this.getText() == "")
			return 0;
		String[] txt;
	    txt = this.getText().split("[ !,.?0-9]+");
	    return txt.length;
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
		if (this.getText() == "")
			return 0;
		String[] txt;
	    txt = this.getText().split("[.!?]+");
	    return txt.length;
	}

	/**
	 * Get the number of syllables in the document.
	 * Words are defined as above.  Syllables are defined as:
	 * a contiguous sequence of vowels, except for a lone "e" at the 
	 * end of a word if the word has another set of contiguous vowels, 
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{
		if (this.getText() == "")
			return 0;
		int count = 0;
		char[] ch = this.getText().toLowerCase().toCharArray();
		for (int i = 1; i < ch.length-1; i++)
		{
			if ((ch[i] == 97 | ch[i] == 101 | ch[i] == 105 | ch[i] == 111 | ch[i] == 117 | 
					ch[i] == 121) & ch[i-1] != ch[i] & (ch[i-1] != 97 & ch[i-1] != 101 & ch[i-1] != 105 & ch[i-1] != 111 & ch[i-1] != 117 & 
					ch[i-1] != 121))
				count++;
			
			if (ch[i+1] < 96 & ch[i] == 101)
				count--;
			if (ch[i] == 101 && i > 3 && ((ch[i-1] == 98 && ch[i-2] == 32 && ch[i+1] == 32) | (ch[i-1] == 104 && ch[i-2] == 116 && ch[i-3] == 32 && ch[i+1] == 32)))
				count++;
		}
		return count;
		
	}
	
	
	/* The main method for testing this class. 
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		testCase(new BasicDocument("This is a test.  How many???  "
		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
		        + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new BasicDocument("Segue"), 2, 1, 1);
		testCase(new BasicDocument("Sentence"), 2, 1, 1);
		testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
		
	}
	
}
