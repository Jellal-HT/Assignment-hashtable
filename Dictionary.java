**
 * This class implements a dictionary using a hash table in which collisions are
 * resolved using double hashing
 * 
 * @author Yicheng Hu
 * 
 */

public class Dictionary implements DictionaryADT {
	// hashtable that store the DictEntry object
	private DictEntry[] hashTable;
	// the size of the hashtable
	private int size;
	// the amount of data stored in the hashtable
	private int numElement;
	// an object of DictEntry represents deleted data
	private DictEntry DELETED = new DictEntry("", -1);

	/**
	 * The constructor that creates an empty hashtable
	 * 
	 * @param the size of the hashtable
	 */
	public Dictionary(int size) {
		this.hashTable = new DictEntry[size];
		this.size = size;
		this.numElement = 0;
	}

	/**
	 * The first hash function
	 * 
	 * @param the key of the DictEntry object
	 * @return the position that the data should be stored in the table
	 */
	private int hashFunction1(String key) {
		// the position that the data should be stored in the table
		int pos = (int) key.charAt(0);
		// the base for the polynomial function that generates hash code
		int base = 41;
		// a loop that generates position
		for (int i = 1; i <= key.length() - 1; i++) {
			pos = (pos * base + key.charAt(i)) % this.size;
		}
		return pos;
	}

	/**
	 * The first hash function
	 * 
	 * @param the key of the DictEntry object
	 * @return the result of the second hash function
	 */
	private int hashFunction2(String key) {
		int num = (int) key.charAt(0);
		// prime number used in the second hash function
		int prime = 17;
		// the base for the polynomial function that generates hash code
		int base = 41;
		for (int i = 1; i <= key.length() - 1; i++) {
			num = (num * base + key.charAt(i)) % prime;
		}
		return prime - num;
	}

	/**
	 * Method that insert data in the hashtable
	 * 
	 * @param the DictEntry object
	 * @return return 1 if collision happens, otherwise, return 0
	 * @throws throw DictionaryEception if exception occurs
	 */
	public int insert(DictEntry pair) throws DictionaryException {
		// the key of the object
		String stringKey = pair.getKey();
		// the position of the data in the table from first hash function
		int pos = hashFunction1(stringKey);
		// the result of the second hash function
		int h2 = hashFunction2(stringKey);
		// if statement that determines whether the list is full or the same key has
		// appeared
		if (find(pair.getKey()) != null || this.numElement == this.size) {
			throw new DictionaryException("The key has appeared or the table is already full");
		}
		// put the data and return 0 if the position got from the first hash function is
		// empty
		if (this.hashTable[pos] == null) {
			this.hashTable[pos] = pair;
			numElement++;
			return 0;
		}
		// if the first position above is full, finding a new position with second hash
		// function
		while (this.hashTable[pos] != null && !this.hashTable[pos].equals(this.DELETED)) {
			pos = (pos + h2) % this.size;
		}
		// put the data and return 1
		this.hashTable[pos] = pair;
		numElement++;
		return 1;
	}

	/**
	 * Method that removes data in the hashtable
	 * 
	 * @param the key of DictEntry object
	 * @throws throw DictionaryEception if exception occurs
	 */
	public void remove(String key) throws DictionaryException {
		// position of the data
		int pos = hashFunction1(key);
		// result of the second hash function
		int h2 = hashFunction2(key);
		// the amount of positions that we have already checked
		int total = 0;
		// a loop that find the data with the parameter key
		while (hashTable[pos] != null && !hashTable[pos].getKey().equals(key)) {
			pos = (pos + h2) % this.size;
			total++;
			if (total == this.size) {
				throw new DictionaryException("The key does not exit");
			}
		}
		// throw exception if the data is not in the table, otherwise, deleted the data
		// by put object deleted
		if (hashTable[pos] == null) {
			throw new DictionaryException("The key does not exit");
		} else {
			hashTable[pos] = this.DELETED;
		}
		this.numElement--;
	}

	/**
	 * Method that insert data in the hashtable
	 * 
	 * @param the DictEntry object
	 * @return return the DictEntry object
	 */
	public DictEntry find(String key) {
		// position of the data
		int pos = hashFunction1(key);
		// result of the second hash function
		int h2 = hashFunction2(key);
		// the amount of positions that we have already checked
		int total = 0;
		// a loop that find the data with the parameter key
		while (hashTable[pos] != null && !hashTable[pos].getKey().equals(key)) {
			pos = (pos + h2) % this.size;
			total++;
			if (total == this.size) {
				return null;
			}
		}
		// return null if the data is not found, otherwise return the data found
		if (hashTable[pos] == null) {
			return null;
		} else {
			return hashTable[pos];
		}
	}

	/**
	 * Method that returns the amount of data stored in the table
	 * 
	 * @return return the amount of data stored in the table
	 */
	public int numElements() {
		return this.numElement;
	}
}
