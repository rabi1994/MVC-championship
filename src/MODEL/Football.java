package MODEL;

import Exceptions.FootballException;
import Exceptions.Only1HalfResultException;
import Exceptions.TieException;

public class Football extends Game implements GameType {

	public Football(Player player1, Player player2) {
		super(player1, player2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startGame(int[] player1Results, int[] player2Results) throws FootballException, TieException {
		this.isPlayed = true;
		if (player1Results.length == 1)
			throw new Only1HalfResultException("not a full game Exception");
		int Player1FullGamesGoals = player1Results[0] + player1Results[1];
		int Player2FullGamesGoals = player2Results[0] + player2Results[1];

		if (Player1FullGamesGoals > Player2FullGamesGoals) {
			this.setWinner(player1);
		} else if (Player1FullGamesGoals < Player2FullGamesGoals) {
			this.setWinner(player2);
		} else if (player1Results.length == 2)
			throw new TieException("can't be tie !\nthe game must be decided");
		else if (player1Results[2] > player2Results[2])
			this.setWinner(player1);
		else if (player1Results[2] < player2Results[2])
			this.setWinner(player2);
		else if (player1Results.length == 3)
			throw new TieException("can't be tie !\nthe game must be decided");

		else if (player1Results[3] > player2Results[3])
			this.setWinner(player1);
		else if (player1Results[3] < player2Results[3])
			this.setWinner(player2);
		else
			throw new TieException("can't be tie !\nthe game must be decided");

	}

	public void setWinner(Player winner) {
		if (winner == player1) {
			player1.setWinner(true);
			player2.setWinner(false);
		} else {
			player2.setWinner(true);
			player1.setWinner(false);
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
