package MODEL;

import java.util.ArrayList;

public  class GroupStage {
	protected ArrayList<GameType> games;
	public ArrayList<Player> players; 
	
	public ArrayList<GameType> getGames() {
		return games;
	}
	public GroupStage(ArrayList<Player> players,String gameType) {
		this.players=players;
		games=new ArrayList<GameType>();
		if(gameType=="Tennis")
		for (int i = 0; i < players.size(); i=i+2) {
			games.add(new Tennis(players.get(i),players.get(i+1)));
		}
		if(gameType=="Football")
			for (int i = 0; i < players.size(); i=i+2) {
				games.add(new Football(players.get(i),players.get(i+1)));
			}
		if(gameType=="Basketball")
			for (int i = 0; i < players.size(); i=i+2) {
				games.add(new Basketball(players.get(i),players.get(i+1)));
			}
		
	}


	public ArrayList<Player> getWinners(){
		ArrayList<Player> winners =new ArrayList<Player>();
		for (int i = 0; i < games.size(); i++) {
			
		
		winners.add(games.get(i).getWinner());
		}
		return winners;
	}
	
	
}
