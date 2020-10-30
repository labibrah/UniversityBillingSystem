
public class Test {

	public static void main(String[] args) {
		Course CS50 = new Course("CS50", "Introduction to Computer Science", 3, 6000);
		Course CS229 = new Course("CS229", "Machine Learning", 3, 6000);
		Course CSE215 = new Course("CS215", "Programming Language-II", 3, 6000);
		Course CSE225 = new Course("CS225", "Data Structures and Algorithms", 3, 6000);
		Course CSE231 = new Course("CS231", "Digital Logic Design", 3, 6000);
		Course CSE311 = new Course("CS311", "Database Systems", 3, 6000);
		Course CSE323 = new Course("CS323", "Operating Systems Design", 3, 6000);
		Course CSE373 = new Course("CS373", "Design and Analysis of Algorithms", 3, 6000);

		Student s1 = new Student("John Smith", 1631728042, 2.70, 'Y', 'N');
		Student s2 = new Student("David Johnson", 1821347042, 3.44, 'N', 'Y');
		Student s3 = new Student("Karim Mohammad", 2021746042, 3.65, 'N', 'N');
		Student s4 = new Student("Kobe Bryant", 1923147042, 3.94, 'N', 'N');
		Student s5 = new Student("James Brown", 1524137042, 2.14, 'Y', 'Y');

		Admin ad = new Admin();

		ad.offerCourse(CS50);
		ad.offerCourse(CS229);
		ad.offerCourse(CSE215);
		ad.offerCourse(CSE225);
		ad.offerCourse(CSE231);
		ad.offerCourse(CSE311);
		ad.offerCourse(CSE323);
		ad.offerCourse(CSE373);
		// ad.publishOfferedCourse();

		s1.makeNewRegistration();
		s2.makeNewRegistration();
		s3.makeNewRegistration();

		s1.addCourse(CS50);
		s1.addCourse(CS229);

		s2.addCourse(CS50);
		s2.addCourse(CSE215);
		s2.addCourse(CSE225);

		s3.addCourse(CS50);
		s3.addCourse(CSE225);
		s3.addCourse(CSE311);

		// ad.seeCourseStatus();

		s4.makeNewRegistration();
		// s4.addCourse(CSE115);
		s4.addCourse(CSE225);

		s5.makeNewRegistration();
		// s5.addCourse(CSE115);
		s5.addCourse(CS229);
		s5.addCourse(CSE215);

		ad.increaseSeatCapacity(CS50, 2);

		s5.addCourse(CS50);
		s4.addCourse(CS50);

		// ad.seeCourseStatus();

		s3.addCourse(CS229);
		s3.addCourse(CSE215);
		// s3.addCourse(CSE231);
		// s3.addCourse(CSE323);

		s5.addCourse(CSE311);
		// s5.addCourse(CSE373);

		// ad.seeCourseStatus();

		// System.out.println(s3.printRegisteredCourse());

		// Task 1: Print the billing info for s1. Call the printBillingInfo() method on
		// s1.
		System.out.println(s1.getBillingInfo());
		// Task 2: Print the billing info for s2. Call the printBillingInfo() method on
		// s2.
		System.out.println(s2.getBillingInfo());
		// Task 3:Call the setExtraFeeCalculator() method of the Admin class and pass
		// new BDTaxAdapter() as the parameter.
		IExtraFeeCalculator tax = new StateTaxAdapter();
		ad.setExtraFeeCalculator(tax);
		// Task 4: Print the billing info for s3. Call the printBillingInfo() method on
		// s3
		System.out.println(s3.getBillingInfo());
		// Task 5: Print the billing info for s4.Call the printBillingInfo() method on s4
		System.out.println(s4.getBillingInfo());
		//Task 6: Print the Registration slip for s5. Call the printRegistrationSlip() method on s5.
		 s5.printRegistrationSlip();
		 
		// System.out.println("Course list of s1");
		/*
		 * for(Course c: s1.getRegistration()) { System.out.println(c.getCourseTitle());
		 * }
		 */
		// Course list of s2
		//

	}

}
