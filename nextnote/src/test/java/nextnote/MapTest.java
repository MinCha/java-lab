package nextnote;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

public class MapTest {
	@Test
	public void Map을_여러구현체로_생성할수있다() {
		Map hashMap = new HashMap();
		Map treeMap = new TreeMap();
	}

	@Test
	public void Map에_아이템을_넣을수있다() {
		Map sut = new HashMap();
		
		sut.put("next", "학생");
		sut.put("kakao", "직장인");
		
		Assert.assertEquals("학생", sut.get("next"));
		Assert.assertEquals("직장인", sut.get("kakao"));
	}

	@Test
	public void Map에_키가_없는걸_GET하면_어떻게될까() {
		Map sut = new HashMap();
		
		sut.put("next", "학생");
		sut.put("kakao", "직장인");
		
		Assert.assertNull(sut.get("nhn"));
	}
	
	@Test
	public void Map에_아이템을_삭제할수있다() {
		Map sut = new HashMap();
		sut.put("next", "학생");
		sut.put("kakao", "직장인");
		
		sut.remove("next");
		
		Assert.assertNull(sut.get("next"));
		Assert.assertEquals("직장인", sut.get("kakao"));
	}

	@Test
	public void HashMap과_TreeMap은_어떤차이가있는걸까() {
		Map hash = new HashMap();
		Map tree = new TreeMap();
			for (int i = 0; i < 50; i++) {
			int seed = new Random().nextInt(100);
			hash.put(seed, seed);
			tree.put(seed, seed);
		}
		
		System.out.println(hash.values());
		System.out.println(tree.values());
	}
}
