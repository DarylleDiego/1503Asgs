/** Avenger class implementing Comparable interface
 *  orders avengers alphabetically
 *  @author Darylle, Khoi, Sahir
 *
 */

public class Avenger implements Comparable<Avenger>
{
	private String heroName;
	private String heroAlias;
	private int frequency;
	private int mentionOrder;

	/**
	 * 
	 * @param heroAlias  Alias of the hero.
	 * @param heroName   Surname of the hero.
	 */
	public Avenger(String heroAlias, String heroName, int id, int mentionOrder)
	{
		this.heroAlias = heroAlias;
		this.heroName = heroName;
		frequency = 1;
		this.mentionOrder = mentionOrder;
	}

	/**
	 * Gets the name of the hero.
	 * @return heroName
	 */
	public String getHeroName()
	{
		return heroName;
	}

	/**
	 * Sets the name of the hero.
	 * @param name
	 */
	public void setHeroName(String name)
	{
		heroName = name;
	}

	/**
	 * Gets the alias of the hero.
	 * @return heroAlias
	 */
	public String getHeroAlias()
	{
		return heroAlias;
	}

	/**
	 * Sets the alias of the hero.
	 * @param alias
	 */
	public void setHeroAlias(String alias)
	{
		heroAlias = alias;
	}

	/**
	 * Gets the frequency of the hero mentioned.
	 * @return frequency
	 */
	public int getFrequency()
	{
		return frequency;
	}
	
	/**
	 * Gets the mention order of the hero.
	 * @return mentionOrder
	 */
	public int getMentionOrder()
	{
		return mentionOrder;
	}

	/**
	 * Adds one to the frequency of the hero if mentioned more than once.
	 */
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
