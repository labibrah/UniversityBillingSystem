import java.util.List;
import java.util.NoSuchElementException;

/**
 * Each object of this class represents a student. 
 * It consists of name, id, cgpa, and status (military or veteran and minority group) information. 
 * If a student qualifies under a specific status such being a veteran or a particular minority group, 
 * then we use the characters ‘Y’/‘N’ to specify the statuses.
 *  This Student class also uses the Registration class as one of its instance variable types 
 *  to keep track of the Registration information of a student. 
 *  When a student registers for course(s), a Registration object is created and that reference will be 
 *  assigned to the student’s instance variable of Registration type. 
 *  When a student adds or drops a course, his/her Registration object gets updated. 
 *  A student may be eligible for multiple discounts like Academic Excellence (CGPA > 3.5) and Military and Veteran discount, 
 *  in that case, his/her cgpa will determine if he/she is eligible for Academic Excellence discount or not and the other discount criteria will be determined by the student’s statuses. 
 *  All the applicable discounts will be added to the student’s Registration object.
 *  @author Labib Rahman
 *  */

public class Student {
	private String name;
	private int id;
	private double cgpa;
	private char militaryVeteranStatus;
	private char minorityGroupStatus;
	private Registration reg;

	public Student(String name, int id, double cgpa, char militaryVeteranStatus, char minorityGroupStatus) {
		this.name = name;
		this.id = id;
		this.cgpa = cgpa;
		this.militaryVeteranStatus = militaryVeteranStatus;
		this.minorityGroupStatus = minorityGroupStatus;

	}

	public String getStudentName() {
		return this.name;
	}

	public int getStudentId() {
		return this.id;
	}

	public double getStudentCgpa() {
		return this.cgpa;
	}

	
	/**
	 * Once a student starts his/her course registration process for a semester, an
	 * instance of Registration gets created for that student. This method creates a
	 * Registration object for a student.
	 */
	
	public void makeNewRegistration() {
		System.out.println(this.name + ":" + " You are now registered to take courses");
		reg = new Registration();
	}

	/**
	 * A student will be able to add a course by this method. All the registration
	 * related tasks of a student are handled by the Registration class. When a
	 * course is added by a student, it is basically added to the ArrayList<Course>
	 * in the Registration class. It is checked that the student is eligible
	 * for adding a course as there is a credit limit for students depending on CGPA.
	 * @param Course to add
	 */
	
	public void addCourse(Course course) {
		if (reg.noOfCreditsReg() < 12) {
			reg.addCourse(course);
		} else if (reg.noOfCreditsReg() >= 12) {
			if (this.cgpa >= 3.5) {
				if (reg.noOfCreditsReg() == 18) {
					System.out.println(this.name + ":" + " You can not take " + course.getCourseId()
							+ " You exceed the 18 credits limit");
				} else {
					if (course.getNumOfStudent() < course.getSeatCapacity()) {
						reg.addCourse(course);
					} else {
						System.out.println("Sorry this course has no more seats left");
					}

				}
			} else {
				System.out.println(this.name + ":" + " You can not take " + course.getCourseId()
						+ " You exceed the 12 credits limit");
			}
		}

	}
	
	/**
	 * Similar to addCourse() method,we decrease the number of students for that course and 
	 * delete that course from the ArrayList<Course>
	 * @param Course to be deleted
	 * */
	
	public void dropCourse(Course course) {
		course.setNumberOfStudent(course.getNumOfStudent() - 1);
		reg.deleteCourse(course);

	}

	/**
	 * @return Registration object of a student which was created during
	 * makeRegistration() method call.
	 */
	
	public Registration getRegistration() {
		if (this.reg == null) {
			System.out.println("Student has not registered yet!");
			throw new NoSuchElementException();
		} else {
			return this.reg;
		}
	}

	/**
	 * @return the course id and course title of all the registered courses of a student.
	 * */
	
	public String printRegisteredCourse() {
		List<Course> coursesRegistered = reg.getCourseList();
		StringBuilder coursesInfo = new StringBuilder();
		coursesInfo.append("Course ID:\tCourse Title:\n");
		coursesInfo.append("================================================\n");
		for (Course c : coursesRegistered) {
			coursesInfo.append(c.getCourseId() + "\t\t" + c.getCourseTitle() + "\n");
		}
		coursesInfo.append("================================================");
		return coursesInfo.toString();
	}

	/*
	 * Sets different discounts applicable for a student. In the Registration class,
	 * an ArrayList<IDiscountStrategy> is there for holding different instances of
	 * discount classes. So, in this method, we check which discounts which can be applied
	 * to a student and pass the corresponding discount class’ instance to the
	 * setApplicableDiscounts.(discountStrategy:IDiscountStrategy) method of
	 * Registration class.
	 */
	
	public void setDiscounts() {
		IDiscountStrategy d;
		if (this.cgpa > 3.5) {
			d = new AcademicExcellenceDiscount();
			reg.setApplicableDiscounts(d);
		} else if (this.militaryVeteranStatus == 'Y') {
			d = new MilitaryVeteranDiscount();
			reg.setApplicableDiscounts(d);
		} else if (this.minorityGroupStatus == 'Y') {
			d = new MinorityGroupDiscount();
			reg.setApplicableDiscounts(d);
		}

	}
	
	/** @return Total breakdown of all fees */

	public String getBillingInfo() {
		this.setDiscounts();
		StringBuilder billingInfo = new StringBuilder();
		billingInfo.append("Billing Info: (ID: " + this.id + ")\n");
		billingInfo.append("================================================\n");
		billingInfo.append("Total Courses Fees:  " + (int) reg.getTotal() + "\n");
		billingInfo.append("Extra Fee:          +" + reg.getExtraFeeAmount() + "\n");
		billingInfo.append("================================================\n");
		billingInfo.append("Grand Total:         " + reg.getGrandTotal() + "\n");
		billingInfo.append("Discount:           -" + reg.getDiscountAmount() + "\n");
		billingInfo.append("================================================\n");
		billingInfo.append("Payable Amount:      " + reg.getPayableAmount() + "\n");
		return billingInfo.toString();
	}

	/*
	 * Prints all the basic information of a student including the billing info and
	 * courses registered for.
	 */
	public void printRegistrationSlip() {
		System.out.println("Registration Time: " + reg.getLocalDateTime());
		System.out.println();
		System.out.println(this.toString());
		System.out.println(printRegisteredCourse());
		System.out.println();
		System.out.println(this.getBillingInfo());

	}

	/** 
	 * @return Basic information of a student 
	 * */
	public String toString() {
		return "Name: " + this.name + ", ID: " + this.id + ", CGPA: " + this.cgpa;
	}
}
