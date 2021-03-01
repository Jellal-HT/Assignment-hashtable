/**
 * This is the class implementing the class of exceptions thrown by the insert
 * and remove methods of class Dictionary
 * 
 * @author Yicheng Hu
 * 
 */

public class DictionaryException extends Exception {
	public DictionaryException(String msg) {
		System.out.println("error: " + msg);
	}
}
