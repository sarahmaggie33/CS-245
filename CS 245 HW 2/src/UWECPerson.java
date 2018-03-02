
public abstract class UWECPerson {
	private int uwecId;
	private String firstName;
	private String lastName;
	private String title;

	public UWECPerson(int uwecId, String firstName, String lastName) {
		this.uwecId = uwecId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public final int getUwecId() {
		return this.uwecId;
	}

	public final String getFirstName() {
		return this.firstName;
	}

	public final String getLastName() {
		return this.lastName;
	}
	
	public final String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public abstract String toString();

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
		if (!this.getTitle().equals(((UWECPerson) other).getTitle())){
			return false;
		}
		return true;

	}

}
