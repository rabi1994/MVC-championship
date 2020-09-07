package MODEL;

import Exceptions.NotFiveGamesException;
import Exceptions.NotThreeOrFiveException;
import Exceptions.TennisException;
import Exceptions.TieException;

public class Tennis extends Game implements GameType {

	public Tennis(Player player1, Player player2) {
		super(player1, player2);

	}

	@Override
	public void startGame(int[] player1Results, int[] player2Results) throws TennisException, TieException {
		// TODO Auto-generated method stub
		isPlayed = true;
		int player1Counter = 0;
		int player2Counter = 0;
		boolean threeGames = true;
		for (int i = 0; i < player1Results.length; i++) {
			if (((int) (Math.abs((double) (player1Results[i] - player2Results[i])))) < 3) {
				threeGames = false;
			}

			if (player1Results[i] > player2Results[i])
				player1Counter++;
			else if (player1Results[i] < player2Results[i])
				player2Counter++;
			

		}
		if (!(player1Results.length == 5 || player1Results.length == 3))
			throw new NotThreeOrFiveException("Erorr !\ngames number not fitting for Tennis");
		if ((threeGames == false) && (player1Results.length < 5)) {
			throw new NotFiveGamesException("you must play 5 matches \nyou have 1 game with less than 3 diffrence");
		}
		if (player1Counter > player2Counter) {
			this.setWinner(player1);
		}
		else if (player1Counter < player2Counter) {
			this.setWinner(player2);
		}
		else
				throw new TieException("tie game ! , must be decided ");

	}

	

	@Override
	public Player getWinner() {
		// TODO Auto-generated method stub
		if (player1.isWinner())
			return player1;
		else

			return player2;
	}

	@Override
	public void setWinner(Player winner) {
		// TODO Auto-generated method stub
		if (winner == player1) {
			player1.setWinner(true);
			player2.setWinner(false);
		}
		if (winner == player2) {
			player1.setWinner(false);
			player2.setWinner(true);
		}
	}

}
