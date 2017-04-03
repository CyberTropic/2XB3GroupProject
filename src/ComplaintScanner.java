import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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
			}
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

	public static void main(String[] args) {
		ComplaintScanner.stateScanner();
		// System.out.println(stateIndex);
		ComplaintScanner.createComplaints("Database100k");
		int sumComp = 0;
		for (int i = 0; i < stateComplaints.length; i++) {
			System.out.println(stateIndex.get(i) + ": " + stateComplaints[i].size() + " complaints");
			sumComp += stateComplaints[i].size();
		}
		System.out.println("Total complaints: " + sumComp);

		System.out.println("Continue?[Y/N]");
		Scanner userIn = new Scanner(System.in);
		while (userIn.hasNext() && (userIn.nextLine().equalsIgnoreCase("y"))) {
			System.out.println("Enter state: ");
			String userState = userIn.nextLine();
			if (stateIndex.indexOf(userState.toUpperCase())==-1){
				System.out.println("Invalid state!");
				continue;
			}
			
			Company[] outComp = ComplaintProcessor.createCompanies(userState.toUpperCase());
			for (Company c : outComp) {
				// System.out.printf("%-40s: %.8f \n",c.getCompanyName(),
				// c.getWeightedComplaint());
			}
			Arrays.sort(outComp);
			System.out.printf("%-6s|%-45s|%-16s \n", "Rank", "Company name", "Complaint Rating");
			System.out.printf(String.join("", Collections.nCopies(6, "-")) + "+"
					+ String.join("", Collections.nCopies(45, "-")) + "+"
					+ String.join("", Collections.nCopies(16, "-"))
					+ "\n");
			for (int i = 0; i < outComp.length; i++) {
				System.out.printf("%-6s|%-45s|%.8f \n", Integer.toString(i + 1), outComp[i].getCompanyName(),
						outComp[i].getWeightedComplaint());
			}
			System.out.println("Continue?[Y/N]");

		}
	}

}
