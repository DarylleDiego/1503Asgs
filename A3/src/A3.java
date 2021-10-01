
import java.util.Scanner;

/**
 * COMP 2503 Winter 2020 Assignment 3
 * 
 * This program must read a input stream and keeps track of the frequency at
 * which an avenger is mentioned either by name or alias. The program uses a BST
 * for storing the data. Multiple BSTs with alternative orderings are
 * constructed to show the required output.
 * 
 * @author Maryam Elahi, Sahir, Don and Darylle
 * @date Fall 2020
 */

public class A3 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private Scanner input = new Scanner(System.in);
	private int mentionOrder = 0;
	private BST<Avenger> alphaBST = new BST<>();
	private BST<Avenger> mentionOrderBST = new BST<Avenger>(new MentionOrder());
	private BST<Avenger> mostPopBST = new BST<Avenger>(new MostPopular());
	private BST<Avenger> leastPopBST = new BST<Avenger>(new LeastPopular());

	public static void main(String[] args) {
		A3 a1 = new A3();
		a1.run();
	}

	public void run() 
	{
		readInput();
		createdAlternativeOrderBSTs();
		printResults();
		
	}

	/**
	*	Deletes Hawkeye from avenger list and alphaBST
	*	creates new BSTs without Hawkeye avenger.
	*/
	private void createdAlternativeOrderBSTs() 
	{
		Avenger toDelete = new Avenger("hawkeye", "barton", 0, 0);
		alphaBST.delete(toDelete);
		for (Avenger a: alphaBST) 
		{
			mostPopBST.add(a);
			leastPopBST.add(a);
			mentionOrderBST.add(a);
		}
	}

	/**
	 * read the input stream and keep track how many times avengers are mentioned by
	 * alias or last name.
	 */
	private void readInput() 
	{
		while(input.hasNext())
        {
            String word = formatWord(input.next());
            if(word.length() > 0)
            {
                totalwordcount++;
                if(!isAvenger(word))
				{
					continue;
				}
                
				Avenger newAvenger = createAvenger(word);
				
				if(newAvenger == null)
				{
					continue;
				}
				else
				{
					if(alphaBST.exists(newAvenger))
					{
						alphaBST.find(newAvenger).frequencyCounter();
					}

					else
					{
						alphaBST.add(newAvenger);

					}
					
				}
			}
		}
    }

	/**
	*	Scans the parameter String value
	*	next and formats the word to only
	*	return lowercase alphabet values
	*/
	private String formatWord(String next) 
    {
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;
	}

	/**
     * Once avenger alias or last name is
     * mentioned, the string word is used
     * to create an Avenger object instance.
     * 
     * @param word	String word
     * @return		Avenger instance/null
     */
	private Avenger createAvenger(String word)
	{
		int inx = -1;
		for (int i = 0; i < avengerRoster.length; i++) 
		{
			if (avengerRoster[i][0].equals(word) 
					|| avengerRoster[i][1].equals(word)) 
			{
				inx = i;
				break;
			}
		}
		if (inx != -1) 
		{
			
			return new Avenger(avengerRoster[inx][0], avengerRoster[inx][1], inx, mentionOrder++); 
		} 
		else
			return null;
	}

	//public int mentionOrder(int inx)

	/**
	 * 	Validates if string input is an avenger.	
	 * 
	 * @param word	String word
	 * @return		boolean true/false
	 */
	private boolean isAvenger(String word)
	{
		for (int i = 0; i < avengerRoster.length; i++) {
			if ((avengerRoster[i][0].equals(word)) 
					|| (avengerRoster[i][1].equals(word))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * print the results
	 */
	private void printResults() {

		int limit = 0;

		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + mentionOrderBST.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		for (Avenger a: mentionOrderBST) 
		{
			System.out.println(a.toString());
		}
		System.out.println();

		System.out.println("Top " + topN + " most popular avengers:");
		for (Avenger a: mostPopBST) 
		{
			if(limit < 4)
			System.out.println(a.toString());

			limit++;
		}
		System.out.println();

		limit = 0;
		System.out.println("Top " + topN + " least popular avengers:");
		for (Avenger a: leastPopBST) 
		{
			if(limit < 4)
			System.out.println(a.toString());

			limit++;
		}
		System.out.println();
		
		System.out.println("All mentioned avengers in alphabetical order:");
		for (Avenger a: alphaBST) 
		{
			System.out.println(a.toString());
		}
		System.out.println();

		System.out.println("Height of the mention order tree is : " + mentionOrderBST.height()
				+ " (Optimal height for this tree is : " + mentionOrderBST.optimalHeight() + ")");
		System.out.println("Height of the alphabetical tree is : " + alphaBST.height()
				+ " (Optimal height for this tree is : " + alphaBST.optimalHeight() + ")");
		System.out.println("Height of the most frequent tree is : " + mostPopBST.height()
				+ " (Optimal height for this tree is : " + mostPopBST.optimalHeight() + ")");
		System.out.println("Height of the least frequent tree is : " + leastPopBST.height()
				+ " (Optimal height for this tree is : " + leastPopBST.optimalHeight() + ")");
	}
}