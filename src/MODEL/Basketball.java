package MODEL;

import Exceptions.TieException;

public class Basketball extends Game implements GameType {

	public Basketball(Player player1, Player player2) {
		super(player1, player2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startGame(int[] player1Results, int[] player2Results) throws TieException {
		this.isPlayed = true;
		int player1FullGamesGoals = 0;
		int player2FullGamesGoals = 0;
		for (int i = 0; i < 4; i++) {
			player1FullGamesGoals += player1Results[i];
			player2FullGamesGoals += player2Results[i];
		}

		if (player1FullGamesGoals > player2FullGamesGoals) {
			this.setWinner(player1);
		} else if (player1FullGamesGoals < player2FullGamesGoals) {
			this.setWinner(player2);
		}
		else {
			throw new TieException("game must be decided !");
		}

	}

	public void setWinner(Player winner) {
		if (winner == player1) {
			player1.setWinner(true);
			player2.setWinner(false);
		} else {
			player1.setWinner(false);
			player2.setWinner(true);
		}

	}



	@Override
	public Player getWinner() {
		// TODO Auto-generated method stub
		if (player1.isWinner())
			return player1;
		else
			return player2;
	}

}
