package LISTENERS;

import java.util.ArrayList;

import Exceptions.FootballException;
import Exceptions.NotFiveGamesException;
import Exceptions.TennisException;
import Exceptions.TieException;

public interface ViewListener {
	public void setWinners(ArrayList<String> winnerName);
	public void updateType(String type);
	public void updateNames(ArrayList<String> names);
	public void startGame(ArrayList<String> player1Results,ArrayList<String> player2Results,int index) throws  TennisException, FootballException, TieException;
	public void updateWinnersList(String winner);

}
