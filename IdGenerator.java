/**
 *  TetraGameSimulation
 *	@authors: Calvin
 *
 */

/**
 * IdGenerator class generates different Id instances.
 * This class act as Singleton of Singleton Pattern.
 */
public class IdGenerator 
{
	/**
	 * Single Instance of IdGenerator.
	 */
	static private IdGenerator instance = new IdGenerator(0);
	
	/**
	 * It tracks the current Id.
	 */
	private int currentId;
	
	/**
	 * Initialize the generator.
	 * @param currentId
	 */
	private IdGenerator(int currentId) {
		this.currentId = currentId;
	}
	
	/**
	 * Get the IdGenerator instance.
	 * @return
	 */
	static public IdGenerator getinstance() {
		return instance;
	}
	
	/**
	 * Get the nextId.
	 * @return
	 */
	public int nextId() {
		return ++currentId;
	}
	
	/**
	 * Get the current Id.
	 * @return
	 */
	public int currentId() {
		return currentId;
	}
}
