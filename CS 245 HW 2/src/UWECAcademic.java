
public abstract class UWECAcademic extends UWECPerson {
	private int numTotalCredits;
	
	public UWECAcademic(int uwecId, String firstName, String lastName) {
		super(uwecId, firstName, lastName);
	}
	
	public final int getNumTotalCredits() {
		return this.numTotalCredits;
	}
	
	public void setNumTotalCredits(int numTotalCredits) {
		this.numTotalCredits = numTotalCredits;
	}
	
	public abstract String toString();
	
	public boolean equals(Object other) {
		if (!(other instanceof UWECAcademic)) {
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
		if (this.getNumTotalCredits() != ((UWECAcademic) other).getNumTotalCredits()) {
			return false;
		}
		if (!this.getTitle().equals(((UWECPerson) other).getTitle())){
			return false;
		}
		return true;
	}
	
}
