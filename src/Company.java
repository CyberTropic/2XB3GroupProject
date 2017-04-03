
public class Company implements Comparable<Company> {
	private String companyName;
	private int complaints;
	private double weightedComplaints;

	public Company(String companyName, int complaints) {
		super();
		this.companyName = companyName;
		this.complaints = complaints;
	}
	
	public Company(String companyName) {
		super();
		this.companyName = companyName;
		this.complaints = 0;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getComplaints() {
		return complaints;
	}
	public void setComplaints(int complaints) {
		this.complaints = complaints;
	}
	public void incComplaints(){
		this.complaints +=1;
	}
	public void addWeightedComplaint(double wc){
		this.weightedComplaints += wc;
	}
	public double getWeightedComplaint(){
		return weightedComplaints;
	}

	@Override
	public int compareTo(Company other) {
		if (this.weightedComplaints > other.weightedComplaints)
			return 1;
		else if (this.weightedComplaints < other.weightedComplaints)
			return -1;
		else if (this.complaints > other.complaints)
			return 1;
		else if (this.complaints < other.complaints)
			return -1;
		return 0;
	}
}
