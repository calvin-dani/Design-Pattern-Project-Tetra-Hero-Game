/**
 *  TetraGameSimulation
 *	@authors: Calvin
 *
 */

/** 
 * EncryptionAlgorithm interface is acting as a Strategy 
 *  of Strategy Pattern 
 */
public interface EncryptionAlgorithm {
	String encrypt(String body);
	String decrypt(String body);
}

/**
 *  simpleEncryption class is acting as a Concrete Strategy 
 */
class simpleEncryption implements EncryptionAlgorithm {
	
	/**
	 * Encrypts a message
	 * @param body
	 * @return 
	 */
	public String encrypt(String body) {
		if(body.length() <= 1) {
			return body;
		}
		StringBuffer sb = new StringBuffer(body);
		return sb.reverse().toString();
	}
	
	/**
	 * Decrypt a message
	 * @param body
	 * @return 
	 */
	public String decrypt(String body) {
		return encrypt(body);
	}
}

/**
 *  nullEncryption class is acting as a Concrete Strategy along 
 *  with Null object of NULL Object Pattern 
 */

class nullEncryption implements EncryptionAlgorithm {
	
	/**
	 * Do Nothing
	 * @param body
	 * @return 
	 */
	public String encrypt(String body) {
		return body;
	}
	
	/**
	 * Do Nothing
	 * @param body
	 * @return 
	 */
	public String decrypt(String body) {
		return body;
	}
}
