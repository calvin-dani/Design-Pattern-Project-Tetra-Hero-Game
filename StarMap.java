/**
 *  TetraGameSimulation
 *	@authors: Calvin
 *
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * StarMap acts as Leaf of Composite Pattern.
 *
 */
public class StarMap extends StarMapComponent {
	String body;
	EncryptionAlgorithm encryptionAlgo;
	List<EncryptingHero> encryptedHeroes = new ArrayList<EncryptingHero>();
	EncryptingHeroIterator lehIterator;
	
	/**
	 * Initializing StarMap.
	 * @param starMapID
	 * @param loc
	 * @param directions
	 */
	StarMap(int starMapID, TFaceGrid loc, String directions) {
		this.starComponentID = starMapID;
		this.mapLocation = loc;
		this.numberItems = 1;
		this.body = directions;
	}
		
	/**
	 * Create Iterator for traversal.
	 * @param leh
	 */
	private void createIterator(List<EncryptingHero> leh) {
		lehIterator = new EncryptingHeroIterator(leh);
	}
	
	/**
	 * Get the message.
	 * @return
	 */
	String getBody() {
		return body;
	}
	
	/**
	 * Encrypt the StarMap.
	 * @param heroID
	 * @param date
	 * @param Symbol
	 */
	void encrypt(int heroID, Date date, char symbol) {
		EncryptingHero eh = null;
		EncryptingHero previousHero;
		String strategy = null;
		boolean alreadyEncrypted = false;

		if(EncryptionStatus == true) {
			createIterator(encryptedHeroes);
			lehIterator.begin();
			while(lehIterator.hasNext()) {
				previousHero = (EncryptingHero)lehIterator.next();
				if(previousHero.heroID == heroID) {
					alreadyEncrypted = true;
					previousHero.updateCounter();
					break;
				}
			}
			if(alreadyEncrypted == false) {
					eh = new EncryptingHero(heroID);
			}
		}
		else {
			eh = new EncryptingHero(heroID, date, symbol, 1);
			
			/* Encrypt the message */
			createMsg("Encrypted by HERO 1");
//			body = encryptionAlgo.encrypt(body);
			EncryptionStatus = true;

		}
		if(eh != null) {
			encryptedHeroes.add(eh);
		}		
	}
	
	/**
	 * Decrypt the StarMap.
	 * @param heroID
	 */
	void decrypt(int heroID) {
		EncryptingHero previousHero;
		
		createIterator(encryptedHeroes);
		lehIterator.begin();
		while(lehIterator.hasNext()) {
			previousHero = (EncryptingHero)lehIterator.next();
			if(previousHero.heroID == heroID) {
				body = encryptionAlgo.decrypt(body);
				return;
			}
		}
	}
	
	/**
	 * To check whether StarMap is encrypted by passed heroID or not.
	 * @param heroID
	 * @return
	 */
	boolean isEncryptedByMe(int heroID) {
		EncryptingHero previousHero;
		
		createIterator(encryptedHeroes);
		lehIterator.begin();
		while(lehIterator.hasNext()) {
			previousHero = (EncryptingHero)lehIterator.next();
			if(previousHero.heroID == heroID) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Display the StarMap Contents.
	 */
	void display() {
		String message = null;
		if(EncryptionStatus != true) {
			message = "\t\t" + body;
		}
		else {
			message = encryptedDisplay();
			for(int i = 0; i < 40; ++i) {
				message = message + getEncryptedSymbol();
			}
		}
		createMsg(message);
	}
	
	/**
	 * Get the encrypted Symbol.
	 * @return
	 */
	char getEncryptedSymbol() {
		EncryptingHero initialHero;
		
		createIterator(encryptedHeroes);
		lehIterator.begin();
		initialHero = (EncryptingHero)lehIterator.next();
		
		return initialHero.symbol;
	}
	
	/**
	 * To display the encrypted content.
	 * @return
	 */
	String encryptedDisplay() {
		
		String message = "";
		createIterator(encryptedHeroes);
		lehIterator.begin();
		EncryptingHero initialHero = (EncryptingHero)lehIterator.next();
		for(int i = 0; i < 40; ++i) {
			message = message + initialHero.symbol;
		}
		
		message = message + "\n\t\tID: " + initialHero.heroID + " Date: " + initialHero.date + "\n";
		while(lehIterator.hasNext()) {
			EncryptingHero newHero = (EncryptingHero)lehIterator.next();
			message = message + "\n\t\tID: " + newHero.heroID + "\n";
		}
		message = message + "\t\t" + body + "\n";
	
		return message;
	}
	
	/**
	 * Set the encryption Algorithm.
	 * @param encrypt
	 */
	void setEncryptionAlgorithm(EncryptionAlgorithm encryptAlgo) {
		this.encryptionAlgo = encryptAlgo;
	}
}
