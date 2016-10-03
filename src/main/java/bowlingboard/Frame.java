package bowlingboard;

import java.util.List;

public interface Frame {

	void setNextFrame(Frame newFrame);

	void addShot(int score);

	boolean isClosed();

	Frame nextFrame();

	int calcScore();

	int getShotCount();

	List<Shot> getShots();

	int getStadingPins();
}
