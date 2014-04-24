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
public class MemoServiceBOTest {
	MemoService sut = new MemoService();
	@Mock
	MemoRepository memoRepository;

	@Test
	public void shouldReturnMemosOnRepository() {
		List<Memo> memosOnRepository = new ArrayList<Memo>();
		when(memoRepository.findAll()).thenReturn(memosOnRepository);

		List<Memo> result = sut.memos();

		assertThat(result, is(sameInstance(memosOnRepository)));
	}

	@Before
	public void prepareColloborators() {
		sut.memoRepository = memoRepository;
	}
}
