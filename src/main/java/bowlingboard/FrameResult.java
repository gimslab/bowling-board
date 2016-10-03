package bowlingboard;

public enum FrameResult {

	NOT_STARTED(0),
	STRIKE(2),
	SPAIR(1),
	OPENED(0),
	NOT_FINISHED(0);

	private int extraShotCountToScore;

	private FrameResult(int extraShotCountToScore) {
		this.extraShotCountToScore = extraShotCountToScore;
	}

	public int getExtraShotCountToScore() {
		return extraShotCountToScore;
	}
}
