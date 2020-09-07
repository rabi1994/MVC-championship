package MODEL;

import Exceptions.FootballException;
import Exceptions.NotFiveGamesException;
import Exceptions.TennisException;
import Exceptions.TieException;

public interface GameType {

	public void startGame(int [] player1Results,int[] player2Results) throws  TennisException, FootballException, TieException;
	public Player getWinner();
	public void setWinner(Player winner);
	public boolean isPlayed();
}
