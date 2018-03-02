
public class UWECFaculty extends UWECAcademic implements UWECEmployee {
	private double yearlySalary;
	
	public UWECFaculty(int uwecId, String firstName, String lastName, int numTotalCredits) {
		super(uwecId, firstName, lastName);
		setNumTotalCredits(numTotalCredits);
		if (getNumTotalCredits()< 48) {
			setTitle("Adjunct Professor");
		} else if (getNumTotalCredits() < 120) {
			setTitle("Assistant Professor");
		} else if (getNumTotalCredits() < 240) {
			setTitle("Associate Professor");
		} else {
			setTitle("Professor");
		}
	}

	public void setNumTotalCredits(int numTotalCredits) {
		super.setNumTotalCredits(numTotalCredits);
		if (getNumTotalCredits()< 48) {
			setTitle("Adjunct Professor");
		} else if (getNumTotalCredits() < 120) {
			setTitle("Assistant Professor");
		} else if (getNumTotalCredits() < 240) {
			setTitle("Associate Professor");
		} else {
			setTitle("Professor");
		}
	}
	
	public final double getYearlySalary() {
		return this.yearlySalary;
	}
	
	public void setYearlySalary(double yearlySalary) {
		this.yearlySalary = yearlySalary;
	}
	
	public double computePaycheck() {
		return (getYearlySalary() * 1/26);
	}
	
	public String toString() {
		String UWECFaculty = "UWECFaculty = uwecId: " + getUwecId() + ", name: " + getFirstName() + " " + getLastName() + ", title: " + getTitle() + ", credits: " + getNumTotalCredits() + ", yearlySalary: " + getYearlySalary();
		return UWECFaculty;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof UWECFaculty)) {
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
		if (this.getYearlySalary() != ((UWECFaculty) other).getYearlySalary()) {
			return false;
		}
		return true;
	}
	
}
