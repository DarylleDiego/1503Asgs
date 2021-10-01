import java.util.Comparator;
/**
 * Same function as A1 LeastPopular class
 * @author Darylle, Khoi, Sahir
 *
 */
public class LeastPopular implements Comparator<Avenger>{

	/**
	 * The primary ordering is by the least frequent avenger.
     * The secondary ordering is in the ascending ordering of the length of the last name.
     * In case the length of the last names are the same,
     * then the tie break is in alphabetical order of last name.
	 */
	@Override
	public int compare(Avenger a1, Avenger a2) 
	{
		if(a1.getFrequency() < a2.getFrequency())
		{
			return -1;
		}
		else if(a1.getFrequency() > a2.getFrequency())
		{
			return 1;
		}
		else
		{
			if(a1.getHeroName().length() == a2.getHeroName().length())
			{
				return a2.getHeroName().compareTo(a1.getHeroName());
			}
			if(a1.getHeroName().length() < a2.getHeroName().length())
			{
				return -1;
			}

			return 1;	
		}

	}

}
