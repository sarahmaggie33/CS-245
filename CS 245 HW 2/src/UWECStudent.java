
public class UWECStudent extends UWECAcademic {
private double gpa;
	
	public UWECStudent(int uwecId, String firstName, String lastName, double gpa) {
		super(uwecId, firstName, lastName);
		this.gpa = gpa;
		if (getNumTotalCredits() < 24){
			setTitle("Freshman");
		} else if (getNumTotalCredits() < 58) {
			setTitle("Sophomore");
		} else if (getNumTotalCredits() < 86) {
			setTitle("Junior");
		} else {
			setTitle("Senior");
		}
	}
	
	public void setNumTotalCredits(int numTotalCredits) {
		super.setNumTotalCredits(numTotalCredits);
		if (getNumTotalCredits() < 24){
			setTitle("Freshman");
		} else if (getNumTotalCredits() < 58) {
			setTitle("Sophomore");
		} else if (getNumTotalCredits() < 86) {
			setTitle("Junior");
		} else {
			setTitle("Senior");
		}
	}
	
	public final double getGpa() {
		return this.gpa;
	}
	
	public String toString() {
		String UWECStudent = "UWECStudent = uwecId: " + getUwecId() + ", name: " + getFirstName() + " " + getLastName() + ", title: " + getTitle() + ", credits: "+ getNumTotalCredits() + ", gpa: " + getGpa();
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
			if (!this.getTitle().equals(((UWECPerson) other).getTitle())) {
				return false;
			}
			if (this.getNumTotalCredits() != (((UWECAcademic) other).getNumTotalCredits())) {
				return false;
			}
			if (this.getGpa() != ((UWECStudent) other).getGpa()) {
				return false;
			}
			
		return true;
		
	}
}
