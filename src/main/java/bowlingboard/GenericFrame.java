package bowlingboard;

import static bowlingboard.FrameResult.NOT_FINISHED;
import static bowlingboard.FrameResult.NOT_STARTED;
import static bowlingboard.FrameResult.OPENED;
import static bowlingboard.FrameResult.SPAIR;
import static bowlingboard.FrameResult.STRIKE;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.FluentIterable;

public class GenericFrame implements Frame {

	public static final int ALL_PIN = 10;

	protected Shot first;
	protected Shot second;

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
		return "[" + null2str(first) + "," + null2str(second) + ":" + getFrameScore() + "]";
	}

	private FrameResult getResult() {
		if (first == null && second == null)
			return NOT_STARTED;
		else if (first != null && first.isStrke())
			return STRIKE;
		else if (second != null && second instanceof SpairShot)
			return SPAIR;
		else if (first != null && second != null)
			return OPENED;
		return NOT_FINISHED;
	}

	protected String getFrameScore() {

		return calcEnabled() ? String.format("%3s", calcScore()) : " - ";
	}

	public int calcScore() {

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

	private int getExtraScoreOfShot(int n) {
		List<Shot> shots = getShotsOf(nextFrame);
		shots.addAll((getShotsOf(nextFrameOf(nextFrame))));
		return sumOfScore(
			from(shots).limit(n));

	}

	private int sumOfScore(FluentIterable<Shot> shots) {
		int s = 0;
		for (Shot shot : shots)
			s += shot.getScore();
		return s;
	}

	private List<Shot> getShotsOf(Frame f) {
		return f == null ? Collections.<Shot> emptyList() : f.getShots();
	}

	private boolean calcEnabled() {
		if (getResult() == OPENED) {
			return true;
		}
		if (getResult() == STRIKE && (shotCountOf(nextFrame) + shotCountOf(nextFrameOf(nextFrame))) > 1) {
			return true;
		}
		if (getResult() == SPAIR && shotCountOf(nextFrame) > 0)
			return true;
		return false;
	}

	private int shotCountOf(Frame f) {
		return f == null ? 0 : f.getShotCount();
	}

	private Frame nextFrameOf(Frame f) {
		return f == null ? null : f.nextFrame();
	}

	protected String null2str(Object o) {
		return o != null ? o.toString() : "-";
	}

	@Override
	public int getShotCount() {
		switch (getResult()) {
			case NOT_STARTED:
				return 0;
			case NOT_FINISHED:
			case STRIKE:
				return 1;
			case SPAIR:
			case OPENED:
				return 2;
			default:
				return 0;
		}
	}

	@Override
	public List<Shot> getShots() {
		switch (getResult()) {
			case NOT_FINISHED:
			case STRIKE:
				return newArrayList(first);
			case SPAIR:
			case OPENED:
				return newArrayList(first, second);
			case NOT_STARTED:
			default:
				return Collections.emptyList();
		}
	}

	@Override
	public int getStadingPins() {
		switch (getResult()) {
			case NOT_STARTED:
				return ALL_PIN;
			case NOT_FINISHED:
				return ALL_PIN - first.getScore();
			case STRIKE:
			case SPAIR:
			case OPENED:
			default:
				return 0;
		}
	}
}
