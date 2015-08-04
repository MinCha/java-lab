package refactoring.day1.practice04.DivergentChange;

public class Prescription {

	// 처방전 문구 
	public String getPrescription(String type, String status) {
		String result = null;
		
		if(status.equals("serious") ){	// status 에는 serious 와 mild 두 가지가 있음
			if ( type.equals("adult") ){ // type 에는 adult 와 child 두 가지가 있음
				result = "two pill a day";
			} else if ( type.equals("child") ){
				result = "one pill a day";
			} else {
				throw new IllegalArgumentException("invalid argument");
			}
		} else if(status.equals("mild") ){	// status 에는 serious 와 mild 두 가지가 있음
			if ( type.equals("adult") ){ // type 에는 adult 와 child 두 가지가 있음
				result = "one pill a day";
			} else if ( type.equals("child") ){
				result = "half pill a day";
			} else {
				throw new IllegalArgumentException("invalid argument");
			}
		}
		return result;
	}

	// 1일 최대 권장복용량
	public double getMaxIngestionPerDay(String type, String status) {
		double result = 0;
		
		if(status.equals("serious") ){	// status 에는 serious 와 mild 두 가지가 있음
			if ( type.equals("adult") ){ // type 에는 adult 와 child 두 가지가 있음
				result = 2;
			} else if ( type.equals("child") ){
				result = 1;
			} else {
				throw new IllegalArgumentException("invalid argument");
			}
		} else if(status.equals("mild") ){	// status 에는 serious 와 mild 두 가지가 있음
			if ( type.equals("adult") ){ // type 에는 adult 와 child 두 가지가 있음
				result = 1;
			} else if ( type.equals("child") ){
				result = 0.5;
			} else {
				throw new IllegalArgumentException("invalid argument");
			}
		}
		return result;
	}
}
