/**
 *  TetraGameSimulation
 *	@authors: Calvin
 *
 */

import java.util.Date;

/**
 * nullStarMap acts as Null Object of Null Object Pattern.
 */
class nullStarMap extends StarMapComponent {

	/**
	 * Initializing Null StarMap.
	 */
	nullStarMap() {
		starComponentID = 0;
		mapLocation = new TFaceGrid(100,100);
	}
	
	/**
	 * Do Nothing.
	 */
	boolean isEncryptedByMe(int heroID) {
		return false;
	}

	/**
	 * Do Nothing.
	 */
	void encrypt(int heroID, Date date, char Symbol) {
		
	}

	/**
	 * Do Nothing.
	 */
	void decrypt(int heroID) {
		
	}

	/**
	 * Do Nothing.
	 */
	void display() {
		
	}

	/**
	 * Do Nothing.
	 */
	void setEncryptionAlgorithm(EncryptionAlgorithm encrypt) {
		
	}
	
	/**
	 * Do Nothing.
	 */
	String getBody() {
		return null;
	}
	
	/**
	 * Do Nothing.
	 */
	String encryptedDisplay() {
		return null;
	}
	
	/**
	 * Do Nothing.
	 */
	char getEncryptedSymbol() {
		return '\0';
	}
}


