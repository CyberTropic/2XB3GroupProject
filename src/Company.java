
public class Company {
	private String companyName;
	private int complaints;

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
}
