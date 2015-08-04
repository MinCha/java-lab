package refactoring.day1.practice09.Etc.ComplicateCondition;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BizCheckTest{
	@Test
	public void testIsTradableOnScheduledDay() throws Exception {
		BizCheck check = new BizCheck();
		List<String> bankList = new ArrayList<String>();
		bankList.add("국민은행");
		bankList.add("제주은행");
		bankList.add("시티은행");

		assertEquals(true, check.isTradable(bankList));
		
		bankList.add("신한은행");
		assertEquals(false, check.isTradable(bankList));
		
		bankList.remove("신한은행");
		assertEquals(true, check.isTradable(bankList));

		bankList.add("외환은행");
		assertEquals(false, check.isTradable(bankList));

	}
}
