import java.util.Comparator;
/**
 * Same function as MostPopular class from A1
 */
public class MostPopular implements Comparator<Avenger>{

	@Override
	public int compare(Avenger a1, Avenger a2)
	{
		int freq = a2.getFrequency() - a1.getFrequency();
		int alias = a1.getHeroAlias().compareTo(a2.getHeroAlias());

		if (freq == 0)
		{
			return alias;
		}

		return freq;
	}
}
