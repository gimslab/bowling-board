package bowlingboard;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserGameTest {

	@Test
	public void equals_null() throws Exception {
		UserGame a = new UserGame("a");
		assertThat(a.equals(null), equalTo(false));
	}

	@Test
	public void equals_otherType() throws Exception {
		UserGame a = new UserGame("a");
		assertThat(a.equals("a"), equalTo(false));
	}

	@Test
	public void equals_saleInstance() throws Exception {
		UserGame a = new UserGame("a");
		assertThat(a.equals(a), equalTo(true));
	}

	@Test
	public void equals_diffName() throws Exception {
		UserGame a = new UserGame("a");
		assertThat(a.equals(new UserGame("b")), equalTo(false));
	}

	@Test
	public void equals_sameName() throws Exception {
		UserGame a = new UserGame("a");
		assertThat(a.equals(new UserGame("a")), equalTo(true));
	}

	@Test
	public void equals_subClass() throws Exception {
		UserGame a = new UserGame("a");
		assertThat(a.equals(new UserGameSub("a")), equalTo(true));
	}

	private static class UserGameSub extends UserGame {
		public UserGameSub(String userName) {
			super(userName);
		}
	}

}
