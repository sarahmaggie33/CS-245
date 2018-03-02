
public class UWECPerson {
	protected int uwecId;
	protected String firstName;
	protected String lastName;

	public UWECPerson(int uwecId, String firstName, String lastName) {
		this.uwecId = uwecId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getUwecId() {
		return this.uwecId;
	}

	public void setUwecId(int uwecId) {
		this.uwecId = uwecId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString() {
		String UWECPerson = "UWECPerson = uwecId: " + getUwecId() + ", name: " + getFirstName() + " " + getLastName();
		return UWECPerson;
	}

	public boolean equals(Object other) {
		if (!(other instanceof UWECPerson)) {
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
		return true;

	}

}
