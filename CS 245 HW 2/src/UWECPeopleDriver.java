import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UWECPeopleDriver {
	
	private static int option;
	private static String fileOutSelected;
	private static String fileInSelected;
	private static double paycheck;

	public static void main(String[] args) {
		Scanner fileIn = null;
		PrintWriter fileOut = null;
		ArrayList<UWECPerson> universityPeople;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the name of the input file: ");
		fileInSelected = in.nextLine();
		File fileGoesIn = new File(fileInSelected);
		try {
			fileIn = new Scanner(fileGoesIn);
		} catch (FileNotFoundException e1) {
			System.err.println("The input file does not exist.");
		}
		
		System.out.println("Enter the name of the output file: ");
		fileOutSelected = in.nextLine();
		File fileGoesOut = new File(fileOutSelected);
		try {
			fileOut = new PrintWriter(fileGoesOut);
		} catch (FileNotFoundException e) {
			System.err.println("The output file does not exist.");
		}
		in.close();
		
		universityPeople = new ArrayList<UWECPerson>();
		
		while (fileIn.hasNextLine() && option != 6) {
			getMenuChoice(fileIn);
			if (option < 6 && option > 0) { 

				if (option == 1) {
					addStudent(fileIn, universityPeople);
				} else if (option == 2) {
					addStaff(fileIn, universityPeople);
				} else if (option == 3) {
					addFaculty(fileIn, universityPeople);
				} else if (option == 5) {
					printDirectory(fileOut, universityPeople);
				} else if (option == 4) {
					computeTotalPayroll(fileOut, universityPeople);
				} else if (option == 6) {
					System.out.println("");
				}
				
			}
		}
		
		
		fileIn.close();
		fileOut.close();
	}

	public static void addStudent(Scanner fileIn, ArrayList<UWECPerson> universityPeople) {
		fileIn.nextLine();
		int uwecId = 0;
		String firstName = null;
		String lastName = null;
		int totalCredits = 0;
		double gpa = 0;
		
		try {
			uwecId = fileIn.nextInt();
			firstName = fileIn.next();
			lastName = fileIn.next();
			totalCredits = fileIn.nextInt();
			gpa = fileIn.nextDouble();
			UWECStudent student = new UWECStudent(uwecId, firstName, lastName, gpa);
			student.setNumTotalCredits(totalCredits);
			student.setTitle(student.getTitle());
			universityPeople.add(student);

		} catch (InputMismatchException e) {
			System.err.println("Unexpected input received in addStudent.");
			fileIn.nextLine();
		}
		
	}

	public static void addStaff(Scanner fileIn, ArrayList<UWECPerson> universityPeople) {
		int uwecId = 0;
		String firstName = null;
		String lastName = null;
		String title = null;
		double hourlyPay = 0;
		double hoursPerWeek = 0;
		
		fileIn.nextLine();
		try {
			uwecId = fileIn.nextInt();
			firstName = fileIn.next();
			lastName = fileIn.next();
			title = fileIn.next();
			hourlyPay = fileIn.nextDouble();
			hoursPerWeek = fileIn.nextDouble();
			UWECStaff member = new UWECStaff(uwecId, firstName, lastName, title);
			member.setHourlyPay(hourlyPay);
			member.setTitle(title);
			member.setHoursPerWeek(hoursPerWeek);
			paycheck += member.computePaycheck();
			universityPeople.add(member);
		} catch (InputMismatchException e) {
			System.err.println("Unexpected input received in addStaff.");
			fileIn.nextLine();
		}	
		
	}

	public static void addFaculty(Scanner fileIn, ArrayList<UWECPerson> universityPeople) {
		int uwecId = 0;
		String firstName = null;
		String lastName = null;
		String title = null;
		int numTotalCredits = 0;
		double yearlySalary = 0;
		fileIn.nextLine();
		try {
			uwecId = fileIn.nextInt();
			firstName = fileIn.next();
			lastName = fileIn.next();
			numTotalCredits = fileIn.nextInt();
			yearlySalary = fileIn.nextDouble();
			UWECFaculty member = new UWECFaculty(uwecId, firstName, lastName, numTotalCredits);
			title = member.getTitle();
			member.setTitle(title);
			member.setNumTotalCredits(numTotalCredits);
			member.setYearlySalary(yearlySalary);
			paycheck += member.computePaycheck();
			universityPeople.add(member);
			
		} catch (InputMismatchException e) {
			System.err.println("Unexpected input received in addFaculty.");
			fileIn.nextLine();
		}

	}

	public static void printDirectory(PrintWriter fileOut, ArrayList<UWECPerson> universityPeople) {
		for (int i = 0; i < universityPeople.size(); i++) {
			UWECPerson person = universityPeople.get(i);
			String thePerson = person.toString();
			fileOut.println(thePerson);
		}
		
	}

	public static void computeTotalPayroll(PrintWriter fileOut, ArrayList<UWECPerson> universityPeople) {
		fileOut.println("Total payroll: $" + paycheck);
	}

	public static int getMenuChoice(Scanner fileIn) {
		try {
			option = fileIn.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Non-integer entered in getMenuChoice.");
			option = 6;
		}
		
		return option;
		
	}

}
