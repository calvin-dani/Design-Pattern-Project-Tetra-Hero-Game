/**
 *  TetraGameSimulation
 *	@authors: Calvin
 *
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * StarMapComponent acts as Component of Composite Pattern.
 * It represents StarMap in Tetra World.
 * It implements Cloneable interface to support cloning of Maps.
 */

public abstract class StarMapComponent implements Cloneable {
	/**
	 * Id of StarMap.
	 */
	int starComponentID;
	
	/**
	 * Number of maps in atlas.
	 */
	int numberItems;
	
	/**
	 * Grid location of StarMap.
	 */
	TFaceGrid mapLocation;
	
	/**
	 * Encryption Status.
	 */
	boolean EncryptionStatus;
	
	/**
	 * To check whether StarMap is encrypted or not.
	 * @return
	 */
	boolean isEncrypted() {
			return EncryptionStatus;
	}
	
	/**
	 * To support cloning of StarMaps.
	 */
	public StarMapComponent clone() throws CloneNotSupportedException {
		return (StarMapComponent)super.clone();
	}

	/**
	 * To check whether StarMap exists at passed Location or not.
	 * @param location
	 * @return
	 */
  boolean showSignal(TFaceGrid location) {
  		if(location.getRow() == mapLocation.getRow() && location.getColumn() == mapLocation.getColumn()) {
  			return true;
  		}
  		return false;
  }
  
  /**
   * Set the location of StarMap.
   * @param newLoc
   */
	public void setLocation(TFaceGrid newLoc) {
		mapLocation = newLoc;
	}
	
	/**
	 * Create Message on GUI.
	 * @param msg
	 */
	public void createMsg(String msg)
	{
		//System.out.println(msg);
		//String message = "<html><font name='sansserif' size='5'/>" + msg ;
		JOptionPane optionPane = new JOptionPane(msg
                , JOptionPane.PLAIN_MESSAGE
                , JOptionPane.DEFAULT_OPTION
                , null, null, null);
		
		JDialog dialog = optionPane.createDialog(null, "SCENARIO");			
        dialog.setLocation(900, 430);
        dialog.setVisible(true);
	
	}
	
	/**
	 * To check whether StarMap is encrypted by passed heroID or not.
	 * @param heroID
	 * @return
	 */
	abstract boolean isEncryptedByMe(int heroID);
	
	/**
	 * Encrypt the Map.
	 * @param heroID
	 * @param date
	 * @param Symbol
	 */
	abstract void encrypt(int heroID, Date date, char Symbol);
	/**
	 * Decrypt the Map.
	 * @param heroID
	 */
	abstract void decrypt(int heroID);
	
	/**
	 * Display the StarMap Contents.
	 */
	abstract void display();
	
	/**
	 * Get the message.
	 * @return
	 */
	abstract String getBody();
	
	/**
	 * To display the encrypted content.
	 * @return
	 */
	abstract String encryptedDisplay();
	
	/**
	 * Get the encrypted Symbol.
	 * @return
	 */
	abstract char getEncryptedSymbol();
	
	/**
	 * Set the encryption Algorithm.
	 * @param encrypt
	 */
	abstract void setEncryptionAlgorithm(EncryptionAlgorithm encrypt);
}