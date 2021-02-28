/**
 * This class represents a record to be stored in a dictionary
 * 
 * @author Yicheng Hu
 * 
 */

public class DictEntry {
	// key attribute of the DictEntry object
	private String key;
	// data of the DictEntry object
	private int code;

	/**
	 * Constructor that stores the values of the parameters in the corresponding
	 * instance variables
	 * 
	 */
	public DictEntry(String theKey, int theCode) {
		this.key = theKey;
		this.code = theCode;
	}

	/**
	 * Returns the value of instance variable key
	 * 
	 * @return the value of instance variable key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Returns the value of instance variable code
	 * 
	 * @return the value of instance variable code
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * Returns true if this object and secondObject have the same key and code
	 * attributes; returns false otherwise
	 * 
	 * @return True or False
	 */
	public boolean isEqual(DictEntry secondObject) {
		if (this.code == secondObject.getCode() && this.key.equals(secondObject.getKey())) {
			return true;
		} else {
			return false;
		}
	}
}
