import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class FinancR {
	
	static ArrayList<String> stateIndex = new ArrayList<>();
	static String[] services = new String[0];
	static ArrayList<Complaint>[] stateComplaints;
	
	private static void sumComp(){
		stateComplaints = ComplaintScanner.stateComplaints;	
		int sumComp = 0;
		System.out.println("States, Number of Complaints");
		for (int i = 0; i < stateComplaints.length; i++) {
			System.out.println(stateIndex.get(i) + ": " + stateComplaints[i].size() + " complaints");
			sumComp += stateComplaints[i].size();
		}
		System.out.println("Total complaints: " + sumComp);
	}
	
	//selecting a state
	private static int getState(){
		Scanner userIn = new Scanner(System.in);
		
		//prompts user to enter state
		System.out.println("Enter state: ");	
		int index=-1;
		
		while(index==-1){
			String userState = userIn.nextLine();
			//finding the index of the state the user chose
			index =Search.binary(stateIndex,userState.toUpperCase());
			if (index==-1){
				System.out.println("Invalid state!");
				System.out.println("Please try again.");
			}
		}
		System.out.println("The state is "+stateIndex.get(index));
		
		return index;
	}
	
	//selecting a service type
	private static String getService(){
		Scanner userIn = new Scanner(System.in);
		
		//prompts user to enter the service number
		System.out.println("Select a service: ");
		//lists services
		for (int i=0; i<services.length;i++){
			System.out.println((i+1) + ": " + services[i] );
		}
		
		String userProduct;
		int productIndex;
		
		do {	//check if the input is valid			
			userProduct = userIn.nextLine();
			productIndex= Integer.parseInt(userProduct)-1;
			
			//if it's not a valid input tell them to try again
			if (productIndex==-1||productIndex>=services.length){
				System.out.println("Invalid product!");
				System.out.println("Please try again.");
			}
			if (productIndex<-1){//come on it's not that hard
				System.out.println("Invalid product!");
				System.out.println("Dude how did you even enter a negative number like how do you mess up like that.");
				System.out.println("Try again and don't to mess up this time.");
			}					
		}while(productIndex<0||productIndex>=services.length);	//repeat until it's valid
			
		userProduct = services[productIndex];
		System.out.println(userProduct);
		
		return userProduct;	
	}
	
	
	public static void main(String[] args){
		//System.out.println("Hi guys");
		
		//grabbing information from datasets
		ComplaintScanner.stateScanner();
		// System.out.println(stateIndex);
		ComplaintScanner.createComplaints("Database100k");
		
		stateIndex = ComplaintScanner.stateIndex;	//index of states
		services = ComplaintScanner.services;	//index of service types
				
		System.out.println("Continue?[Y/N]");
		Scanner userIn = new Scanner(System.in);
		while (userIn.hasNext() && (userIn.nextLine().equalsIgnoreCase("y"))) {			
			
			//prints the states and number of complaints in each
			sumComp();
			
			//Input
			int sIndex=getState();
			String userProduct = getService();
			
			Company[] outComp = ComplaintProcessor.createCompanies(sIndex, userProduct);
			/*for (Company c : outComp) {
				System.out.printf("%-40s: %.8f \n",c.getCompanyName(),
			 	c.getWeightedComplaint());
			}*/
			
			//Sort companies
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
		userIn.close();
	}

}
