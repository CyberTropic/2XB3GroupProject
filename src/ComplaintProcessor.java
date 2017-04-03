import java.util.ArrayList;

public class ComplaintProcessor {
		
	public static Company[] createCompanies(String state){
		
		int stateID = ComplaintScanner.stateIndex.indexOf(state);
		if (stateID == -1){
			return null;
		}
		
		ArrayList<Company> companiesInState = new ArrayList<>();
		ArrayList<String> companyNames = new ArrayList<>();
		
		for (Complaint c : ComplaintScanner.stateComplaints[stateID]){
			if (companyNames.indexOf(c.getCompany())==-1){ //Company not seen yet
				companiesInState.add(new Company(c.getCompany()));
				companyNames.add(c.getCompany());
				companiesInState.get(companyNames.indexOf(c.getCompany())).incComplaints();
			} else {
				companiesInState.get(companyNames.indexOf(c.getCompany())).incComplaints();
			}
			
		}
		
		return companiesInState.toArray(new Company[companiesInState.size()]);
		 
	}

}
