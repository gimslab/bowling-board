package bowlingboard;

public class LastFrame extends GenericFrame {

	private Shot lastShot;

	public LastFrame(Frame prev) {
		super(prev);
	}

	@Override
	public String toString() {
		return "[" + null2str(first) + "," + null2str(second) + "," + null2str(lastShot) + ":" + getFrameScore() + "]";
	}
}
