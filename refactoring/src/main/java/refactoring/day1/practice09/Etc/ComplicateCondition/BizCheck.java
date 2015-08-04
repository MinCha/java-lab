
package refactoring.day1.practice09.Etc.ComplicateCondition;

import java.util.Iterator;
import java.util.List;

public class BizCheck {
	
	public boolean isTradable(List bankList){
		boolean isTradable = true;
		
		// 자동이체일 마이너스 거래 가능 검사
		for (Iterator iterator = bankList.iterator(); iterator.hasNext();) {
			if ( isTradable == true ){
				String bankName = (String) iterator.next();
				if ( bankName.equals("신한은행")){
					isTradable = false;
					System.out.println( "신한은행은 자동이체 당일 마이너스 거래 불가한 은행입니다.");
				} else if ( bankName.equals("외환은행")){
					isTradable = false;
					System.out.println( "외환은행은 자동이체 당일 마이너스 거래 불가한 은행입니다.");
				}
			}
		}
		return isTradable;
	}
}
