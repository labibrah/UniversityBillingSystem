/**
 * This class provides us a means to calculate the
 * development fee which will be added on the student’s bill.
 * @author Labib Rahman
 * */
public class DevelopmentFeeCalculator implements IExtraFeeCalculator {
	
	/**
	 * This is an overridden method from the interface IExtraFeeCalculator.
	 * @return The development fee amount based on the total course fees of a
	 * student.
	 */
	@Override
	public int getExtraAmount(int courseTotal) {
		int fee = (int) (courseTotal * 0.10);
		return fee;
	}

}
