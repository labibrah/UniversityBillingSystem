import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Whenever a student wants to register for the course(s) in a specific 
 * semester, a object of this class gets created for that student. This object keeps track of
 * all the registration-related information for that student. When a student adds a course, this
 * gets added to the ArrayList<Course> courseList of Registration class. This class is used
 * to store the registered course(s) of a student. Using the instance of this class, we can get
 * the total amount, which is the fee for the courses a student registered for. Not only to
 * determine the course fees, but this class also provides useful methods to get the extra fee
 * and discount amount for that specific instance. This class is also used
 * to store the eligible discount criteria of a student into an ArrayList<IDiscountStrategy> applicableDisounts.
 * @author Labib Rahman
 * */

public class Registration implements Iterable<Course> {
	private List<Course> courseList;
	private IExtraFeeCalculator eFeeCalculator;
	
	private List<IDiscountStrategy> applicableDiscounts; //stores the eligible discount criteria of a student 
	private LocalDateTime date;

	public Registration() {
		courseList = new ArrayList<>();
		date = LocalDateTime.now();
		applicableDiscounts = new ArrayList<IDiscountStrategy>();

	}

	/**
	 * @return The date and time of registration for a student. 
	 * */
	
	public String getLocalDateTime() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = date.format(format);
		return formatDateTime;
	}

	public int noOfCreditsReg() {
		int sum = 0;
		for (Course c : courseList) {
			sum += c.getCourseCredit();
		}
		return sum;
	}

	/**
	 * This method will add a course to a student’s course list.
	 */
	
	public void addCourse(Course course) {
		CurrentOfferedCourse courses = new CurrentOfferedCourse();
		boolean courseOffered = false;

		if (course.getNumOfStudent() < course.getSeatCapacity()) {
			courseOffered = true;
			courseList.add(course);
			course.setNumberOfStudent(course.getNumOfStudent() + 1);
		} else {
			System.out.println(course.getCourseId() + " cannot be added. Seats are full!");
		}

	}
	
	/**
	 * @return ArrayList containing all the courses a student registered for
	 * in a semester.
	 */
	
	public List<Course> getCourseList() {
		if (courseList.size() == 0) {
			System.out.println("You have not registered for any courses yet!");
			throw new NoSuchElementException();

		} else {
			return courseList;
		}

	}

	/**
	 * This method will remove a specific course from the student’s course list.
	 */	
	
	public void deleteCourse(Course course) {
		if (courseList.contains(course)) {
			courseList.remove(course);
			System.out.println("Course: " + course.getCourseId() + " has been successfully removed.");
			course.setNumberOfStudent(course.getNumOfStudent() + 1);
		} else {
			System.out.println("You have not registered for the course you are trying to remove.");
		}

	}

	/**
	 * Adds instances of discount classes to the ArrayList<IDiscountStrategy>
	 * applicableDiscounts.
	 * @param Instances of discount classes
	 */
	
	public void setApplicableDiscounts(IDiscountStrategy discountStrategy) {
		applicableDiscounts.add(discountStrategy);

	}
	
	/**
	 * A student may be eligible for multiple discounts, and in that case, the
	 * university will apply the one which is the greatest through this method.
	 * 
	 * @return The discount amount for a student based on his/her credentials
	 */
	
	public int getDiscountAmount() {
		int academicExcellenceDiscount = 0;
		int  militaryVeteranDiscount  = 0;
		int minorityGroupDiscount = 0;

		for (Object o : applicableDiscounts) {
			if (o instanceof AcademicExcellenceDiscount) {
				academicExcellenceDiscount = ((AcademicExcellenceDiscount) o).getTotal(this);
			} else if (o instanceof MilitaryVeteranDiscount) {
				 militaryVeteranDiscount  = ((MilitaryVeteranDiscount) o).getTotal(this);
			} else if (o instanceof MinorityGroupDiscount) {
				minorityGroupDiscount = ((MinorityGroupDiscount) o).getTotal(this);
			}
		}
		return Math.max(Math.max(academicExcellenceDiscount, militaryVeteranDiscount), minorityGroupDiscount);
	}
	
	/**
	 * Each course has a fee depending on its credit and tuition per credit. So, a
	 * total amount is calculated by adding all the registered course fees.
	 * @return A total amount based on a student’s registered courses.
	 */
	
	public double getTotal() {
		double total = 0;
		for (Course c : courseList) {
			total += c.getSubTotal();
		}
		return total;
	}

	/**
	 * Calls the getExtraFeeCalculator() of Admin class and that method returns the
	 * instance of DevelopmentFeeCalculator or StateTaxAdapter depending on which
	 * was set by the admin. By using this instance, we can call the
	 * getExtraAmount(courseTotal : int) method to get the fee.
	 * 
	 * @return The extra fee that will be applied by the University depending on a
	 * specific semester.
	 */
	
	public int getExtraFeeAmount() {
		IExtraFeeCalculator eFeeCalculator = Admin.getExtraFeeCalculator();
		if (eFeeCalculator == null) {
			return 0;
		}
		return eFeeCalculator.getExtraAmount((int) (getTotal()));
	}

	/** 
	 * @return The grand total amount for a specific student. 
	 * */
	
	public int getGrandTotal() {
		double grandTotal = this.getTotal() + this.getExtraFeeAmount();
		return (int) grandTotal;
	}

	public int getPayableAmount() {
		double payable = this.getGrandTotal() - this.getDiscountAmount();
		return (int) payable;
	}

	@Override
	public Iterator<Course> iterator() {
	
		return courseList.iterator();
	}

}
