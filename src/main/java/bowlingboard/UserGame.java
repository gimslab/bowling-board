package bowlingboard;

import java.util.ArrayList;
import java.util.List;

public class UserGame {

	private String userName;
	private UserGame nextUser;

	private List<Frame> frames;

	public UserGame(String userName) {
		this.userName = userName;
		frames = new ArrayList<>();
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

	@Override
	public String toString() {
		return userName;
	}
	
}