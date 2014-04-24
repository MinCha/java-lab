package com.googlecode.goodsamples.testing;

/**
 * <p>
 * This source comes from some production code and then is modified to clarify a test scenario.
 * </p>
 */
public class ArticleBO {
	ArticleDAO articleDAO;

	public void moveArticle(Integer articleId) {
		Article article = articleDAO.findBy(articleId);

		if (article.movable()) {
			articleDAO.updateArticle(article);
			articleDAO.updateAttachedFiles(article);
			articleDAO.updateAttachedPics(article);
		}
	}

}
