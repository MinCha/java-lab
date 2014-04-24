package com.googlecode.goodsamples.testing;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockitoArticleBOTest {
	private final Integer articleId = Integer.MAX_VALUE;

	ArticleBO sut = new ArticleBO();

	@Mock
	ArticleDAO articleDAO;
	@Mock
	Article article;

	@Test
	public void articleShouldBeMoved() {
		when(articleDAO.findBy(articleId)).thenReturn(article);
		when(article.movable()).thenReturn(Boolean.TRUE);

		sut.moveArticle(articleId);

		verify(articleDAO).updateArticle(article);
	}

	@Test
	public void articleShouldBeNotMoved() {
		when(articleDAO.findBy(articleId)).thenReturn(article);
		when(article.movable()).thenReturn(Boolean.FALSE);

		sut.moveArticle(articleId);
	}

	@Before
	public void prepareColloborators() {
		sut.articleDAO = articleDAO;
	}
}
