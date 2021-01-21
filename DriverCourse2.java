// -------------------------------------------------------
// Assignment 4 Question 1
// Written by: Waleed Iqbal 40112596
// For COMP 248 Section W – Winter 2019
// April 15th, 2019
// --------------------------------------------------------

/*This program creates 2 arrays of object Course for two different students and adds courses for
 * each student using the different constructors of the Course class and using the other methods
 * to do different tasks like displaying the classes, checking if the two students have similar 
 * classes and prompting the user to enter a class they want to check if a student is taking.
 */

//Importing Scanner
import java.util.Scanner;

public class DriverCourse2 {

	public static void main(String[] args) {
		
		//Initializing Scanner
		Scanner kb = new Scanner(System.in);
		
		//Creating 2 arrays of objects for 2 students with a max of 30 courses
		Course[] studenta = new Course[30];
		Course[] studentb = new Course[30];
		
		//Adding 1st course for Student A using default constructor
		studenta[0] = new Course();
		studenta[0].setName("Engr213");
		studenta[0].setCredits(3.0);
		studenta[0].setLetterGrade("A-");
		
		//Adding 2nd course for Student A
		studenta[1] = new Course("Comp248", 3.5, "A");
		
		//Adding 3rd course for Student A
		studenta[2] = new Course("Comp249", 3.5);
		
		//Adding 4th course for Student A
		studenta[3] = new Course("Comp232", 3.0, "B+");
		
		//Adding 1st course for Student B
		studentb[0] = new Course("Comp248", 3.5);
		
		//Adding 2nd course for Student B using copy constructor
		studentb[1] = new Course(studenta[3]);
		
		//Adding 3rd course for Student B
		studentb[2] = new Course("Soen287", 3.0, "B");
		
		//Displaying Student A's courses using toString
		System.out.println("Courses on Student A's list:");
		for (int i = 0; i < studenta.length; i++)
		{
			if (studenta[i]==null)
				break;
			System.out.println(studenta[i]);
		}
		System.out.println();
		
		//Displaying Student B's courses using accessors
		System.out.println("Courses on Student B's list:");
		for (int i = 0; i < studentb.length; i++) 
		{
			if (studentb[i]==null)
				break;
			System.out.println("Course name: " + studentb[i].getName() + "\tNumber of credits: "
					+ studentb[i].getCredits() + "\tGrade: " + studentb[i].getLetterGrade());
		}
		System.out.println();
		
		//Checking if both students are taking the same course(s) and displaying them or a message if no same courses
		System.out.println("List of courses from Student A's list in Student B's list:");
		boolean notcommon = true;
		for (int j = 0; j < studenta.length; j++)
		{
			if (studenta[j]==null)
				break;
			for (int k = 0; k < studentb.length; k++)
			{
				if (studentb[k]==null)
					break;
				if (studentb[k].equals(studenta[j]))
				{
					System.out.println(studentb[k].getName());
					notcommon = false;
				}
			}
		}
		if (notcommon)
			System.out.println("No courses in common.");
		System.out.println();
		
		//Declaring 2 strings for user input
		String checka, checkb;
		//Prompting user to enter the course to check and checking if Student A is taking it
		System.out.println("First course to check for Student A (no spaces please)?");
		checka = kb.nextLine();
		boolean notparta = true;
		for (int l = 0; l < studenta.length; l++)
		{
			if (studenta[l]==null)
				break;
			if (checka.equalsIgnoreCase(studenta[l].getName()))
			{
				System.out.println("Course "+checka+" is part of Student A's courses.");
				notparta = false;
			}
		}
		if (notparta)
			System.out.println("Course "+checka+" is not part of Student A's courses.");
		System.out.println();
		//Prompting user to enter a course and checking if Student B is taking it
		System.out.println("First course to check for Student B (no spaces please)?");
		checkb = kb.nextLine();
		boolean notpartb = true;
		for (int m = 0; m < studenta.length; m++)
		{
			if (studentb[m]==null)
				break;
			if (checkb.equalsIgnoreCase(studentb[m].getName()))
			{
				System.out.println("Course "+checkb+" is part of Student B's courses.");
				notpartb = false;
			}
		}
		if (notpartb)
			System.out.println("Course "+checkb+" is not part of Student B's courses.");
		
		//Closing Scanner
		kb.close();

	}

}
