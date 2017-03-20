
public class Complaint {
	private int complaintID;
	private String product;
	private String company;
	private String zipCode;
	
	public Complaint(int complaintID, String product, String company, String zipCode) {
		this.complaintID = complaintID;
		this.product = product;
		this.company = company;
		this.zipCode = zipCode;
	}
	
	public int getComplaintID() {
		return complaintID;
	}
	public void setComplaintID(int complaintID) {
		this.complaintID = complaintID;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
