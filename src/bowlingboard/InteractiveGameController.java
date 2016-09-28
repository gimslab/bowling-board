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
		while (board.availNextShot()) {
			Input input = prompt(board.getCurrentUser() + "! your shot result [(0..9) / (s)trike / (f)oul / (g)utter / (q)uit ] = ?");
			System.out.println("your input : " + input);
			if (input.hasError()) {
				System.out.println("your input has error !!!");
				continue;
			}
			if (input.isQuit()) {
				System.out.println("quit");
				break;
			}
			board.addShot(input);
			System.out.println(board.getTextBoard());
		}
	}

	private Input prompt(String msg) {
		System.out.println(msg);
		try {
			return new Input(s.next());
		} catch (IllegalArgumentException e) {
			System.out.println(e);
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