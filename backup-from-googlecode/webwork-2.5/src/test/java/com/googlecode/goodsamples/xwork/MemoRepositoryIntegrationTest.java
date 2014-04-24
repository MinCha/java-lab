package com.googlecode.goodsamples.xwork;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MemoRepositoryIntegrationTest {
	MemoRepository sut = new MemoRepository();

	@Test
	public void shouldFindAllMemos_WhenThrerIsNoMemo() {
		assertThat(sut.findAll(), is(notNullValue()));
	}

	@Test
	public void shouldFindAllMemos_WhenThereIsOneMemo() {
		sut.persist(new Memo("Some memo"));

		List<Memo> result = sut.findAll();

		int oneBecuaseThereIsPersistedOneMemo = 1;
		assertThat(result.size(), is(oneBecuaseThereIsPersistedOneMemo));
	}
}
