// -------------------------------------------------------
// Assignment 4 Question 1
// Written by: Waleed Iqbal 40112596
// For COMP 248 Section W – Winter 2019
// April 15th, 2019
// --------------------------------------------------------

/*This class has accessors, mutators and toString methods. There are two constructors. 
 * When the toString method is called it returns a String in a specific format.
 */

/*The second part of this class (assignment 4) has a constructor with 2 arguments for 
 * courses that are being taken so grade is IP, a copy constructor and equals method 
 * that returns true if the name and credits of the courses is the same
 */

public class Course 
{
	//Data declaration
	private String name;
	private double credits;
	private String letterGrade;
	
	//Default constructor
	public Course () {
		name = "unknown";
		credits = 3;
		letterGrade = "C";
	}
	
	//Parameterized constructor
	public Course(String aName, double aCredits, String aLetterGrade) 
	{
		name = aName;
		credits = aCredits;
		letterGrade = aLetterGrade;
	}
	
	//Accessor method for the name attribute 
	public String getName() {
		return name;
	}
	
	//Accessor method for the credits attribute
	public double getCredits() {
		return credits;
	}
	
	//Accessor method for the letterGrade attribute
	public String getLetterGrade() {
		return letterGrade;
	}
	
	//Mutator method for the name attribute
	public void setName(String n) {
		name = n;
	}
	
	//Mutator method for the credits attribute
	public void setCredits(double c) {
		credits = c;
	}
	
	//Mutator method for the letterGrade attribute
	public void setLetterGrade(String l) {
		letterGrade = l;
	}
	
	//toString method that returns a String in a specific format
	public String toString() {
		return "Course Name: " + name + "\tNumber of credits: " + credits + "\t  Grade: " + letterGrade;
	}
	
	//Assignment 4 Start
	
	//Creating a constructor with 2 arguments for courses that are being taken so grade is IP
	public Course(String name, double credits) {
		this.name = name;
		this.credits = credits;
		letterGrade = "IP";
	}
	
	//Creating a copy constructor
	public Course(Course aCourse) {
		if (aCourse == null)
			System.exit(0);
		
		name = aCourse.name;
		credits = aCourse.credits;
		letterGrade = aCourse.letterGrade;
	}
	
	//equals method that returns true if the name and credits of the courses is the same
	boolean equals(Course x) {
		return x.name.equalsIgnoreCase(this.name) &&
				x.credits == this.credits;
			
	}
	     
}
