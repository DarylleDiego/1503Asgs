import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/** 
 * COMP 2503 Winter 2020 Assignment 1 
 * 
 * This program must read a input stream and keeps track of the 
 * frequency at which an avenger is mentioned either by name or alias.
 *
 *  @author Maryam Elahi, Darylle, Khoi, Sahir
 * @date Fall 2020
 */

public class A1 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private ArrayList<Avenger> avengersArrayList = new ArrayList<>();
	private Scanner in;

	public static void main(String[] args)
	{
		A1 a1 = new A1();
		a1.run();
	}

	public void run() 
	{
		readInput();
		printResults();
	}

	/**
	 * read the input stream and keep track  
	 * how many times avengers are mentioned by alias or last name.
	 */
	private void readInput()
	{
		/*
		In a loop, while the scanner object has not reached end of stream,
		 	- read a word.
		 	- clean up the word
		    - if the word is not empty, add the word count. 
		    - Check if the word is either an avenger alias or last name then
				- Create a new avenger object with the corresponding alias and last name.
				- if this avenger has already been mentioned, increase the frequency count for the object already in the list.
				- if this avenger has not been mentioned before, add the newly created avenger to the list, remember to set the frequency.
		 */
		
		in = new Scanner(System.in);
		while(in.hasNext())
		{
			String word = in.next().trim().toLowerCase().replaceAll("'s", "").replaceAll("[\\d.]", "").replaceAll("[^a-z]", "");  
			
			if(word.length() > 0)
			{	
				if(word.equals("exit"))
				{
					break;
				}
				for (int row = 0; row < avengerRoster.length; row++) 
				{ 
					for (int col = 0; col < avengerRoster[row].length; col++)
					{
						if(word.equals(avengerRoster[row][col]))
						{
							Avenger member = new Avenger(avengerRoster[row][0],avengerRoster[row][1]);
	
							if(avengersArrayList.contains(member))
							{
								int i = avengersArrayList.indexOf(member);
								avengersArrayList.get(i).frequencyCounter();
	
							}
							else
							{
								avengersArrayList.add(member);
								member.frequencyCounter();
							}
						}
	
					}
				}
				totalwordcount++;
			}
		}
	}

/**
 * print the results
 */
private void printResults() {
	System.out.println("Total number of words: " + totalwordcount);
	System.out.println("Number of Avengers Mentioned: " + avengersArrayList.size());
	System.out.println();

	System.out.println("All avengers in the order they appeared in the input stream:");
	// Todo: Print the list of avengers in the order they appeared in the input
	// Make sure you follow the formatting example in the sample output
	for(int i = 0; i < avengersArrayList.size(); i++)
	{
		System.out.println(avengersArrayList.get(i).toString());
	}
	System.out.println();

	System.out.println("Top " + topN + " most popular avengers:");
	// Todo: Print the most popular avengers, see the instructions for tie breaking
	// Make sure you follow the formatting example in the sample output
	Collections.sort(avengersArrayList, new MostPopular());
	for(int i = 0; i < avengersArrayList.size(); i++)
	{
		if(i < 4)
		{
			System.out.println(avengersArrayList.get(i).toString());	
		}

	}
	System.out.println();

	System.out.println("Top " + topN + " least popular avengers:");
	// Todo: Print the least popular avengers, see the instructions for tie breaking
	// Make sure you follow the formatting example in the sample output
	Collections.sort(avengersArrayList, new LeastPopular());
	for(int i = 0; i < avengersArrayList.size(); i++)
	{
		if(i < 4)
		{
			System.out.println(avengersArrayList.get(i).toString());
		}
	}		
	System.out.println();

	System.out.println("All mentioned avengers in alphabetical order:");
	// Todo: Print the list of avengers in alphabetical order
	Collections.sort(avengersArrayList);
	for(int i = 0; i < avengersArrayList.size(); i++)
	{
		System.out.println(avengersArrayList.get(i).toString());
	}
	System.out.println();
}
}
