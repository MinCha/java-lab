package refactoring.day1.practice03.LongParameterList.PreserveWholeObject;

public class TempRange {
	private int low;
	private int high;

	public TempRange(int high, int low) {
		this.high = high;
		this.low = low;
	}
	
	public static TempRange basicTempRange(){
		TempRange range = new TempRange(18, 12);
		return range;
	}
	
	public int getHigh() {
		return high;
	}
	
	public void setHigh(int high) {
		this.high = high;
	}
	
	public int getLow() {
		return low;
	}
	
	public void setLow(int low) {
		this.low = low;
	}

}
