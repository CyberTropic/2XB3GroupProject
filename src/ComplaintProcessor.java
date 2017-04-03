import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.BreadthFirstPaths;

public class ComplaintProcessor {

	public static Company[] createCompanies(String state, String product) {

		int stateID = ComplaintScanner.stateIndex.indexOf(state);
		if (stateID == -1) {
			return null;
		}

		ArrayList<Company> companiesInState = new ArrayList<>();
		ArrayList<String> companyNames = new ArrayList<>();

		// Construct company array from given state
		for (Complaint c : ComplaintScanner.stateComplaints[stateID]) {
			if (c.getProduct().equals(product)) {
				if (companyNames.indexOf(c.getCompany()) == -1) { // Company not
																	// seen yet
					companiesInState.add(new Company(c.getCompany()));
					companyNames.add(c.getCompany());
					companiesInState.get(companyNames.indexOf(c.getCompany())).incComplaints();
				} else {
					companiesInState.get(companyNames.indexOf(c.getCompany())).incComplaints();
				}
			}
		}

		// BFS to find state distances
		BreadthFirstDirectedPaths stateBFS = new BreadthFirstDirectedPaths(ComplaintScanner.stateGraph, stateID);

		for (int i = 0; i < ComplaintScanner.stateGraph.V(); i++) {
			if (stateBFS.hasPathTo(i)) {
				for (Complaint c : ComplaintScanner.stateComplaints[i]) {
					if (!(companyNames.indexOf(c.getCompany()) == -1)) {
						// System.out.println(stateBFS.distTo(i));
						companiesInState.get(companyNames.indexOf(c.getCompany()))
								.addWeightedComplaint(1.0 / (stateBFS.distTo(i) + 1));
					}
				}
			}
		}

		return companiesInState.toArray(new Company[companiesInState.size()]);

	}

}
