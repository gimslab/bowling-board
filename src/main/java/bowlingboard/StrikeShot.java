package bowlingboard;

import static bowlingboard.GenericFrame.ALL_PIN;

public class StrikeShot implements Shot {

	@Override
	public boolean isStrke() {
		return true;
	}

	@Override
	public int getScore() {
		return ALL_PIN;
	}

	@Override
	public String toString() {
		return "X";
	}
}
