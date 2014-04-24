package com.googlecode.goodsamples.refactoring.servicestop.support;

public class ReadOnlyStop implements Stop {
	public boolean stopNow() {
		return false;
	}
}
