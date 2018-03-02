import java.util.ArrayList;
import java.util.Scanner;

public class UWECPeopleDriver {
	private static ArrayList<UWECStudent> students;
	private static ArrayList<UWECStaff> staff;
	private static int option;
	
	public static void main(String[] args) {
		Scanner stdln = new Scanner(System.in);
		students = new ArrayList<UWECStudent>();
		staff = new ArrayList<UWECStaff>();
		getMenuChoice(stdln);
		while (option < 6 && option > 0 && option != 5) {
			getMenuChoice(stdln);
		}
	}
	
	public static void addStudent(Scanner stdln, ArrayList<UWECStudent> students) {
		System.out.print("\nEnter id: ");
		int uwecId = stdln.nextInt();
		System.out.print("Enter first name: ");
		String firstName = stdln.next();
		System.out.print("Enter last name: ");
		String lastName = stdln.next();
		System.out.print("Enter gpa: ");
		double gpa = stdln.nextDouble();
		UWECStudent student = new UWECStudent(uwecId, firstName, lastName, gpa);
		students.add(student);
	}
	
	public static void addStaff(Scanner stdln, ArrayList<UWECStaff> staff) {
		System.out.print("\nEnter id: ");
		int uwecId = stdln.nextInt();
		System.out.print("Enter first name: ");
		String firstName = stdln.next();
		System.out.print("Enter last name: ");
		String lastName = stdln.next();
		System.out.print("Enter title: ");
		String title = stdln.next();
		System.out.print("Enter hourly pay: ");
		double hourlyPay = stdln.nextDouble();
		System.out.print("Enter hours per week: ");
		double hoursPerWeek = stdln.nextDouble();
		UWECStaff member = new UWECStaff(uwecId, firstName, lastName);
		member.setTitle(title);
		member.setHourlyPay(hourlyPay);
		member.setHoursPerWeek(hoursPerWeek);
		staff.add(member);
	}
	
	public static void printStudents(ArrayList<UWECStudent> students) {
		if (!students.isEmpty()) {
			System.out.println(" ");
			for (int ii = 0; ii < students.size(); ii++) {
				System.out.println(students.get(ii));
			}
		} else {
			System.out.println("\nNo students found.");
		}
	}
	
	public static void printStaff(ArrayList<UWECStaff> staff) {
		if (!staff.isEmpty()) {
			System.out.println(" ");
			for (int ii = 0; ii < staff.size(); ii++) {
				System.out.println(staff.get(ii));
			}
		} else {
			System.out.println("\nNo staff found.");
		}
	}
	
	public static int getMenuChoice(Scanner stdln) {
		System.out.println("\n+++++++++++++++++++++++++\n+ 1.  Add Student       +\n+ 2.  Add Staff         +\n+ 3.  Print Students    +\n+ 4.  Print Staff       +\n+ 5.  Quit              +\n+++++++++++++++++++++++++");
		System.out.print("Enter an option (1-5): ");
		option = stdln.nextInt();
		while (option > 5 || option < 1) { 
			System.out.print("Enter an option (1-5): ");
			option = stdln.nextInt();
		}
		if (option == 1) {
			addStudent(stdln, students);
		}
		if (option == 2) {
			addStaff(stdln, staff);
		}
		if (option == 3) {
			printStudents(students);
		}
		if (option == 4) {
			printStaff(staff);
		}
		if(option == 5) {
			System.out.println("");;
		}
		return option;
	}
	
}
