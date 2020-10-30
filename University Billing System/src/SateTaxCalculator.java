/**
 * The State Tax will be calculated by this class and returns the tax amount in double.
 * StateTaxAdapter: The tax amount returned by the method in this class is converted into the integer type in the StateTaxAdapter class. 
 * @author Labib Rahman
*/
public class SateTaxCalculator {
	/**
	 *  @return The tax amount calculated for a specific student. 
	 *  */
	
	public static double calculateVatAmount(int courseTotal) {
		int tax = (int) (courseTotal * 0.15);
		return tax;
	}

}
