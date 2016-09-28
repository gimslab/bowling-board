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
		if (nowPlayingUser == null) {
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}

	public UserGame getUserToShot() {
		return nowPlayingUser;
	}

	public void addShot(Input input) {
		// TODO Auto-generated method stub

	}

	public String getTextBoard() {
		return "BBBBBBBBBBBBBBBBBBBBBBB";
	}

	public int getUserCount() {
		return userGames.size();
	}
}
