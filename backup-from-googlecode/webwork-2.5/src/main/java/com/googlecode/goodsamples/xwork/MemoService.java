package com.googlecode.goodsamples.xwork;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoService {
	@Autowired
	MemoRepository memoRepository;

	public List<Memo> memos() {
		return memoRepository.findAll();
	}

}
