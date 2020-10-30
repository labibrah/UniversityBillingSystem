/**
 * The university gives academic discounts to students. Discounts are given to students who have CGPA > 3.5. 
 * This class implements the method to calculate the discount amount which is 20% of the total amount.
 * @author Labib Rahman
*/
public class AcademicExcellenceDiscount implements IDiscountStrategy {
	/**
	 *  @return The discount amount calculated for a specific student. 
	 *  */
	public int getTotal(Registration reg) {
		return (int) (reg.getTotal() * 0.20);
	}
}
