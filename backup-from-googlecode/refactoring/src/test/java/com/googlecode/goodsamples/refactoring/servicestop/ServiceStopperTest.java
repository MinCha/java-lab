package com.googlecode.goodsamples.refactoring.servicestop;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.googlecode.goodsamples.refactoring.servicestop.ServiceStopper.StopType;
import com.googlecode.goodsamples.refactoring.servicestop.support.FailedReadOnlyNotificationException;
import com.googlecode.goodsamples.refactoring.servicestop.support.FailedStopException;
import com.googlecode.goodsamples.refactoring.servicestop.support.ReadOnlyNotifier;
import com.googlecode.goodsamples.refactoring.servicestop.support.Stop;
import com.googlecode.goodsamples.refactoring.servicestop.support.StopMessage;

@RunWith(MockitoJUnitRunner.class)
public final class ServiceStopperTest {
	private final ServiceStopper target = new ServiceStopper();
	private final StopType completeStopType = StopType.COMPLETE_STOP;
	private final StopType readOnlyStopType = StopType.READONLY_STOP;
	private final Boolean success = Boolean.TRUE;
	private final Boolean fail = Boolean.FALSE;

	@Mock
	private Stop completeStop;
	@Mock
	private Stop readOnlyStop;
	@Mock
	private ReadOnlyNotifier readOnlyNotifier;

	@Test
	public void serviceShouldBeStopped_WhenNeedingCompleteStop() {
		when(completeStop.stopNow()).thenReturn(success);

		target.stop(completeStopType, "Completely stop test");

		verify(completeStop).stopNow();
	}

	@Test
	public void serviceShouldBeStopped_WhenNeedingReadOnlyStop() {
		when(readOnlyStop.stopNow()).thenReturn(success);
		when(readOnlyNotifier.getRelatedServices()).thenReturn(
				createRelatedServices());
		when(
				readOnlyNotifier.notifyToRelatedServices(Mockito
						.any(StopMessage.class))).thenReturn(success);

		target.stop(readOnlyStopType, "Stop in read-only test");

		verify(readOnlyStop).stopNow();
		verify(readOnlyNotifier).notifyToRelatedServices(
				Mockito.any(StopMessage.class));
	}

	@Test(expected = FailedReadOnlyNotificationException.class)
	public void whenFailingReadOnlyNotification() {
		when(readOnlyStop.stopNow()).thenReturn(success);
		when(
				readOnlyNotifier.notifyToRelatedServices(Mockito
						.any(StopMessage.class))).thenThrow(
				new FailedReadOnlyNotificationException());

		target.stop(readOnlyStopType, "Some reason");
	}

	@Test(expected = FailedStopException.class)
	public void whenFailingCompleteStop() {
		when(completeStop.stopNow()).thenReturn(fail);

		target.stop(completeStopType, "Complete stop test");
	}

	@Test(expected = FailedStopException.class)
	public void whenFailingReadOnlyStop() {
		when(readOnlyStop.stopNow()).thenReturn(fail);

		target.stop(readOnlyStopType, "Stop in read-only mode test");
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenStopTypeIsNull() {
		final StopType nullStopType = null;
		target.stop(nullStopType, "Some reason");
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenReasonIsNull() {
		final StopType someType = StopType.COMPLETE_STOP;
		final String nullReason = null;
		target.stop(someType, nullReason);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenReasonIsEmpty() {
		final StopType someType = StopType.COMPLETE_STOP;
		final String emptyReason = "";
		target.stop(someType, emptyReason);
	}

	private List<String> createRelatedServices() {
		List<String> relatedServices = new ArrayList<String>();
		relatedServices.add("someService");
		return relatedServices;
	}

	@Before
	public void prepareColloboratorsToTest() {
		target.completeStop = completeStop;
		target.readOnlyStop = readOnlyStop;
		target.readOnlyNotifier = readOnlyNotifier;
	}
}
