package bowlingboard;

import static bowlingboard.FrameResult.OPENED;
import static bowlingboard.FrameResult.SPAIR;
import static bowlingboard.FrameResult.STRIKE;

public class GenericFrame implements Frame {

	public static final int ALL_PIN = 10;

	private Shot first;
	private Shot second;

	private FrameResult result;

	private Frame nextFrame;

	@Override
	public void setNextFrame(Frame f) {
		nextFrame = f;
	}

	@Override
	public void addShot(int score) {

		if (first == null)
			first = score == ALL_PIN ? new StrikeShot() : new GenShot(score);
		else if (second == null)
			second = new GenShot(score);

		if (first.isStrke() || allShotDone())
			decideFrameResult();
	}

	private boolean allShotDone() {
		return first != null && second != null;
	}

	private void decideFrameResult() {
		if (first.isStrke())
			result = STRIKE;
		else if (scoreSum() == ALL_PIN)
			result = SPAIR;
		else
			result = OPENED;
	}

	private int scoreSum() {
		return scoreOf(first) + scoreOf(second);
	}

	private int scoreOf(Shot shot) {
		return shot == null ? 0 : shot.getScore();
	}

	@Override
	public boolean isClosed() {
		return result != null;
	}

	@Override
	public Frame nextFrame() {
		return this.nextFrame;
	}

	@Override
	public String toString() {
		return "(first = " + first + " / second = " + second + " / result = " + result + " / next = " + nextFrame + ")";
	}
}
