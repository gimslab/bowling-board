package bowlingboard;

public interface Frame {

	void setNextFrame(Frame newFrame);

	void addShot(int score);

	boolean isClosed();

	Frame nextFrame();
}
