
public class Company {
	private String companyName;
	private int complaints;
	
	public Company(String companyName, int complaints) {
		super();
		this.companyName = companyName;
		this.complaints = complaints;
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
}
