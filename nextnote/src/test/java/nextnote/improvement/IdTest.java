package nextnote.improvement;

import junit.framework.Assert;

import org.junit.Test;

public class IdTest {
	@Test
	public void 노트_아이디를_발급받을수있다() {
		NoteId first = NoteId.issue();
		
		Assert.assertEquals(first.id() + 1, NoteId.issue().id());
		Assert.assertEquals(first.id() + 2, NoteId.issue().id());
	}

	@Test
	public void 카테고리_아이디를_발급받을수있다() {
		CategoryId first = CategoryId.issue();
		
		Assert.assertEquals(first.id() + 1, CategoryId.issue().id());
		Assert.assertEquals(first.id() + 2, CategoryId.issue().id());
	}
}
