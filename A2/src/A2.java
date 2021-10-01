import java.util.Scanner;
/**
 * This class gets all the Avengers from an input and puts them in lists 
 * of various sorting orders
 * @author Darylle, Khoi, Sahir
 *
 */
public class A2 
{
    public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

    private Scanner input = new Scanner (System.in);
	private int totalwordcount = 0;
	private int topN = 4;
	private MostPopular mostPop = new MostPopular();
	private LeastPopular leastPop = new LeastPopular();
	private SLL<Avenger> avengerOrderList = new SLL<>();
	private SLL<Avenger> avengerAlphaList = new SLL<>();
	private SLL<Avenger> avengerMostPopList = new SLL<>(mostPop);
	private SLL<Avenger> avengerLeastPopList = new SLL<>(leastPop);
	
	public static void main(String []args)
	{
        A2 a2 = new A2();
		a2.run();
	}

    public void run() {
		readInput();
		printResults();
    }

	/**
     * Reads a string input and scans for any
     * avengers mentioned and makes avenger
     * object instances. Also counts the total
     * number of words in string input.
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
					if (avengerOrderList.exist(word))
					{
						avengerOrderList.find(word).getData().frequencyCounter();
					}
					else
					{
						avengerOrderList.addTail(newAvenger);	
					}
													
					
				}
			}
        }
            
		Node<Avenger> mover = avengerOrderList.top();
        while(mover != null)
        {
            avengerAlphaList.addInOrder(mover.getData()); 
            avengerMostPopList.addInOrder(mover.getData()); 
            avengerLeastPopList.addInOrder(mover.getData());
            	
            mover = mover.getNext();
		}
    }

	/**
     * Formats the string input to 
     * lowercase letters and removes
     * any nonalphabetic characters
     * 
     * @param next String next
     * @return	String ret
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
			return new Avenger(avengerRoster[inx][0], avengerRoster[inx][1], inx);
		} 
		else
			return null;
	}

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
	 * Output print method.
	 * Prints the results of all lists
	 * in formatted version.
	 */
	private void printResults() {
		System.out.println();
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + avengerOrderList.getCount());
		System.out.println();

		// Print all ordered by appearance
		System.out.println("All avengers in the order they appeared in the input stream:");
		avengerOrderList.printList();
		System.out.println();
		
		// Print all ordered by total number of mentions
		System.out.println("Top " + topN + " most popular avengers:");
		avengerMostPopList.printList(topN);
		System.out.println();

		// Print top five ordered by aliasCnt
		System.out.println("Top " + topN + " least popular avengers:");
		avengerLeastPopList.printList(topN);
		System.out.println();

		// Print all ordered by alias alphabetically
		System.out.println("All mentioned avengers in alphabetical order:");
		avengerAlphaList.printList();
		System.out.println();
	}
}
