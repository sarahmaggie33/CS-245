
public class UWECStudent extends UWECPerson {
	private double gpa;
	
	
	public UWECStudent(int uwecId, String firstName, String lastName, double gpa) {
		super(uwecId, firstName, lastName);
		this.gpa = gpa;
		this.firstName = firstName;
		this.lastName = lastName;
		this.uwecId = uwecId;
	}
	
	public double getGpa() {
		return this.gpa;
	}
	
	
	public String toString() {
		String UWECStudent = "UWECStudent = uwecId: " + getUwecId() + ", name: " + getFirstName() + " " + getLastName() + ", gpa: " + getGpa();
		return UWECStudent;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof UWECStudent)) {
			return false;
		}
		
		if (this.getUwecId() != ((UWECPerson) other).getUwecId()) {
			return false;
		}
		if (!this.getFirstName().equals(((UWECPerson) other).getFirstName())) {
			return false;
		}
		if (!this.getLastName().equals(((UWECPerson) other).getLastName())) {
			return false;
		} 
		if (this.getGpa() != ((UWECStudent) other).getGpa()) {
			return false;
		}
		
		return true;

	}
}
