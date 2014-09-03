package nextnote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class GenericTest {
	Map any = new HashMap();

	@Test
	public void 아무거나_넣을수_있다() {
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
		any.put("1", 1111);
		any.put("1", 1111.0d);
		any.put("1", 1111.0f);
		any.put("1", 1111888888888l);
	}
	
	@Test
	public void List타입에String을넘기면() {
		System.out.println(sortList(Arrays.asList(5,3,1,2,4)));
		//System.out.println(sortList("what is it?"));
	}
	
	@Test
	public void Generic이란() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "String");
		map.put(2, "Only String");
		
		String first = map.get(1);
		Assert.assertEquals("String", first);
		String second = map.get(2);
		Assert.assertEquals("Only String", second);
	}

	private List sortList(List maybe_list) {
		Collections.sort(maybe_list);
		return maybe_list;
	}
}
