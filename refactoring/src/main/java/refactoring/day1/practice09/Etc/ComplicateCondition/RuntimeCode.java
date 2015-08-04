package refactoring.day1.practice09.Etc.ComplicateCondition;

import java.util.ArrayList;
import java.util.List;

public class RuntimeCode {

	public static void main(String[] args) {
		BizCheck check = new BizCheck();
		List bankList = new ArrayList();
		bankList.add("국민은행");
		bankList.add("제주은행");
		bankList.add("시티은행");
		bankList.add("신한은행");

		if ( check.isTradable(bankList) ) {
			System.out.println("거래진행을 계속합니다");
		} else {
			System.out.println("거래진행을 중단합니다.");
		}
	}

}
