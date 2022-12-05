/**
 *  TetraGameSimulation
 *	@authors: Calvin
 *
 */

import java.util.Date;

/**
 * EncryptingHero encompass hero information performing encryption on StarMap.
 *
 */
public class EncryptingHero {
	int heroID;
	Date date;
	char symbol;
	int restorationCounter;
	
	/**
	 * Initialize first EncryptingHero.
	 * @param heroID
	 * @param date
	 * @param symbol
	 * @param restorationCounter
	 */
	EncryptingHero(int heroID, Date date, char symbol, int restorationCounter) 
	{
		this.heroID = heroID;
		this.date = date;
		this.symbol = symbol;
		this.restorationCounter = restorationCounter;
	}
	
	/**
	 * Adding other hero's.
	 * @param heroID
	 */
	EncryptingHero(int heroID) {
		this.heroID = heroID;
		this.date = null;
		this.symbol = '\0';
		this.restorationCounter = 0;
	}
	
	/**
	 * Updating the restoration Counter.
	 */
	void updateCounter() {
		restorationCounter++;
	}
}
