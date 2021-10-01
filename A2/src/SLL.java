import java.util.Comparator;

/** SLL class implementing Comparable interface
 *  
 *  @author Darylle, Khoi, Sahir
 *
 */
public class SLL <T extends Comparable<T>> 
{
	private Node<T> head;
	private Node<T> tail;
	private Comparator<T> sortList;

	/**
	 * This constructor does not take in any parameters and is 
	 * used to create a list of added in order of input 
	 * and a list of natural order using the comparable
	 */
	public SLL()
	{
		sortList = null;
	}

	/**
	 * This constructor takes in a comparator parameter, which is
	 *  used to create a list in the order of the set requirements of the 
	 *  comparator
	 * @param sortList       The comparator
	 */
	public SLL(Comparator<T> sortList)
	{
		this.sortList = sortList;
	}

	
	/**
	* Takes in a generic type and adds it to the front
	* of a singly linked list
	*
	* @param n 					Generic type n
	*/
	public void addHead(T n) 
	{
		Node<T> add = new Node<>(n);
		if(head == null)
		{
			head = tail = add;
		}
		else
		{
			add.setNext(head);
			head = add;
		}
	}

	/**
	 * Takes in generic type and adds it
	 * to tail of singly linked list, setting
	 * next to null
	 *
	 * @param n					Generic type n
	 */
	public void addTail(T n) 
	{
		Node<T> add = new Node<>(n);
		if (tail == null) 
		{ 
			head = tail = add;
		} 
		else 
		{
			tail.setNext(add);
			tail = add;
		}

	}

	/**
	 * Adds in a generic type in proper order depending 
	 * on referenced comparator and natural ordering
	 *
	 * @param n					Generic Type n
	 */
	public void addInOrder(T n) 
	{

		Node<T> add = new Node<>(n);

		if(head == null || compare(add,head) < 0)
		{
			addHead(n);
		}

		else if(compare(add,tail) > 0)
		{
			addTail(n);
		}
		else
		{
			Node<T> mover = head;
			while(mover.getNext() != null && compare(add,mover.getNext()) > 0)
			{
				mover = mover.getNext();
			}
			add.setNext(mover.getNext());
			mover.setNext(add);
		}

	}

	/**
	 * Returns head of list
	 *
	 * @return            			Node <T> head
	 */
	public Node<T> top()
	{
		return head;
	}

	/**
	 * Takes in a string to be matched. Searches 
	 * through singly linked list for Node objext c
	 * containing matching name or alias. Returns
	 * Node if match is found, or null if not
	 *
	 * @param key					String key
	 * @return						null, or Node with matching name or alias
	 */
	public Node<T> find(String key) 
	{
		Node<T> mover = head;

		while(mover != null)
		{
			if(((Avenger) mover.getData()).getHeroAlias().equals(key) || ((Avenger) mover.getData()).getHeroName().equals(key))
			{
				return mover;
			}
			mover = mover.getNext();
		}

		return null;
	}
	/**
	 * Takes in a string to be matched. Searches 
	 * through singly linked list for Node object
	 * containing matching name or alias. Returns
	 * true if match is found, or false if not
	 *
	 * @param key					String key
	 * @return						true/false
	 */
	public boolean exist(String key) 
	{
		Node<T> mover = head;

		while(mover != null)
		{
			if(((Avenger) mover.getData()).getHeroAlias().equals(key) || ((Avenger) mover.getData()).getHeroName().equals(key))
			{
				return true;
			}
			mover = mover.getNext();
		}

		return false;
	}

	/**
	 * Helper method to help sort avenger into a list.
	 * Returns integer value off of unicode value comparison.
	 *
	 * @param n1					Node<T> n1
	 * @param n2					Node<T> n2
	 * @return 						int compare value
	 */
	private int compare(Node<T> n1, Node<T> n2)
	{
		if(sortList != null)
		{
			return sortList.compare(n1.getData(), n2.getData());
		}
		else
		{
			return n1.getData().compareTo(n2.getData());
		}

	}
	/**
	 * Returns the size of the list.
	 *
	 * @return						int count
	 */
	public int getCount()  
	{  
		int count = 0; // Initialize count  
		Node<T> current = head; // Initialize current  
		while (current != null)  
		{  
			count++;  
			current = current.getNext();  
		}  
		return count;  
	}  
	
	/**
	 * Print method for ordered and alphabetical list.
	 * Prints all identified avengers.
	 *
	 */
	public void printList() 
	{
		Node<T> current = head;
		while (current != null) {
			// Visit the node. In this case, print it out.
			System.out.println(current.getData().toString());
			current = current.getNext();
		}
	}
	
	/**
	 * Prints only n value of the top avengers based off of
	 * most/least mentions.
	 * Parameter indicates how many avengers will be printed.
	 *
	 * @param 	n					int n
	 */
	public void printList(int n)
	{
		Node<T> current = head;
		int count = 0;
		while(current != null && count < n)
		{
			//Visit the node. In this case, print it out.
			System.out.println(current.getData().toString());
			current = current.getNext();
			count++;
		}
	}
}


