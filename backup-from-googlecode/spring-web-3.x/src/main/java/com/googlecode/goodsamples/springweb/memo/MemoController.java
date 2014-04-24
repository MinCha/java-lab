package com.googlecode.goodsamples.springweb.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemoController {
	private MemoService memoService;

	@Autowired
	public MemoController(MemoService memoService) {
		this.memoService = memoService;
	}

	public ModelAndView memolist(int page) {
		assertThatPageIsValid(page);

		ModelAndView result = new ModelAndView();
		result.addObject("memos", memoService.findBy(page));
		return result;
	}

	private void assertThatPageIsValid(int page) {
		int mimimumPage = 1;
		if (page < mimimumPage) {
			throw new IllegalArgumentException("Invalid page : " + page);
		}
	}
}
