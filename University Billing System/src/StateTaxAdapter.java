/**
 * The tax amount returned by the method in StateTaxCalculator class, has
 * to be converted into the integer type to get the total. This class implements the method to do so.
 * @author Labib Rahman
 * */
public class StateTaxAdapter extends SateTaxCalculator implements IExtraFeeCalculator {
	/**
	 * This is an overridden method from the interface IExtraFeeCalculator. This
	 * method calls another method calculateVatAmount(total:int) of the
	 * StateTaxCalculator class to get the tax amount. This third-party tax
	 * calculator returns the tax amount in double but we need it to be an integer
	 * value. So, the adapter class provides a method to convert and returns the tax
	 * amount in integer.
	 * 
	 * @return The state tax fee amount based on the total course fees of a student.
	 */
	
	@Override
	public int getExtraAmount(int courseTotal) {
		int tax = (int) super.calculateVatAmount(courseTotal);
		return tax;
	}

}
