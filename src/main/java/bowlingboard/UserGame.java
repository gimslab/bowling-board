package bowlingboard;

import java.util.ArrayList;
import java.util.List;

public class UserGame {

	private static final int FRAME_SIZE = 10;

	private String userName;
	private UserGame nextUser;

	private List<Frame> frames;
	private Frame currentFrame;
	private boolean finished;

	public UserGame(String userName) {
		this.userName = userName;
		initFrames();
		this.finished = false;
	}

	private void initFrames() {
		frames = new ArrayList<>();
		Frame prev = new GenericFrame();
		frames.add(prev);
		for (int i = 1; i < FRAME_SIZE; i++) {
			Frame newFrame = new GenericFrame();
			frames.add(newFrame);
			prev.setNextFrame(newFrame);
			prev = newFrame;
		}
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
		if (currentFrame == null) {
			currentFrame = frames.get(0);
		}
		finished = false;
		currentFrame.addShot(score);
		if (currentFrame.isClosed()) {
			currentFrame = currentFrame.nextFrame();
			finished = true;
		}
	}

	public boolean frameFinished() {
		return finished;
	}

	@Override
	public String toString() {
		return "userName = " + userName
			+ "\nnextUser = " + nextUser.getName()
			+ "\ncurrentFrame = " + (currentFrame != null ? currentFrame.toString() : currentFrame)
			+ "\nfinished = " + finished;
	}

}