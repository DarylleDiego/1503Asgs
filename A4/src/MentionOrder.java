
import java.util.Comparator;
/**
 * This class uses the comparator to order the avengers in their mentioned order.
 * @author Darylle, Khoi, Sahir
 *
 */
public class MentionOrder implements Comparator<Avenger>{

	/**
	 * The primary ordering is by the mention order of the avengers.
	 */
	@Override
	public int compare(Avenger a1, Avenger a2) 
	{
		if (a1.getMentionOrder() < a2.getMentionOrder())
		{
			return -1;
		}
		else
		{
			return 1;
		}

	}

}
