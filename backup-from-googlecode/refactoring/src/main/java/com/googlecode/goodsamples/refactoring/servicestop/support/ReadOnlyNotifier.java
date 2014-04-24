package com.googlecode.goodsamples.refactoring.servicestop.support;

import java.util.List;

public class ReadOnlyNotifier {

	public List<String> getRelatedServices() {
		return null;
	}

	public boolean notifyToRelatedServices(StopMessage stopMessage) {
		if (getRelatedServices().size() > 0) {
			//...
		}
		return false;
	}

}
