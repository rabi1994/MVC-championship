package MODEL;


public abstract class Game{
	
	protected boolean isPlayed ;
	protected Player player1 ;
	protected Player player2 ;
	
	public Game(Player player1,Player player2) {
		this.player1=player1;
		this.player2=player2;
	}

	public boolean isPlayed() {
		return isPlayed;
	}

	protected void setPlayed(boolean isPlayed) {
		this.isPlayed = isPlayed;
	}
	

}
