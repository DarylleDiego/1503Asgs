
public class Avenger implements Comparable<Avenger> {
	private String heroAlias;
	private String heroName;

	private int frequency;

	public Avenger(String alias, String name, int id) {
		heroName = name;
		heroAlias = alias;
		frequency = 1;
	}

	// Getters and Setters
	public String getAlias() {
		return heroAlias;
	}

	public String getName() {
		return heroName;
	}

	public int getFrequency() {
		return frequency;
	}

	public void addFrequency() {
		this.frequency++;
	}
	
	@Override
	public int compareTo(Avenger other) {
		if (other == null)
			return -1;
		return this.getAlias().compareTo(other.getAlias());
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		Avenger a = (Avenger) other;
		if (this.getAlias().equals(a.getAlias()) && this.getName().equals(a.getName()))
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		String aliasAndName = heroAlias + heroName;
		return aliasAndName.hashCode();
	}

	public String toString () {
		return 	heroAlias + " aka " + heroName 
				+ " mentioned " + frequency + " time(s)";
	}
}
