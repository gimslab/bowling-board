package bowlingboard;

public class GenShot implements Shot {

	private int score;

	public GenShot(int score) {
		this.score = score;
	}

	@Override
	public boolean isStrke() {
		return false;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return score + "";
	}
}