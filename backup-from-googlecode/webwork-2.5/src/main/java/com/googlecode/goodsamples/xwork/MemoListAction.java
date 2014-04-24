package com.googlecode.goodsamples.xwork;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork.ActionSupport;

@SuppressWarnings("serial")
public class MemoListAction extends ActionSupport {
	@Autowired
	MemoService memoService;

	public List<Memo> getMemos() {
		return memoService.memos();
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}
}
