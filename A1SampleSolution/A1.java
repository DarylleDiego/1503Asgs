import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/** 
 * COMP 2503 Winter 2020 Assignment 1 
 * 
 * This program reads a input stream and keeps track of the 
 * frequency at which an avenger is mentioned either by name or alias.
 *
 * @author Maryam Elahi
 * @date Fall 2020
*/

public class A1 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private Scanner input = new Scanner(System.in);
	private int totalwordcount = 0;
	private ArrayList<Avenger> avengersArrayList = new ArrayList<>();

	public static void main(String[] args) {
		A1 a1 = new A1();
		a1.run();
	}

	public void run() {
		readInput();
		printResults();
	}

	private void readInput() {
		while (input.hasNext()) {

			String word = cleanWord(input.next());

			if (word.length() > 0) {
				totalwordcount++;
				if (!isAvenger(word))
					continue;
				Avenger newAvengerObject = createAvengerObject(word);
				if (newAvengerObject == null) {
					continue;
				} else {
					if (avengersArrayList.contains(newAvengerObject)) {
						avengersArrayList.get(avengersArrayList.indexOf(newAvengerObject)).addFrequency();
					} else {
						avengersArrayList.add(newAvengerObject);
					}
				}
			}
		}
	}

	private Avenger createAvengerObject(String word) {
		int inx = -1;
		for (int i = 0; i < avengerRoster.length; i++) {
			if (avengerRoster[i][0].equals(word) 
					|| avengerRoster[i][1].equals(word)) {
				inx = i;
				break;
			}
		}
		if (inx != -1) {
			return new Avenger(avengerRoster[inx][0], avengerRoster[inx][1], inx);
		} else
			return null;
	}

	private boolean isAvenger(String word) {
		for (int i = 0; i < avengerRoster.length; i++) {
			if ((avengerRoster[i][0].equals(word)) 
					|| (avengerRoster[i][1].equals(word))) {
				return true;
			}
		}
		return false;
	}

	private String cleanWord(String next) {
		// First, if there is an apostrophe, the substring
		// before the apostrophe is used and the rest is ignored.
		// Words are converted to all lowercase.
		// All other punctuation and numbers are skipped.
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;
	}

	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + avengersArrayList.size());
		System.out.println();

		// Print all ordered by appearance
		System.out.println("All avengers in the order they appeared in the input stream:");
		printAllNameAndAlias(avengersArrayList);
		System.out.println();
		
		// Print all ordered by total number of mentions
		System.out.println("Top " + topN + " most popular avengers:");
		Collections.sort(avengersArrayList, new AvengerComparatorFreqDesc());
		printTopN(avengersArrayList, topN);
		System.out.println();

		// Print top five ordered by aliasCnt
		System.out.println("Top " + topN + " least popular avengers:");
		Collections.sort(avengersArrayList, new AvengerComparatorFreqAsc());
		printTopN(avengersArrayList, topN);
		System.out.println();

		// Print all ordered by alias alphabetically
		System.out.println("All mentioned avengers in alphabetical order:");
		Collections.sort(avengersArrayList);
		printAllNameAndAlias(avengersArrayList);
		System.out.println();
	}

	private void printAllNameAndAlias(ArrayList<Avenger> aList) {
		for (Avenger a : aList)
			System.out.println(a);
			//System.out.println(a.getAlias() + " aka " + a.getName() + " " + (a.getNameCnt() + a.getAliasCnt()));
	}

	private void printTopN(ArrayList<Avenger> alist, int n) {
		for (int i = 0; i < n && i < alist.size(); i++)
			System.out.println(alist.get(i));
	}
}
