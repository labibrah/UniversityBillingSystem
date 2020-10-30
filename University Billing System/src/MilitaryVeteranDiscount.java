/**
 * The university offers discounts to military veterans.
 * This class implements the method to calculate the discount amount which is 25% of the total amount.
 * @author Labib Rahman
 * */
public class MilitaryVeteranDiscount implements IDiscountStrategy {
	/**
	 *  @return The discount amount calculated for a specific student. 
	 *  */
	
	@Override
	public int getTotal(Registration reg) {

		return (int) (reg.getTotal() * 0.25);
	}

}
