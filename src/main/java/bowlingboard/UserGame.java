package bowlingboard;

import java.util.ArrayList;
import java.util.List;

public class UserGame {

	private static final int FRAME_SIZE = 10;

	private String userName;
	private UserGame nextUser;

	private List<Frame> frames;
	private Frame currentFrame;
	private boolean turnFinished;

	public UserGame(String userName) {
		this.userName = userName;
		initFrames();
		this.turnFinished = false;
	}

	private void initFrames() {
		frames = new ArrayList<>();
		Frame prev = new GenericFrame(null);
		frames.add(prev);
		for (int i = 1; i < FRAME_SIZE - 1; i++) {
			Frame newFrame = new GenericFrame(prev);
			frames.add(newFrame);
			prev.setNextFrame(newFrame);
			prev = newFrame;
		}

		LastFrame newFrame = new LastFrame(prev);
		frames.add(newFrame);
		prev.setNextFrame(newFrame);
		prev = newFrame;

		currentFrame = frames.get(0);
	}

	public String getName() {
		return userName;
	}

	public void setNextUser(UserGame user) {
		nextUser = user;
	}

	public UserGame getNextUser() {
		return nextUser;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof UserGame)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		return userName.equals(((UserGame) obj).userName);
	}

	public void addShot(int score) {
		turnFinished = false;
		currentFrame.addShot(score);
		if (currentFrame.isClosed()) {
			currentFrame = currentFrame.nextFrame();
			turnFinished = true;
		}
	}

	public boolean frameFinished() {
		return turnFinished;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(userName).append(" : ");
		for (Frame f : frames)
			sb.append(f.toString());
		return sb.toString();
	}

	public int getStadingPins() {
		return currentFrame.getStadingPins();
	}

	public boolean hasAvailShot() {
		return currentFrame != null;
	}
}
