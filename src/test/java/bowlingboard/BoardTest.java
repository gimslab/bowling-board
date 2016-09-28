package bowlingboard;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BoardTest {

	private Board sut = new Board();

	@Test(expected = NoUserException.class)
	public void open_noUser() throws Exception {
		sut.open();
	}

	@Test
	public void open() throws Exception {
		sut.addUser("a");
		sut.open();
		assertThat(sut.getUserToShot().getName(), equalTo("a"));
	}

	@Test
	public void addUser() throws Exception {
		sut.addUser("a");
		sut.open();
		assertThat(sut.getUserCount(), equalTo(1));
		assertThat(sut.getUserToShot().getName(), equalTo("a"));
		assertThat(sut.getUserToShot().getNextUser(), equalTo(sut.getUserToShot()));
	}

	@Test
	public void addUser_2() throws Exception {
		sut.addUser("a");
		sut.addUser("b");
		sut.open();
		assertThat(sut.getUserCount(), equalTo(2));
		assertThat(sut.getUserToShot().getNextUser().getName(), equalTo("b"));
	}

	@Test
	public void addUser_3() throws Exception {
		sut.addUser("a");
		sut.addUser("b");
		sut.addUser("c");
		sut.open();
		assertThat(sut.getUserCount(), equalTo(3));
		assertThat(sut.getUserToShot().getNextUser().getName(), equalTo("b"));
	}

	@Test
	public void availNextShot_noUser() throws Exception {
		assertThat(sut.availNextShot(), equalTo(false));
	}

	@Test
	public void availNextShot_notOpen() throws Exception {
		sut.addUser("a");
		assertThat(sut.availNextShot(), equalTo(false));
	}

	@Test
	public void availNextShot() throws Exception {
		sut.addUser("a");
		sut.open();
		assertThat(sut.availNextShot(), equalTo(true));
	}

}
