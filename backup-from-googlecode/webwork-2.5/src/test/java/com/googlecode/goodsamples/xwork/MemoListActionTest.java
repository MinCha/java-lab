package com.googlecode.goodsamples.xwork;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MemoListActionTest {
	MemoListAction sut = new MemoListAction();
	@Mock
	MemoService memoService;

	@Test
	public void shouldExposeMemos() {
		List<Memo> memos = new ArrayList<Memo>();
		when(memoService.memos()).thenReturn(memos);

		List<Memo> result = sut.getMemos();

		assertThat(result, is(notNullValue()));
		assertThat(result, is(sameInstance(memos)));
	}

	@Test
	public void shouldReturnSuccess() throws Exception {
		String result = sut.execute();

		assertThat(result, is(com.opensymphony.xwork.Action.SUCCESS));

	}

	@Before
	public void prepareColloborators() {
		sut.memoService = memoService;
	}
}
