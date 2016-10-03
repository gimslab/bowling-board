package bowlingboard;

public class Input {

	private static final String QUIT = "Q";
	private static final String ERROR = "E";
	private static final String FOUL = "F";
	private static final String GUTTER = "G";
	private static final String ZERO_MARKS = FOUL + GUTTER + ERROR + QUIT;
	private static final String TEN = "X";
	private static final String AVAIL = "01234567890" + TEN + ZERO_MARKS;

	private String mark = null;
	private int score = 0;

	public Input(int maxAvailablePins, String str) {
		if (str == null | str.trim().length() < 1) {
			throw new IllegalArgumentException("not available chars - " + str + " / available chars are : " + AVAIL);
		}
		String firstChar = str.substring(0, 1).toUpperCase();
		if (AVAIL.indexOf(firstChar) < 0) {
			throw new IllegalArgumentException("not available chars - " + firstChar + " / available chars are : " + AVAIL);
		}
		this.mark = firstChar;
		if (ZERO_MARKS.indexOf(mark) > -1)
			this.score = 0;
		else if (TEN.equals(mark))
			this.score = 10;
		else
			this.score = Integer.parseInt(mark);
		if (score > maxAvailablePins)
			throw new IllegalArgumentException("over the limit : " + maxAvailablePins);
	}

	public static Input withError() {
		return new Input(0, ERROR);
	}

	public boolean hasError() {
		return ERROR.equals(mark);
	}

	@Override
	public String toString() {
		return mark + "(" + score + ")";
	}

	public boolean isQuit() {
		return QUIT.equals(mark);
	}

	public int getScore() {
		return score;
	}
}
