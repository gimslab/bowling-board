package bowlingboard;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private List<UserGame> userGames;
	private UserGame firstUser;
	private UserGame lastUser;
	private UserGame nowPlayingUser;

	public Board() {
		userGames = new ArrayList<>();
	}

	public void open() {
		if (userGames.size() < 1)
			throw new NoUserException();
		nowPlayingUser = firstUser;
	}

	public void addUser(String name) {

		UserGame newUser = new UserGame(name);
		firstUser = firstUser == null ? newUser : firstUser;
		if (lastUser != null)
			lastUser.setNextUser(newUser);
		lastUser = newUser;
		lastUser.setNextUser(firstUser);
		userGames.add(newUser);
	}

	public boolean availNextShot() {
		return nowPlayingUser != null && nowPlayingUser.hasAvailShot();
	}

	public UserGame getUserToShot() {
		return nowPlayingUser;
	}

	public void addShot(int score) {
		nowPlayingUser.addShot(score);
		if (nowPlayingUser.frameFinished()) {
			nowPlayingUser = nowPlayingUser.getNextUser();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (UserGame user : userGames)
			sb.append(user.toString()).append("\n");
		return sb.toString();
	}

	public int getUserCount() {
		return userGames.size();
	}

	public int getStadingPins() {
		return nowPlayingUser.getStadingPins();
	}
}
