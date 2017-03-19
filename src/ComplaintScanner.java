import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

import com.opencsv.CSVReader;

public class ComplaintScanner {

	public static void createComplaints() {
		// Initialize data reader
		String folder = "resources";
//		File f = new File(folder + "/CC_smallDatabase.csv");
//		Reader inFile = null;
//		try {
//			inFile = new Reader(f);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(folder + "/CC_smallDatabase.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] tokens;
		
		try {
			while ((tokens = reader.readNext()) != null) {
				System.out.println("Items: " + tokens.length);
				System.out.println(tokens[1] + ", " + tokens[7]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		ComplaintScanner.createComplaints();
	}
	
}
