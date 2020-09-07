package MODEL;

public class Player {
	private String name ;
	private boolean isWinner;
	public Player(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isWinner() {
		return isWinner;
	}
	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}
	public String toString() {
	return this.getName();
	}
	
	

}
