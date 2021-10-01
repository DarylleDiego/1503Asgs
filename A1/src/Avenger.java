/** Avenger class implementing Comparable interface
 *  orders avengers alphabetically
 */

public class Avenger implements Comparable<Avenger>
{
	private String heroName;
	private String heroAlias;
	private int frequency;

	public Avenger(String heroAlias, String heroName)
	{
		this.heroName = heroName;
		this.heroAlias = heroAlias;
	}

	public String getHeroName()
	{
		return heroName;
	}

	public void setHeroName(String name)
	{
		heroName = name;
	}

	public String getHeroAlias()
	{
		return heroAlias;
	}

	public void setHeroAlias(String alias)
	{
		heroAlias = alias;
	}

	public int getFrequency()
	{
		return frequency;
	}

	public void frequencyCounter()
	{
		frequency  = frequency + 1;
	}


	@Override
	public boolean equals(Object other)
	{
		Avenger o = (Avenger) other;

		if (other != null)
		{
			return (this.heroAlias.equals(o.heroAlias) && this.heroName.equals(o.heroName));
		}

		return false;
	}

	@Override
	public int compareTo(Avenger o)
	{
		return this.heroAlias.compareTo(o.heroAlias);
	}

	@Override
	public String toString()
	{

		String format = heroAlias + " aka " + heroName 
				+ " mentioned " + frequency + " time(s)";
		return format;
	}


}
