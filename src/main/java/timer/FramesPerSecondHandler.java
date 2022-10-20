package timer;

import wrappers.SystemWrapper;

public class FramesPerSecondHandler {

	protected static final int THREAD_SLEEP_LAG_DELAY = 8;
	protected static final int MICROSECONDS_IN_A_SECOND = 1000000;
	private static final int NANOSECONDS_IN_A_SECOND = 1000000000;
	private final SystemWrapper systemWrapper;
	long now;
	long last;
	int desiredFramesPerSecond;
	long framePeriodNS;
	long nextFrameNanoseconds;
	long timeToSleepMilliseconds;

	public FramesPerSecondHandler(int desiredFramesPerSecond, SystemWrapper systemWrapper) {
		this.systemWrapper = systemWrapper;
		this.desiredFramesPerSecond = desiredFramesPerSecond;
		framePeriodNS = NANOSECONDS_IN_A_SECOND / desiredFramesPerSecond;
		last = 0;
	}

	public boolean hasEnoughTimeElapsed() {
		now = systemWrapper.nanoTime();
		return now - last >= framePeriodNS;
	}

	public void resetLastRunTimer() {
		last = now;
	}

	public long calculateSleepDurationInMilliSeconds() {
		nextFrameNanoseconds = last + framePeriodNS;
		timeToSleepMilliseconds = (long) Math
				.floor((nextFrameNanoseconds - systemWrapper.nanoTime()) / (double) MICROSECONDS_IN_A_SECOND)
				- THREAD_SLEEP_LAG_DELAY;
		if (timeToSleepMilliseconds > 0) {
			return timeToSleepMilliseconds;
		} else {
			return 0;
		}
	}

}
