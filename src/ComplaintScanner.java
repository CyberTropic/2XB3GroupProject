import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

import com.opencsv.CSVReader;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ComplaintScanner {

	public static void createComplaints() {
		// Initialize data reader
		ArrayList<Complaint> allComplaints = new ArrayList<>();
		String folder = "resources";
		// File f = new File(folder + "/CC_smallDatabase.csv");
		// Reader inFile = null;
		// try {
		// inFile = new Reader(f);
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(folder + "/CC_smallDatabase.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] tokens;

		try {
			reader.readNext();
			while ((tokens = reader.readNext()) != null) {
				System.out.println("Items: " + tokens.length);
				int complaintID;
				try {
					complaintID = Integer.parseInt(tokens[17]);
				} catch (NumberFormatException e) {
					complaintID = -1;
				}
				String product = tokens[1];
				String company = tokens[7];
				String zipCode = tokens[9];
				Complaint readerComplaint = new Complaint(complaintID, product, company, zipCode);
				System.out.println(complaintID + ";" + product + ";"+company+";"+zipCode);
				allComplaints.add(readerComplaint);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ComplaintScanner.createComplaints();
	}

}
