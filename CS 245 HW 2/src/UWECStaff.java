import java.util.Scanner;

public class UWECStaff extends UWECPerson implements UWECEmployee {
	private double hourlyPay;
	private double hoursPerWeek;
//	Scanner in = new Scanner(assignment2Input1.txt);
	
	public UWECStaff(int uwecId, String firstName, String lastName, String title) {
		super(uwecId, firstName, lastName);
//		this.uwecId = uwecId;
//		this.firstName = firstName;
//		this.lastName = lastName;
	}
	
	public final double getHourlyPay() {
		return this.hourlyPay;
	}
	
	public void setHourlyPay(double hourlyPay) {
		this.hourlyPay = hourlyPay;
	}
	
	public final double getHoursPerWeek() {
		return this.hoursPerWeek;
	}
	
	public void setHoursPerWeek(double hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}
	
	public String toString() {
		String UWECStaff = "UWECStaff = uwecId: " + getUwecId() + ", name: " + getFirstName() + " " + getLastName() + ", title: " + getTitle() + ", hourly pay: " + getHourlyPay() + ", hours/week: " + getHoursPerWeek();
		return UWECStaff;
	}
	
	public double computePaycheck() {
		double paycheck = (getHoursPerWeek() * 2) * getHourlyPay();
		return paycheck;	
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof UWECStaff)) {
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
		if (this.getHourlyPay() != (((UWECStaff) other).getHourlyPay())) {
			return false;
		}
		if (this.getHoursPerWeek() != (((UWECStaff) other).getHoursPerWeek())) {
			return false;
		}
		return true;
		
	}


	
	
}
