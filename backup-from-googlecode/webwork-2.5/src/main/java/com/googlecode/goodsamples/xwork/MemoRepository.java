package com.googlecode.goodsamples.xwork;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MemoRepository {
	private List<Memo> memos = new ArrayList<Memo>();

	public List<Memo> findAll() {
		return memos;
	}

	public void persist(Memo memo) {
		memos.add(memo);
	}
}
