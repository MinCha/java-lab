package nextnote.learning;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

@SuppressWarnings({"rawtypes", "unchecked"})
public class GenericTest {
	@Test
	public void 아무거나_넣을수_있다() {
		Map any = new HashMap();
		any.put("1", new String("Walwal"));
		any.put("2", new Integer(1));
		
		String result = (String) any.get("1");
		Assert.assertEquals("Walwal", result);

		Object unknown = any.get("2");
		if (unknown instanceof Integer) {
			Integer someNumber = (Integer) unknown;
			System.out.println(someNumber);
		} else if (unknown instanceof String) {
			String someString = (String) unknown;			
			System.out.println(someString);
		}
	}

	@Test
	public void primitive타입빼고는_다넣을수_있다() {
		Map any = new HashMap();
		any.put("1", 1111);
		any.put("1", 1111.0d);
		any.put("1", 1111.0f);
		any.put("1", 1111888888888l);
	}
	
	@Test
	// 이클립스에서 보면 위에서 노란색으로 warning이 발생하는데, 아래 소스코드는 warning이 발생하지 않는다.
	public void Generic이란무엇일까() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "String");
		map.put(2, "Only String");
		
		String first = map.get(1);
		Assert.assertEquals("String", first);
		String second = map.get(2);
		Assert.assertEquals("Only String", second);
	}
}
