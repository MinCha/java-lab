package com.googlecode.goodsamples.testing;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class JMockArticleBOTest {
	private final Integer articleId = Integer.MAX_VALUE;
	Mockery context = createJMockContext();

	ArticleBO sut = new ArticleBO();

	ArticleDAO articleDAO = context.mock(ArticleDAO.class);
	Article article = context.mock(Article.class);

	@Test
	public void articleShouldBeMoved() {
		context.checking(new Expectations() {
			{
				oneOf(articleDAO).findBy(articleId);
				will(returnValue(article));

				oneOf(article).movable();
				will(returnValue(Boolean.TRUE));

				oneOf(articleDAO).updateArticle(article);
				oneOf(articleDAO).updateAttachedFiles(article);
				oneOf(articleDAO).updateAttachedPics(article);
			}
		});

		sut.moveArticle(articleId);
	}

	@Test
	public void articleShouldBeNotMoved() {
		context.checking(new Expectations() {
			{
				oneOf(articleDAO).findBy(articleId);
				will(returnValue(article));

				oneOf(article).movable();
				will(returnValue(Boolean.FALSE));
			}
		});

		sut.moveArticle(articleId);
	}

	@Before
	public void prepareColloborators() {
		sut.articleDAO = articleDAO;
	}

	private Mockery createJMockContext() {
		return new JUnit4Mockery() {
			{
				setImposteriser(ClassImposteriser.INSTANCE);
			}
		};
	}
}
