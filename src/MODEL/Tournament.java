package MODEL;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.text.View;

import Exceptions.FootballException;
import Exceptions.NotFiveGamesException;
import Exceptions.TennisException;
import Exceptions.TieException;
import LISTENERS.ModelListener;
import LISTENERS.ViewListener;

enum etype {
	Tennis, Football, Basketball
};

public class Tournament implements ModelListener {
	private ArrayList<Player> players;
	private etype type;
	private GroupStage groupStage;
	private ViewListener listener;
	public static int stageCounter = 0;

	public Tournament() {
		players = new ArrayList<Player>();

	}

	public void setType(String type) {
		this.type = etype.valueOf(type);
	}

	public void setPlayers(ArrayList<Player> players) {

		this.players = players;
		groupStage = new GroupStage(players, this.type.toString());
	}

	public void setPlayers() {
		Collections.shuffle(players);
		groupStage = new GroupStage(players, this.type.toString());
	}

	public void startGame(int index, int[] player1Results, int[] player2Results) throws TennisException, FootballException, TieException {
		boolean flag = true;
		groupStage.getGames().get(index).startGame(player1Results, player2Results);
		this.updateWinnersList(groupStage.getGames().get(index).getWinner().toString());
		
		ArrayList<GameType> games = groupStage.getGames();

		for (int i = 0; i < games.size(); i++) {
			if (!games.get(i).isPlayed()) {
				flag = false;
				break;
			}
		}
		
			if (flag) {
				ArrayList<Player> players=this.groupStage.getWinners();
				ArrayList<String> winners=new ArrayList<String>();
				for (int i = 0; i < players.size(); i++) {
					
					winners.add(players.get(i).getName());
					if(games.size()==1)
						break;
				}
				this.updateWinners(winners);
				if(games.size()!=1)
				this.nextStage();
			}
	}

	private void nextStage() {
		groupStage = new GroupStage(groupStage.getWinners(), this.type.toString());
	}

	public void updateWinnersList(String winner) {
		listener.updateWinnersList(winner);
	}

	@Override
	public void updateType(String type) {
		// TODO Auto-generated method stub
		this.setType(type);
	}

	@Override
	public void updateNames(ArrayList<String> names) {
		// TODO Auto-generated method stub
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < 8; i++) {
			players.add(new Player(names.get(i)));

		}
		this.setPlayers(players);

	}

	public void playGame(ArrayList<String> player1Results, ArrayList<String> player2Results, int index)
			throws TennisException, FootballException,TieException,NumberFormatException {
		int[] player1Arr = new int[player1Results.size()];
		int[] player2Arr = new int[player2Results.size()];

		for (int i = 0; i < player1Results.size(); i++) {
			player1Arr[i] = Integer.parseInt(player1Results.get(i));
			player2Arr[i] = Integer.parseInt(player2Results.get(i));
		}
		this.startGame(index, player1Arr, player2Arr);

	}

	public void registerListener(ViewListener listener) {
		// TODO Auto-generated method stub
		this.listener = listener;
	}

	@Override
	public void updateWinners(ArrayList<String> names) {
		// TODO Auto-generated method stub
		this.listener.setWinners(names);
	}

}
