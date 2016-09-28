package bowlingboard;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private List<UserGame> userGames = new ArrayList<>();

	public void addUser(String name) {
		userGames.add(new UserGame(name));
	}

	public boolean availNextShot() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getCurrentUser() {
		return "userX";
	}

	public void addShot(Input input) {
		// TODO Auto-generated method stub
		
	}

	public String getTextBoard() {
		return "BBBBBBBBBBBBBBBBBBBBBBB";
	}
}
