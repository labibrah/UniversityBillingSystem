/**
 * A class representing a course offered by the University. Each course consists of id, title, credit, 
 * tuition per credit, number of students, and seat capacity. 
 * In order to get each course’s fee, the tuition per credit is multiplied by the credits of that course. 
 * @author Labib Rahman
*/

public class Course {
	private String id;
	private String title;
	private int credit;
	private int tuitionPerCredit;
	private int numOfStudent;
	private int seatCapacity = 3;

	public Course(String id, String title, int credit, int tuitionPerCredit) {
		this.id = id;
		this.title = title;
		this.credit = credit;
		this.tuitionPerCredit = tuitionPerCredit;

	}

	// getters:
	public String getCourseId() {
		return this.id;
	}

	public String getCourseTitle() {
		return this.title;
	}

	public int getCourseCredit() {
		return this.credit;
	}

	public int getCourseTuitionPerCredit() {
		return this.tuitionPerCredit;
	}

	public int getNumOfStudent() {
		return this.numOfStudent;
	}

	public int getSeatCapacity() {
		return this.seatCapacity;
	}

	public void setNumberOfStudent(int num) {
		numOfStudent = num;
	}
	
	/**
	 * Increase the seat capacity of this course.
	 * 
	 * @param An instance of Admin class
	 */
	
	public void increaseSeatCapacity(Object o, int newCapacity) {
		if (o instanceof Admin) {
			this.seatCapacity += newCapacity;
		} else {
			System.out.println("Only admin has access to increase seat capacity!");
		}
	}
	
	/**
	 * @return The total fee for a specific course 
	 * */

	public int getSubTotal() {
		return this.credit * this.tuitionPerCredit;

	}

	public String toString() {
		String output = "Course ID: " + this.id + "Course Name: " + this.title;
		return output;
	}

}
