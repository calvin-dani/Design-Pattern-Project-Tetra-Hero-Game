/**
 *  TetraGameSimulation
 *	@authors: Calvin
 *
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * StarAtlas acts as Composite of Composite Pattern.
 * It consists of many StarMaps.
 *
 */
class StarAtlas extends StarMapComponent {
	
	/**
	 * List of StarMaps.
	 */
	List<StarMapComponent> lmaps = new ArrayList<StarMapComponent>();
	
	/**
	 * External Iterator used to traverse several StarMaps in StarAtlas.
	 */
	StarMapComponentIterator lsmcIterator;
	
	/**
	 * Initialize StarAtlas.
	 * @param starComponentID
	 * @param loc
	 */
	StarAtlas(int starComponentID, TFaceGrid loc) {
		this.starComponentID = starComponentID;
		this.mapLocation = loc;
		this.numberItems = 0;
		
	}
	
	/**
	 * Create Iterator for traversal.
	 * @param lsmc
	 */
	private void createIterator(List<StarMapComponent> lsmc) {
		this.lsmcIterator = new StarMapComponentIterator(lsmc);
	}
	
	/**
	 * Get the message.
	 * @return
	 */
	String getBody() {
		return null;
	}
	
	/**
	 * Get the encrypted Symbol.
	 * @return
	 */
	char getEncryptedSymbol() {
		StarMapComponent map;
		
		EncryptionStatus = true;
		createIterator(lmaps);
		lsmcIterator.begin();
		if(lsmcIterator.hasNext()) {
			map = (StarMapComponent)lsmcIterator.next();
			return map.getEncryptedSymbol();
		}
		
		return '\0';
	}
	
	/**
	 * Encrypt the StarAtlas.
	 * @param heroID
	 * @param date
	 * @param Symbol
	 */
	void encrypt(int heroID, Date date, char symbol) {
		StarMapComponent map;
		
		EncryptionStatus = true;
		createIterator(lmaps);
		lsmcIterator.begin();
		while(lsmcIterator.hasNext()) {
			map = (StarMapComponent)lsmcIterator.next();
			map.encrypt(heroID, date, symbol);
		}
	}
	
	/**
	 * To check whether StarAtlas is encrypted by passed heroID or not.
	 * @param heroID
	 * @return
	 */
	boolean isEncryptedByMe(int heroID) {
		StarMapComponent map;
		
		createIterator(lmaps);
		lsmcIterator.begin();
		while(lsmcIterator.hasNext()) {
			map = (StarMapComponent)lsmcIterator.next();
			if(map.isEncryptedByMe(heroID) == true) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Decrypt the StarAtlas.
	 * @param heroID
	 */
	void decrypt(int heroID) {
		StarMapComponent map;
		
		createIterator(lmaps);
		lsmcIterator.begin();
		while(lsmcIterator.hasNext()) {
			map = (StarMapComponent)lsmcIterator.next();
			map.decrypt(heroID);
		}
	}
	
	/**
	 * Display the StarAtlas Contents.
	 */
	void display() {
		StarMapComponent map;

		if(EncryptionStatus != true) {
			createIterator(lmaps);
			lsmcIterator.begin();
			while(lsmcIterator.hasNext()) {
				map = (StarMapComponent)lsmcIterator.next();
			  map.display();
			}
		}
		else {
			String message = encryptedDisplay();
			createMsg(message);
		}
	}
	
	/**
	 * To display the encrypted content.
	 * @return
	 */
	String encryptedDisplay() {
		String message = null;
		StarMapComponent map;
		
		createIterator(lmaps);
		lsmcIterator.begin();
		map = (StarMapComponent)lsmcIterator.next();
		message = map.encryptedDisplay();
		while(lsmcIterator.hasNext()) {
			map = (StarMapComponent)lsmcIterator.next();
		  message += "\t";
			message += map.getBody();
		}
		message += "\n";
		for(int i = 0; i < 40; ++i) {
			message = message + map.getEncryptedSymbol();
		}
		
		return message;
	}
	
	/**
	 * Set the encryption Algorithm.
	 * @param encrypt
	 */
	void setEncryptionAlgorithm(EncryptionAlgorithm encrypt) {
		StarMapComponent map;

		createIterator(lmaps);
		lsmcIterator.begin();
		while(lsmcIterator.hasNext()) {
			map = (StarMapComponent)lsmcIterator.next();
		  map.setEncryptionAlgorithm(encrypt);
		}
	}
	
	/**
	 * Add new StarMap in StarAtlas.
	 * @param starMap
	 */
	void addStarMap(StarMapComponent starMap) {
		lmaps.add(starMap);
		numberItems++;
	}
}