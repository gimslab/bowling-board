package bowlingboard;

import static bowlingboard.FrameResult.NOT_STARTED;
import static bowlingboard.FrameResult.OPENED;
import static bowlingboard.FrameResult.SPAIR;
import static bowlingboard.FrameResult.STRIKE;
import static com.google.common.base.Preconditions.checkArgument;

public class GenericFrame implements Frame {

	public static final int ALL_PIN = 10;

	private Shot first;
	private Shot second;

	private Frame prevFrame;
	private Frame nextFrame;

	public GenericFrame(Frame prev) {
		this.prevFrame = prev;
	}

	@Override
	public void setNextFrame(Frame f) {
		nextFrame = f;
	}

	@Override
	public void addShot(int score) {

		if (first == null) {
			first = score == ALL_PIN ? new StrikeShot() : new GenShot(score);
		} else if (second == null) {
			int sum = first.getScore() + score;
			checkArgument(sum <= ALL_PIN, "over max pin score : " + sum);
			second = sum == ALL_PIN ? new SpairShot(score) : new GenShot(score);
		}
	}

	private boolean allShotDone() {
		return first.isStrke() || first != null && second != null;
	}

	@Override
	public boolean isClosed() {
		return allShotDone();
	}

	@Override
	public Frame nextFrame() {
		return this.nextFrame;
	}

	@Override
	public String toString() {
		return "[" + null2str(first) + "," + null2str(second) + ":" + calcScore() + "]";
	}

	private FrameResult getResult() {
		if (first == null && second == null)
			return NOT_STARTED;
		else if (first != null && first.isStrke())
			return STRIKE;
		else if (second != null && second instanceof SpairShot)
			return SPAIR;
		return OPENED;
	}

	public int calcScore() {

		if (!calcEnabled())
			return 0;

		return getPrevFrameScore()
			+ getScoreOfThisFrameOnly()
			+ getExtraScoreOfShot(
				getResult().getExtraShotCountToScore());
	}

	private int getPrevFrameScore() {
		return prevFrame == null ? 0 : prevFrame.calcScore();
	}

	private int getScoreOfThisFrameOnly() {
		return scoreOf(first) + scoreOf(second);
	}

	private int scoreOf(Shot shot) {
		return shot == null ? 0 : shot.getScore();
	}

	private int getExtraScoreOfShot(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	private boolean calcEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	private String null2str(Object o) {
		return o != null ? o.toString() : "-";
	}
}
