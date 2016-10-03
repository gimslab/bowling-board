package bowlingboard;

import java.util.Scanner;

public class InteractiveGameController implements GameController {

	private Board board;
	private Scanner s;

	public InteractiveGameController(Board board) {
		this.board = board;
		this.s = new Scanner(System.in);
	}

	@Override
	public void startGame() {

		board.open();

		while (board.availNextShot()) {

			Input input = prompt(
				board.getStadingPins(),
				"--------\n" + board.getUserToShot().getName() + "! input score [(0..9) (x)10 (f)oul (g)utter (q)uit / standing="
					+ board.getStadingPins() + " ] = ?");
			System.out.println("your input : " + input);

			if (input.hasError()) {
				System.out.println("your input has error !!!");
				continue;
			}
			if (input.isQuit()) {
				System.out.println("quit");
				break;
			}

			board.addShot(input.getScore());

			System.out.println(board.toString());
		}
	}

	private Input prompt(int maxAvailablePins, String msg) {
		System.out.print(msg);
		try {
			return new Input(maxAvailablePins, s.next());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return Input.withError();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (s != null) {
			s.close();
		}
	}
}
