public class Game {

	private int numberOfPlayers = 2;
	private Player[] playerArray = new Player[2];
	// Active Player
	private String gameName;
	
	// Default Game Object constructor
	Game () {
	}
	
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public void setGameName (String name) {
		this.gameName = name;
	}
	
	public String getGameName () {
		return gameName;
	}
}