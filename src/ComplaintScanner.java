import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.opencsv.CSVReader;

public class ComplaintScanner {
	
	static ArrayList<String> stateIndex = new ArrayList<>();
	static ArrayList<String[]> stateAdj = new ArrayList<>();
	static ArrayList<Complaint> allComplaints = new ArrayList<>();
	

	public static void createComplaints() {
		// Initialize data reader
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
				String state = tokens[8];
				Complaint readerComplaint = new Complaint(complaintID, product, company, zipCode, state);
				System.out.println(complaintID + ";" + product + ";" + company + ";" + zipCode + ";" + state);
				allComplaints.add(readerComplaint);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void stateScanner() {
		

		String folder = "resources";
		File f = new File(folder + "/StateAdjacency.txt");
		Scanner inFile = null;
		
		try {
			inFile = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (inFile.hasNextLine()) {
			String[] nl = inFile.nextLine().split(",");
			stateIndex.add(nl[0]);
			String[] adj = new String[0];
			if (nl.length > 1) {
				adj = Arrays.copyOfRange(nl, 1, nl.length);
			}
			stateAdj.add(adj);
		}

		for (int i = 0; i < stateIndex.size(); i++) {
			System.out.println(stateIndex.get(i) + " adjacent to:");
			if (stateAdj.get(i).length > 0){
				System.out.println(Arrays.toString(stateAdj.get(i)));
			} else {
				System.out.println("none");
			}
		}

	}

	public static void main(String[] args) {
		ComplaintScanner.stateScanner();
		ComplaintScanner.createComplaints();
	}

}
