package nextnote.learning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ListTest {
	@Test
	public void 링크드리스트에서_Index의_동작을_확인한다() {
		List sut = new LinkedList();
		sut.add(1);
		sut.add(2);
		sut.add(3);
		
		Assert.assertEquals(1, sut.get(0));
		Assert.assertEquals(2, sut.get(1));
		Assert.assertEquals(3, sut.get(2));
		try {
			Assert.assertEquals(null, sut.get(3)); // Exception이 발생하는데 왜 발생하는걸까?
			Assert.fail(); // 무조건 실패한다는 의미이다.
		} catch (IndexOutOfBoundsException e) {
		}
	}
	
	@Test
	public void LinkedList에서_만약_5개아이템이_있었는데_아이템을_지우면_Index는_어떻게될까() {
		List sut = new LinkedList();
		sut.add(1);
		sut.add(2);
		sut.add(3);

		Assert.assertEquals(1,sut.remove(0));
		Assert.assertEquals(2,sut.remove(0));
		Assert.assertEquals(3,sut.remove(0));		
		Assert.assertEquals(0, sut.size());
	}

	@Test
	public void ArrayList에서_만약_5개아이템이_있었는데_아이템을_지우면_Index는_어떻게될까() {
		List sut = new ArrayList();
		sut.add(1);
		sut.add(2);
		sut.add(3);

		Assert.assertEquals(1,sut.remove(0));
		Assert.assertEquals(2,sut.remove(0));
		Assert.assertEquals(3,sut.remove(0));		
		Assert.assertEquals(0, sut.size());
	}

}
