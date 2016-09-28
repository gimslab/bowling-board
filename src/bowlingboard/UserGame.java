package bowlingboard;

import java.util.ArrayList;
import java.util.List;

public class UserGame {

	private String userName;
	private List<Frame> frames = new ArrayList<>();

	public UserGame(String userName) {
		this.userName = userName;
	}
}