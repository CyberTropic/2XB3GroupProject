import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import com.opencsv.CSVReader;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;

public class ComplaintScanner {

	static ArrayList<String> stateIndex = new ArrayList<>();
	static Digraph stateGraph = new Digraph(stateIndex.size());
	static ArrayList<Complaint> allComplaints = new ArrayList<>();
	static String[] services = new String[0];

	static ArrayList<Complaint>[] stateComplaints;

	public static void createComplaints(String database) {
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
			reader = new CSVReader(new FileReader(folder + "/CC_" + database + ".csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] tokens;
		ArrayList<String> tempServices = new ArrayList<>();
		
		int line=0;
		
		try {
			reader.readNext();
			while ((tokens = reader.readNext()) != null) {
				line++;
//				 System.out.println("Items: " + tokens.length);
				if (tokens.length != 18){
					System.out.println("Invalid line " + line + "\n" + Arrays.toString(tokens));
					break;
				}
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
				if (stateIndex.indexOf(state) == -1) {
					state = "XX";
				}
				Complaint readerComplaint = new Complaint(complaintID, product, company, zipCode, state);
				// System.out.println(complaintID + ";" + product + ";" +
				// company + ";" + zipCode + ";" + state);
				// allComplaints.add(readerComplaint);
				stateComplaints[stateIndex.indexOf(state)].add(readerComplaint);
				
				if (tempServices.indexOf(product)==-1){
					tempServices.add(product);
				}
				
			}
			
			services = tempServices.toArray(new String[tempServices.size()]);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
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

		ArrayList<String[]> tempAdjHolder = new ArrayList<>();

		stateIndex.add("XX");
		tempAdjHolder.add(new String[0]);

		while (inFile.hasNextLine()) {
			String[] nl = inFile.nextLine().split(",");
			stateIndex.add(nl[0]);
			String[] adj = new String[0];
			if (nl.length > 1) {
				adj = Arrays.copyOfRange(nl, 1, nl.length);
			}
			tempAdjHolder.add(adj);
		}
		Collections.sort(stateIndex);
		stateGraph = new Digraph(stateIndex.size());
		for (int i = 0; i < stateIndex.size(); i++) {
			if (tempAdjHolder.get(i).length > 0) {
				for (String s : tempAdjHolder.get(i)) {
					stateGraph.addEdge(i, stateIndex.indexOf(s));
				}
			}
		}

		stateComplaints = (ArrayList<Complaint>[]) new ArrayList[stateIndex.size()];
		for (int v = 0; v < stateIndex.size(); v++) {
			stateComplaints[v] = new ArrayList<Complaint>();
		}

		// System.out.println(stateGraph);

		// for (int i = 0; i < stateIndex.size(); i++) {
		// System.out.println(stateIndex.get(i) + " adjacent to:");
		// if (stateAdj.get(i).length > 0){
		// System.out.println(Arrays.toString(stateAdj.get(i)));
		// } else {
		// System.out.println("none");
		// }
		// }

	}

}
