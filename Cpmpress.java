
/**
 * Compression class that takes the name of file from argument and compresses it
 * 
 * @author Yicheng Hu 
 * 
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Compress {

	public static void main(String[] args) {
		// create a hashtable with the size that is larger than 4069
		Dictionary table = new Dictionary(8081);
		// a for loop that initializes the hashtable
		for (int i = 0; i < 256; i++) {
			String key = Character.toString((char) i);
			DictEntry pairs = new DictEntry(key, i);
			try {
				table.insert(pairs);
			} catch (DictionaryException e) {
				e.printStackTrace();
			}
		}
		// initialize a BufferedInputStream and BufferedOutputStream
		BufferedInputStream in;
		BufferedOutputStream out;
		// get the file name from argument
		String inputFile = args[0];
		try {
			// open file
			in = new BufferedInputStream(new FileInputStream(inputFile));
			// open the output file
			out = new BufferedOutputStream(new FileOutputStream(inputFile + ".zzz"));
			// the variable stores the code of the next DictEntry object that will be
			// inserted in the dictionary
			int nc = 256;
			// read the input file
			int b = 0;
			b = in.read();
			// read the first byte and convert it to a string
			String s = Character.toString((char) b);
			String s2;
			// a loop that store the inputfile data into the hashtable and write to the
			// compressed output file
			while (true) {
				b = in.read();
				// stop when reach the end of the output file
				if (b == -1) {
					break;
				}
				s = s + (char) b;
				if (table.find(s) == null) {
					s2 = s.substring(0, s.length() - 1);
					DictEntry d = table.find(s2);
					int k = d.getCode();
					MyOutput.output(k, out);
					if (table.numElements() < 4096) {
						DictEntry d2 = new DictEntry(s, nc);
						table.insert(d2);
						nc++;
					}
					s = s.substring(s.length() - 1);
				}
			}
			DictEntry d = table.find(s);
			int k = d.getCode();
			// write to the ouptfile with the code of the DictEntry object
			MyOutput.output(k, out);
			// close two files
			in.close();
			MyOutput.flush(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DictionaryException e) {
			e.printStackTrace();
		}
	}

}
