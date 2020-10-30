/**
 * The university offers discounts to students who belong to any
 * minority groups. This class implements the method to
 * calculate the discount amount which is 10% of the total amount.
 * @author Labib Rahman
 * */
public class MinorityGroupDiscount implements IDiscountStrategy {
	/**
	 *  @return The discount amount calculated for a specific student. 
	 *  */
	@Override
	public int getTotal(Registration reg) {
		return (int) (reg.getTotal() * 0.10);
	}

}
