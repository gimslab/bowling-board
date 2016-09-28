package bowlingboard;

public class Main {

	public static void main(String[] args) {

		Board board = new Board();
		board.addUser("AAA");
		
		GameController controller = new InteractiveGameController(board);
		controller.startGame();
	}

}
