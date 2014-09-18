package com.github.pagenavigation;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

public class PageUrlMaker {
	private String path;
	private String queryString;
	private String pageExpressionPattern;

	PageUrlMaker(String path, String queryString, String pageExpressionPattern) {
		this.path = path;
		this.queryString = queryString;
		this.pageExpressionPattern = pageExpressionPattern;
	}

	String create(int pageN) {
		return replaceOnlyPageNumberFrom(createUrlTemplate(), pageN);
	}
	
	private String createUrlTemplate() {
		if (StringUtils.isEmpty(queryString)) {
			return path;
		} else {
			queryString = encodeOnlyValuesOf(queryString);
			return path + "?" + queryString;				
		}
	}
	
	private String encodeOnlyValuesOf(String queryString) {
		StringBuilder result = new StringBuilder();
		
		for (String keyAndValue : qs.split("&")) {
			String key = keyAndValue.split("=")[0];
			String value = keyAndValue.split("=")[1];
			try {
				result.append(key).append("=").append(URLEncoder.encode(value, "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				throw new CannotMakePageUrlException(e.toString(), e);
			}
		}
		
		return StringUtils.removeEnd(result.toString(), "&");
	}
	
	private String replaceOnlyPageNumberFrom(String uri, int pageN) {
		String from = toCompleteRegularExpression(pageExpressionPattern);
		String to = pageExpressionPattern.replace(PageNavigation.FIXED_PAGE_N_EXPRESSION_RULE, String.valueOf(pageN));
		return uri.replaceAll(from, to);
	}	

	private String toCompleteRegularExpression(String newPageExpression) {
		return newPageExpression.replace(PageNavigation.FIXED_PAGE_N_EXPRESSION_RULE, "[0-9]+");
	}

}
