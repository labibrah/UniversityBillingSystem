import java.util.List;

/**
 * A class which represents an instance of an admin who manages the courses offered in a semester and
 * publishes the offered courses list to the students. The admin can increase the seat
 * capacity of a specific course, and can see the status (how many students are registered,
 * seat capacity, etc.) of any of the courses.
 * @author Labib Rahman
*/

public class Admin {
	
	private CurrentOfferedCourse courses = new CurrentOfferedCourse();

	private static IExtraFeeCalculator eFeeCalculator;
	
	/**
	 * Adds a course in the current offered courses list. 
	 * */
	
	public void offerCourse(Course course) {
		courses.addCourse(course);
		eFeeCalculator = new DevelopmentFeeCalculator();
	}

	/** 
	 * Prints all the courses offered in a semester with course id. 
	 * */
	
	public void publishOfferedCourse() {
		if (courses.isEmpty()) {
			System.out.println("No courses have been added by admin yet!");
		} else {
			List<Course> cList = courses.getCourseList();
			int i = 1;
			System.out.println("Current offered courses:");
			for (Course c : cList) {
				System.out.println(i + "." + c.getCourseId());
				i++;
			}
		}
	}
	
	/*
	 * Increases the seat capacity of a specified course by that size.
	 * */
	
	public void increaseSeatCapacity(Course course, int size) {
		if (courses.isEmpty()) {
			System.out.println("Admin needs to add courses to increses their capacity!");
		} else {
			Course courseToIncrease = courses.getCourse(course);
			courseToIncrease.increaseSeatCapacity(this, size);
			System.out.println("Admin has increased capacity of Course: " + course.getCourseId() + " by " + size
					+ " successfully.");
		}
	}

	/*
	 * Prints all the offered courses with course id, number of students enrolled in
	 * and seat capacity of that course.
	 */
	
	public void seeCourseStatus() {
		if (courses.isEmpty()) {
			System.out.println("No courses have been added by admin yet!");
		} else {
			List<Course> currentCourse = courses.getCourseList();
			for (Course c : currentCourse) {
				System.out.println("Course ID: " + c.getCourseId() + ", Seat Capacity: " + c.getSeatCapacity()
						+ ", Number of Students: " + c.getNumOfStudent());
			}
		}

	}

	/*
	 * Either the instance of DevelopmentFeeCalculator or StateTaxAdapter will be
	 * passed as a parameter for this method. This method will set the value of
	 * eFeeCalculator.
	 */
	
	public void setExtraFeeCalculator(IExtraFeeCalculator eFeeCalculator) {
		Admin.eFeeCalculator = eFeeCalculator;
	}
	
	/*
	 * It will return the instance of DevelopmentFee Calculator or StateTaxAdapter.
	 */
	
	public static IExtraFeeCalculator getExtraFeeCalculator() {
		return eFeeCalculator;
	}
}
